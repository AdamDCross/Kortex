package states;

import fsm.State;
import fsm.StateMachine;
import main.Line;
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

        mmText = new Message("Welcome to the world of Kortex!\n     Click to switch to game.",
                Text.BOLD, new Vector2f(Window.getInstance().getScreenWidth() / 2, 60.0f), Color.WHITE);
    }
    @Override
    public void onEntry() {
        super.onEntry();


    }

    @Override
    public void onExit() {
        super.onExit();


    }

    @Override
    public void update() {
        super.update();

        //alien.update();

        for (Event e : Window.getInstance().getGameWindow().pollEvents( )) {
            /*
            if(e.type == Event.Type.CLOSED){  //Check for window close event
                // the user pressed the close button
                Window.getInstance().getGameWindow().close( );
            }
            else if(e.type == Event.Type.MOUSE_BUTTON_PRESSED) { //Check for mouse move events
                StateMachine.getInstance().setState("GAME");
            }*/
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

        //Line.drawRect(new Vector2f(10.0f, 10.0f), new Vector2f(128.0f, 102.4f));
        //img.render(); //Uncomment if you want to text image rendering
        //alien.render();
    }
}
