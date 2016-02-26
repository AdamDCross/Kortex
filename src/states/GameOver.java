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
public class GameOver extends State {
    private Message msg;

    public GameOver(){
        super("GAMEOVER");

        msg = new Message("Game Over \n Click to go back",
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
