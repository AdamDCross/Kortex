package states;

import dijkstra.DijkstraTest;
import fsm.State;
import main.Render;
import main.Window;
import org.jsfml.window.event.Event;

import java.util.Vector;

/**
 * Created by Vince on 14/01/2016.
 */
public class Game extends State {
    private Vector<Render> gameObjects;
    private DijkstraTest test;
    //private main.Message helloWorld;

    public Game()
    {
        super("GAME");
        gameObjects = new Vector<Render>(10);

        test = new DijkstraTest();

        gameObjects.addElement(test);

        //helloWorld = new main.Message("Hello World", Text.BOLD, new Vector2f(main.Window.getInstance().getScreenWidth() / 2, main.Window.getInstance().getScreenHeight() / 2), Color.WHITE);
        //helloWorld.setText("");
    }

    @Override
    public void update() {
        super.update();

        for (Event e : Window.getInstance().getGameWindow().pollEvents( )) {
            if(e.type == Event.Type.CLOSED){  //Check for window close event
                // the user pressed the close button
                Window.getInstance().getGameWindow().close( );
            }
            else if(e.type == Event.Type.MOUSE_MOVED) { //Check for mouse move events
                test.updateCurrentMousePosition(e.asMouseEvent().position);
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

        //helloWorld.renderText();
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

