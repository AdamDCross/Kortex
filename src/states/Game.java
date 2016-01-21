package states;

import dijkstra.DijkstraTest;
import enemy.PatrollingEnemy;
import fsm.State;
import fsm.StateMachine;
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
    private PatrollingEnemy red;

    public Game()
    {
        super("GAME");
        gameObjects = new Vector<Render>(10);

        test = new DijkstraTest();

        red = new PatrollingEnemy(new Vector2f(0,50), new Vector2f(250, 50), 5);

        gameObjects.addElement(test);
        gameObjects.addElement(red);

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
}

