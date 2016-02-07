package states;

import graphics.HUD;
import main.Beacon;
import main.Button;
import org.jsfml.graphics.FloatRect;
import org.jsfml.window.Keyboard;
import test.Anite;
import test.TileTest;
import dijkstra.DijkstraTest;
import enemy.PatrollingEnemy;
import fsm.State;
import fsm.StateMachine;
import main.Render;
import main.Window;
import org.jsfml.system.Vector2f;
import org.jsfml.window.event.Event;

import java.util.Vector;

public class Game extends State {
    private Vector<Render> gameObjects;
    private DijkstraTest test;
    private PatrollingEnemy red;
    private TileTest t;
    private Anite a;
    private int numOfRemainingWaves;
    private int currentWave;
    private int score;

    private HUD panel;

    public Game()
    {
        super("GAME");
        gameObjects = new Vector<Render>(10);
        /*t=new TileTest();
        gameObjects.addElement(t);*/
        a=new Anite();
        test = new DijkstraTest();

        red = new PatrollingEnemy(new Vector2f(0,50), new Vector2f(250, 50), 5);
        //gameObjects.addElement(test);
        //gameObjects.addElement(red);

        //gameObjects.addElement(a);


        setupGame();
        panel = new HUD(0.10f, 0.1f, this);
        gameObjects.addElement(panel);
    }

    private void setupGame(){
        numOfRemainingWaves = 10;
        currentWave = 0;
        score = 0;
    }

    public int getNumOfRemainingWaves(){
        return numOfRemainingWaves;
    }

    public int getCurrentWave(){
        return currentWave;
    }

    public int getScore(){
        return score;
    }

    @Override
    public void update() {
        super.update();

        for (Event e : Window.getInstance().getGameWindow().pollEvents( )) {
            switch(e.type) {
                case CLOSED:
                    Window.getInstance().getGameWindow().close();
                    break;
                case MOUSE_MOVED:
                    test.updateCurrentMousePosition(e.asMouseEvent().position);
                    break;
                case MOUSE_BUTTON_PRESSED:
                    panel.mousePress(e.asMouseButtonEvent().position);
                    break;
                case RESIZED:
                    Window.getInstance().recalculateScreenRes(e.asSizeEvent().size);
                    break;
                case KEY_RELEASED:
                    if(e.asKeyEvent().key == Keyboard.Key.P){
                        StateMachine.getInstance().setState("PAUSE");
                    }
            }
        }

        for( int i = 0; i < gameObjects.size(); i++)
        {
            gameObjects.elementAt(i).update();
        }

        //Beacon.getInstance().update();
    }

    @Override
    public void render() {
        super.render();

        for( int i = 0; i < gameObjects.size(); i++)
        {
            gameObjects.elementAt(i).render();
        }

        //Beacon.getInstance().render();


    }

    @Override
    public void onEntry() {
        super.onEntry();
        //Beacon.getInstance().spawn(true, 100, new Vector2f(0.0f,0.0f));
    }

    @Override
    public void onExit() {
        super.onExit();
    }
}

