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
 * Created by vincentdealmeida on 26/01/2016.
 */
public class HighScore extends State {
    private Message msg;

    public HighScore(){
        super("HIGH_SCORE");

        msg = new Message("Welcome to high score state!!!!!!1",
                Text.BOLD, new Vector2f(Window.getInstance().getScreenWidth() / 2, Window.getInstance().getScreenHeight() / 2), Color.WHITE);
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
                    //StateMachine.getInstance().setState("GAME");
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
