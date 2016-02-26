package states;

import assets.AssetManager;
import enemy.NPCHandle;
import graphics.HUD;
import main.*;
import org.jsfml.audio.Music;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import player.Player;
import fsm.State;
import fsm.StateMachine;
import org.jsfml.window.event.Event;

import java.util.Vector;

public class Game extends State {
    private Vector<Render> gameObjects;
    private int numOfRemainingWaves;
    private int currentWave;
    private HUD panel;
    private Music inGameMusic;

    private Player player;

    public Game()
    {
        super("GAME");
        gameObjects = new Vector<Render>(10);

        setupGame();

        gameObjects.addElement(panel);

        inGameMusic = AssetManager.getInstance().getAudioAssetByAssetType("MUSIC").elementAt(1).getMusicObject();
    }

    private void setupGame(){
        numOfRemainingWaves = 10;
        currentWave = 0;
        player = new Player("Abe ;)", 150);
        NPCHandle.getInstance().setPlayer(player);
        panel = new HUD(0.10f, 0.1f, this,player);
    }

    //method called just before the game is due to quit from inside the game
    //and all clean up code can be executed in here
    private void quitGame(){
        player.gameQuit();
        inGameMusic.stop();
        HighScoreManager.getInstance().writeScoresToFile();
    }

    public int getNumOfRemainingWaves(){
        return numOfRemainingWaves;
    }

    public int getCurrentWave(){
        return currentWave;
    }

    public long getScore(){
        return player.getScore();
    }

    @Override
    public void update() {
        super.update();

        for (Event e : Window.getInstance().getGameWindow().pollEvents( )) {
            switch(e.type) {
                case CLOSED:
                    quitGame();
                    Window.getInstance().getGameWindow().close();
                    break;
                case MOUSE_MOVED:
                    Vector2i mPos = e.asMouseEvent().position;
                    panel.mouseMove(mPos);
                    break;
                case MOUSE_BUTTON_PRESSED:
                    panel.mousePress(e.asMouseButtonEvent().position);
                    break;
                case RESIZED:
                    Window.getInstance().recalculateScreenRes(e.asSizeEvent().size);
                    break;
                case KEY_RELEASED:
                    if(e.asKeyEvent().key == Keyboard.Key.ESCAPE){
                        StateMachine.getInstance().setState("PAUSE");
                    }
            }
        }

        for( int i = 0; i < gameObjects.size(); i++)
        {
            gameObjects.elementAt(i).update();
        }

        Beacon.getInstance().update();
    }

    @Override
    public void render() {
        super.render();

        for( int i = 0; i < gameObjects.size(); i++)
        {
            gameObjects.elementAt(i).render();
        }

        Beacon.getInstance().render();
    }

    @Override
    public void onEntry() {
        super.onEntry();
        inGameMusic.play();
        if(StateMachine.getInstance().getPreviousStateID().equals("MAIN_MENU")){
            setupGame();
        }
    }

    @Override
    public void onExit() {
        super.onExit();
        inGameMusic.stop();
        quitGame();
    }
}

