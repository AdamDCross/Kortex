package states;

import Test.Anite;
import Test.TileTest;
import dijkstra.DijkstraTest;
import enemy.Enemy;
import fsm.State;
import fsm.StateMachine;
import main.Cell;
import main.Render;
import main.Window;
import org.jsfml.system.Vector2f;
import org.jsfml.window.event.Event;

import java.util.Vector;

/**
 * Created by Vince on 14/01/2016.
 */
public class Game extends State {
    private Vector<Render> gameObjects;
    private DijkstraTest test;
    private Enemy red;
    private TileTest t;
    private Anite a;

    public Game()
    {
        super("GAME");
        gameObjects = new Vector<Render>(10);
        /*t=new TileTest();
        gameObjects.addElement(t);*/
        a=new Anite();
        test = new DijkstraTest();

        red = new Enemy(getCell(1,2), 5);

        gameObjects.addElement(test);
        gameObjects.addElement(red);

        gameObjects.addElement(a);

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
                    StateMachine.getInstance().setState("PAUSE");
                    break;
            }
        }

        for( int i = 0; i < gameObjects.size(); i++)
        {
            gameObjects.elementAt(i).update();
        }
    }

    @Override
    public void render() {
        super.render();

        for( int i = 0; i < gameObjects.size(); i++)
        {
            gameObjects.elementAt(i).render();
        }
    }

    @Override
    public void onEntry() {
        super.onEntry();
    }

    @Override
    public void onExit() {
        super.onExit();
    }

    public Cell getCell(int x, int y){
        return test.getCell(x,y);
    }
}

