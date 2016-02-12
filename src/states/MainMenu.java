package states;

import fsm.State;
import fsm.StateMachine;
import main.Button;
import main.Line;
import main.Message;
import main.Window;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;

import java.util.Vector;

/**
 * MainMenu state which contains all code and rendering
 * for all front-end related entities. It is the first
 * state run when starting the game.
 * -----------------------------------------------------
 * Created by Vincent de Almeida | Updated: 29/01/2016
 * -----------------------------------------------------
 */
public class MainMenu extends State {
    private Message mmText;
    private Vector<Button> btns;

    public MainMenu() {
        super("MAIN_MENU");

        /*mmText = new Message("Welcome to the world of Kortex!\n     Click to switch to game.",
                Text.BOLD, new Vector2f(Window.getInstance().getScreenWidth() / 2, 60.0f), Color.WHITE);*/
        mmText = new Message("Welcome to the world of Kortex!\n     Click to switch to game.",
                Text.BOLD, new FloatRect(0.0f, 0.0f, 640.0f, 125.0f), Color.WHITE, 35);

        btns = new Vector<Button>(5);

        //main.Button btn = new main.Button("Button", new FloatRect(260.0f, 250.0f, 150.0f, 50.0f), 35);

        //btns.addElement(btn);
    }
    @Override
    public void onEntry() {
        super.onEntry();


    }

    @Override
    public void onExit() {
        super.onExit();
        Window.getInstance().resetClock();
    }

    @Override
    public void update() {
        super.update();

        for (Event e : Window.getInstance().getGameWindow().pollEvents( )) {
            switch(e.type) {
                case CLOSED:
                    Window.getInstance().getGameWindow().close();
                    break;
                case MOUSE_BUTTON_PRESSED:
                    StateMachine.getInstance().setState("GAME");
                    break;
            }
        }

        for(int i = 0; i < btns.size(); i++){ btns.elementAt(i).update(); }
    }

    @Override
    public void render() {
        super.render();

        mmText.renderText();

        for(int i = 0; i < btns.size(); i++){ btns.elementAt(i).render(); }
    }
}
