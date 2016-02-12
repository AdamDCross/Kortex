package states;

import config.BasicConfiguration;
import config.ConfigurationManager;
import fsm.State;
import fsm.StateMachine;
import main.Message;
import main.Window;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
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

        msg = new Message("Welcome to the option state!\n     Click to switch to menu.",
                Text.BOLD, new FloatRect(0.0f, 0.0f, Window.getInstance().getScreenWidth(),Window.getInstance().getScreenHeight()), Color.WHITE, 35);
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
                    if(StateMachine.getInstance().getPreviousStateID().equals("PAUSE")){
                        StateMachine.getInstance().setState("GAME");
                    }
                    else {
                        StateMachine.getInstance().setState("MAIN_MENU");
                    }
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

        if(StateMachine.getInstance().getPreviousStateID().equals("PAUSE")){
            msg.setText("Welcome to the option state!\nClick to switch back to game.");
        }
        else{

        }
    }

    @Override
    public void onExit() {
        super.onExit();
    }
}
