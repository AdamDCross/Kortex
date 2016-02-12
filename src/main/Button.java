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

    private boolean isWithinRect(Vector2f pos){
        return pos.x>dimensions.left&&pos.x<(dimensions.left+dimensions.width)&&pos.y>dimensions.top&&pos.y<(dimensions.top+dimensions.height);
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
