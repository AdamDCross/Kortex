package states;

import assets.AssetManager;
import fsm.State;
import fsm.StateMachine;
import graphics.Image;
import main.Button;
import main.Message;
import main.Window;
import org.jsfml.audio.Music;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.event.Event;
import sun.applet.Main;

public class Pause extends State {
    private Image bcg;
    private org.jsfml.graphics.Image capture;
    private Button resume;
    private Button mute;
    private Button menu;
    private boolean muted;


    /**
     * This is a constructor for the Pause class it doesn't take any parameters but instead captures an image of the
     * current screen being displayed
     */
    public Pause(){
        super("PAUSE");

        float baseX = (Window.getInstance().getScreenWidth() / 2) / 2;
        baseX = baseX - (MainMenu.BUTTON_WIDTH);
        float x = baseX;
        float y = (Window.getInstance().getScreenHeight() / 2) - (MainMenu.BUTTON_HEIGHT / 2);
        resume = new Button("Resume", new FloatRect(x,y,MainMenu.BUTTON_WIDTH,MainMenu.BUTTON_HEIGHT),20,"GAME",true);

        x = (Window.getInstance().getScreenWidth() / 2) - (MainMenu.BUTTON_WIDTH / 2);
        mute = new Button("Mute", new FloatRect(x,y,MainMenu.BUTTON_WIDTH,MainMenu.BUTTON_HEIGHT),20,"MUTE",true);

        x = (Window.getInstance().getScreenWidth() / 2) + (((Window.getInstance().getScreenWidth()) - (Window.getInstance().getScreenWidth() / 2)) / 2);
        menu = new Button("Menu", new FloatRect(x,y,MainMenu.BUTTON_WIDTH,MainMenu.BUTTON_HEIGHT),20,"MAIN_MENU",true);

        muted = false;
    }

    /**
     * This method renders the captured screen shot image with an over lay od text "paused.... Click to unpause"
     */
    @Override
    public void render() {
        bcg.render();

        RectangleShape r=new RectangleShape(new Vector2f(Window.getInstance().getScreenWidth(),Window.getInstance().getScreenHeight()));
        r.setFillColor(new Color(Color.BLACK,128));
        Window.getInstance().getGameWindow().draw(r);

        resume.render();
        mute.render();
        menu.render();
    }

    /**
     * This method checks whether or not the mouse has been clicked or not in order to change state from Pause back to
     * whatever state was running before
     */
    @Override
    public void update() {
        super.update();
        for (Event e : Window.getInstance().getGameWindow().pollEvents( )) {
            switch(e.type) {
                case CLOSED:
                    Window.getInstance().getGameWindow().close();
                    break;
                case MOUSE_BUTTON_PRESSED:
                    Vector2i mousePos = e.asMouseEvent().position;
                    if(resume.isWithinRect(mousePos)) {
                        StateMachine.getInstance().setState("GAME");
                    }
                    else if(mute.isWithinRect(mousePos)){
                        muted = !muted;
                        //mute audio playback when config manager is fully up and running
                        if(muted){
                            AssetManager.getInstance().getAudioAssetByAssetType("MUSIC").elementAt(1).getMusicObject().setVolume(0.0f);
                        }
                        else{
                            AssetManager.getInstance().getAudioAssetByAssetType("MUSIC").elementAt(1).getMusicObject().setVolume(25.0f);
                        }
                    }
                    else if(menu.isWithinRect(mousePos)){
                        StateMachine.getInstance().setState("MAIN_MENU");
                    }
                    break;
                case KEY_RELEASED:
                    if(e.asKeyEvent().key == Keyboard.Key.ESCAPE){
                        StateMachine.getInstance().setState("GAME");
                    }
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

        if(StateMachine.getInstance().getPreviousStateID().equals("GAME")) {
            capture = Window.getInstance().getGameWindow().capture();
            bcg = new Image(capture, new Vector2f(0, 0));
        }
    }
}
