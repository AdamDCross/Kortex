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

    public MainMenu(){
        super("MAIN_MENU");

        mmText = new Message("Welcome to the\n world of main.Kortex!\n\n Enter at your peril...\n\nClick to switch to game.",
                Text.BOLD, new Vector2f(Window.getInstance().getScreenWidth() / 2, Window.getInstance().getScreenHeight() / 2), Color.WHITE);

        //img = new graphics.Image("C:\\Users\\Vince\\IdeaProjects\\main.Kortex\\src\\team.jpg", new Vector2f(main.Window.getInstance().getScreenWidth() / 2, main.Window.getInstance().getScreenHeight() / 2));
        //alien = new graphics.Animation("C:\\Users\\Vince\\IdeaProjects\\main.Kortex\\animation\\tmp-", ".gif", 22, 75, new Vector2f(main.Window.getInstance().getScreenWidth() / 2, main.Window.getInstance().getScreenHeight() / 2));
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
            if(e.type == Event.Type.CLOSED){  //Check for window close event
                // the user pressed the close button
                Window.getInstance().getGameWindow().close( );
            }
            else if(e.type == Event.Type.MOUSE_BUTTON_PRESSED) { //Check for mouse move events
                StateMachine.getInstance().setState("GAME");
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
