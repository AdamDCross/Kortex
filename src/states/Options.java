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
 * Class for the options menu state.
 */
public class Options extends State {
    private Message msg;

    public Options(){
        super("OPTIONS");

        msg = new Message("Welcome to option state!!!!!!1",
                Text.BOLD, new Vector2f(Window.getInstance().getScreenWidth() / 2, Window.getInstance().getScreenHeight() / 2), Color.WHITE, 48);
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
                    StateMachine.getInstance().setState("MAIN_MENU");
                    break;
            }

        }
    }

    @Override
    public void render() {
        super.render();

        msg.renderText();
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
