package states;

import fsm.State;
import fsm.StateMachine;
import main.Message;
import main.Window;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.window.event.Event;

/**
 * Created by Vince on 14/01/2016.
 */
public class MainMenu extends State {
    private Message mmText;
    //graphics.Image img;
    //graphics.Animation alien;

    public MainMenu() {
        super("MAIN_MENU");

        mmText = new Message("Welcome to the\n world of Kortex!\n\n Enter at your peril...\n\nClick to switch to game.",
                Text.BOLD, new Vector2f(Window.getInstance().getScreenWidth() / 2, Window.getInstance().getScreenHeight() / 2), Color.WHITE);
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

        //alien.update();

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
    }

    @Override
    public void render() {
        super.render();

        mmText.renderText();
        //img.render(); //Uncomment if you want to text image rendering
        //alien.render();
    }
}
