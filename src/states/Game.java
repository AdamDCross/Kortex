package states;

import main.Beacon;
import main.Button;
import org.jsfml.graphics.FloatRect;
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
    private Button menu;

    public Game()
    {
        super("GAME");
        gameObjects = new Vector<Render>(10);
        /*t=new TileTest();
        gameObjects.addElement(t);*/
        a=new Anite();
        test = new DijkstraTest();

        red = new PatrollingEnemy(new Vector2f(0,50), new Vector2f(250, 50), 5);

        gameObjects.addElement(test);
        gameObjects.addElement(red);

        gameObjects.addElement(a);

        menu = new Button("Menu", new FloatRect(Window.getInstance().getScreenWidth() - 160.0f, 10.0f, 150.0f, 50.0f), 35, "MENU");

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
                     if(menu.isWithinRect(e.asMouseEvent().position)) {
                         StateMachine.getInstance().setState("MAIN_MENU");
                     }
                     else {
                         StateMachine.getInstance().setState("PAUSE");
                     }
                    break;
                case RESIZED:
                    Window.getInstance().recalculateScreenRes(e.asSizeEvent().size);
                    break;
            }
        }

        for( int i = 0; i < gameObjects.size(); i++)
        {
            gameObjects.elementAt(i).update();
        }

        Beacon.getInstance().update();

        //System.out.println("Mouse tile x:" + test.getRelTileForMousePosition().x + ", Mouse tile y:" + test.getRelTileForMousePosition().y);
    }

    @Override
    public void render() {
        super.render();

        for( int i = 0; i < gameObjects.size(); i++)
        {
            gameObjects.elementAt(i).render();
        }

        Beacon.getInstance().render();

        menu.render();
    }

    @Override
    public void onEntry() {
        super.onEntry();
        Beacon.getInstance().spawn(true, 100, new Vector2f(0.0f,0.0f));
    }

    @Override
    public void onExit() {
        super.onExit();
    }
}

