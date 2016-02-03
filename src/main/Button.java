package main;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

/**
 * Button class
 */
public class Button implements Render {
    private Message txt;
    private FloatRect dimensions;
    private String ID;

    public Button(String text, FloatRect dimensions, int fontSize, String ID){
        txt = new Message(text, Text.BOLD, dimensions, Color.WHITE, fontSize);
        this.dimensions = dimensions;
        this.ID = ID;
    }

    public boolean isWithinRect(Vector2i pos){
        if( (((float)pos.x) >= dimensions.left) && (((float)pos.x) <= (dimensions.left+dimensions.width)) &&
                (((float)pos.y) >= dimensions.top) && (((float)pos.y) <= (dimensions.top+dimensions.height)) ) {
                return true;
        }

        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        txt.renderText();
        Line.drawRect(dimensions);

    }

    public String getButtonID(){
        return this.ID;
    }
}
