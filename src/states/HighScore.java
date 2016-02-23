package states;

import fsm.State;
import fsm.StateMachine;
import main.HighScoreManager;
import main.Message;
import main.Score;
import main.Window;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.window.event.Event;

import java.util.Vector;

/**
 * High score state class.
 */
public class HighScore extends State {
    private Message msg;
    private Vector<Message> highScoreMessages;
    private Vector<Score> highScores;

    public HighScore(){
        super("HIGH_SCORE");

        msg = new Message("High Scores",
                Text.BOLD, new FloatRect(0.0f, 0.0f, Window.getInstance().getScreenWidth(), 0.10f * Window.getInstance().getScreenHeight()), Color.WHITE, 35);

        highScoreMessages = new Vector<>(10);
    }

    private void recalculateMessages(){
        highScoreMessages.clear();

        //Create left side rects for names
        for(int i = 0; i < highScores.size(); i++){
            highScoreMessages.addElement(new Message(highScores.elementAt(i).name,Text.BOLD,
                    new FloatRect(0.0f, 0.10f * Window.getInstance().getScreenHeight() + (i * (Window.getInstance().getScreenHeight() / 10)),
                            Window.getInstance().getScreenWidth() / 2, Window.getInstance().getScreenHeight() / 10),
                    Color.WHITE, 35));
        }

        //Create right side rects for scores
        for(int i = 0; i < highScores.size(); i++) {
            highScoreMessages.addElement(new Message("" + highScores.elementAt(i).score, Text.BOLD,
                    new FloatRect(Window.getInstance().getScreenWidth() / 2, 0.10f * Window.getInstance().getScreenHeight() + (i * (Window.getInstance().getScreenHeight() / 10)),
                            Window.getInstance().getScreenWidth() / 2, Window.getInstance().getScreenHeight() / 10),
                    Color.WHITE, 35));
        }
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

        for(int i = 0; i < highScoreMessages.size(); i++){
            highScoreMessages.elementAt(i).renderText();
        }
    }

    @Override
    public void onEntry() {
        super.onEntry();
        highScores = HighScoreManager.getInstance().getHighScores();
        recalculateMessages();
    }

    @Override
    public void onExit() {
        super.onExit();
    }
}
