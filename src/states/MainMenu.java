package states;

import assets.AssetManager;
import fsm.State;
import fsm.StateMachine;
import main.*;
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
    private String lastPressButtonID;
    public static final float BUTTON_WIDTH = 150.0f;

    public MainMenu() {
        super("MAIN_MENU");

        mmText = new Message("Kortex",
                Text.BOLD, new FloatRect(0.0f, 0.0f, Window.getInstance().getScreenWidth(), 0.26f * Window.getInstance().getScreenHeight()), Color.WHITE, 35);

        btns = new Vector<>(5);

        float x = (((Window.getInstance().getScreenWidth() / 2) - (BUTTON_WIDTH/2)) / Window.getInstance().getScreenWidth()) * Window.getInstance().getScreenWidth();

        main.Button btn = new main.Button("New",
                new FloatRect(x, 0.3125f * Window.getInstance().getScreenHeight(), BUTTON_WIDTH, 50.0f), 35, "GAME");
        main.Button btn2 = new main.Button("Scores",
                new FloatRect(x, 0.4375f * Window.getInstance().getScreenHeight(), BUTTON_WIDTH, 50.0f), 35, "HIGH_SCORE");
        main.Button btn3 = new main.Button("Options",
                new FloatRect(x, 0.5625f * Window.getInstance().getScreenHeight(), BUTTON_WIDTH, 50.0f), 35, "OPTIONS");
        main.Button btn4 = new main.Button("Exit",
                new FloatRect(x, 0.6875f * Window.getInstance().getScreenHeight(), BUTTON_WIDTH, 50.0f), 35, "EXIT");

        btns.addElement(btn);
        btns.addElement(btn2);
        btns.addElement(btn3);
        btns.addElement(btn4);

        lastPressButtonID = "NONE";
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
                    for(int i = 0; i < btns.size(); i++) {
                        if (btns.elementAt(i).isWithinRect(e.asMouseEvent().position)) {
                            lastPressButtonID = btns.elementAt(i).getButtonID();
                            break;
                        }
                    }
                    break;
                case RESIZED:
                    Window.getInstance().recalculateScreenRes(e.asSizeEvent().size);
                    break;
            }
        }

        if( lastPressButtonID.equals("EXIT") ){
            Window.getInstance().getGameWindow().close();
        }
        else if(!lastPressButtonID.equals("NONE")){
            StateMachine.getInstance().setState(lastPressButtonID);
        }

        lastPressButtonID = "NONE";
    }

    @Override
    public void render() {
        super.render();

        mmText.renderText();

        for(int i = 0; i < btns.size(); i++){ btns.elementAt(i).render(); }
    }
}
