package states;

import assets.ArtAsset;
import assets.AssetManager;
import enemy.Enemy;
import enemy.NPCHandle;
import graphics.HUD;
import main.*;
import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import player.Player;
import player.Turret;
import test.Anite;
import test.TileTest;
import dijkstra.DijkstraTest;
import fsm.State;
import fsm.StateMachine;
import org.jsfml.system.Vector2f;
import org.jsfml.window.event.Event;

import java.util.Vector;

public class Game extends State {
    private Vector<Render> gameObjects;
    private DijkstraTest test;
    private Enemy red;
    private Anite a;
    private int numOfRemainingWaves;
    private int currentWave;
    NPCHandle handler;
    private HUD panel;

    private Player player;

    public Game()
    {
        super("GAME");
        gameObjects = new Vector<Render>(10);
        /*t=new TileTest();
        gameObjects.addElement(t);*/
        a=new Anite();
        //test = new DijkstraTest();

        red = new Enemy(new Vector2f(0,50), new Vector2f(250, 50), 5);
        //gameObjects.addElement(test);
        //gameObjects.addElement(red);
        //gameObjects.addElement(a);

        setupGame();
        player = new Player("Kortex player", 50);
        panel = new HUD(0.10f, 0.1f, this,player);
        gameObjects.addElement(panel);
        //handler=new NPCHandle(this,player);


        Vector<ArtAsset> turrets = AssetManager.getInstance().getArtAssetByAssetType("TURRET");
        Turret turretTest = new Turret(turrets.elementAt(0).getAssetPath(),
                turrets.elementAt(1).getAssetPath(),true,100,0.0f,1.0f,110,
                new FloatRect(panel.getGameWindowRect().left,  panel.getGameWindowRect().height - 128, 141.0f,128.0f),
                0,0,0,0,false,0,"TEST","assets/explosions/explosiontilesheet.png",141,128,10,11,50);
        //handler.addTurret(turretTest);
        //gameObjects.addElement(turretTest);


    }

    private void setupGame(){
        numOfRemainingWaves = 10;
        currentWave = 0;
    }

    //method called just before the game is due to quit from inside the game
    //and all clean up code can be executed in here
    private void quitGame(){
        player.gameQuit();
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
                    test.updateCurrentMousePosition(mPos);
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
        FloatRect beaconSize = new FloatRect(panel.getGameWindowRect().width / 2, panel.getGameWindowRect().height / 2, 64, 64);
        Beacon.getInstance().spawn(true, 100, new Vector2f(panel.getGameWindowRect().width / 2,panel.getGameWindowRect().height / 2), beaconSize);
    }

    @Override
    public void onExit() {
        super.onExit();
        quitGame();
    }
}

