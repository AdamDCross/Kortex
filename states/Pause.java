package states;

import fsm.State;
import fsm.StateMachine;
import graphics.Image;
import main.Message;
import main.Window;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.window.event.Event;


/**
 * Created by Phillip on 20/01/2016.
 */
public class Pause extends State {

    int state;
    Image bcg;
    org.jsfml.graphics.Image capture;

    public Pause(){
        super("PAUSE");
        capture=Window.getInstance().getGameWindow().capture();
        bcg=new Image(capture,new Vector2f(0,0));

    }

    @Override
    public void render() {
        bcg.render();
        RectangleShape r=new RectangleShape(new Vector2f(32,32));
        //r.setFillColor(new Color(Color.BLACK,48));
        Window.getInstance().getGameWindow().draw(r);
        new Message("PAUSED...\n\n\nclock to unpause", Text.BOLD, new Vector2f(Window.getInstance().getScreenWidth() / 2, Window.getInstance().getScreenHeight() / 2), Color.WHITE).renderText();
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
    }

    @Override
    public void onExit() {
        super.onExit();
    }

    @Override
    public void onEntry() {
        super.onEntry();
        capture=Window.getInstance().getGameWindow().capture();
        bcg=new Image(capture,new Vector2f(0,0));

    }
}
