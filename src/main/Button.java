package main;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

/**
 * Button class
 */
public class Button implements Render {
    private Message txt;
    private FloatRect dimensions;

    public Button(String text, FloatRect dimensions, int fontSize){
        txt = new Message(text, Text.BOLD, dimensions, Color.WHITE, fontSize);
        this.dimensions = dimensions;
    }

    private boolean isWithinRect(){
        return true;
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        txt.renderText();
        Line.drawRect(dimensions);

    }


}
