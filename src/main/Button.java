package main;

import graphics.Image;
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
    private String stringText;
    private FloatRect dimensions;
    private int fontSize;
    private String ID;
    private boolean borderActive;
    private Image buttonImage;
    private boolean usesImage;

    public Button(String text, FloatRect dimensions, int fontSize, String ID){
        stringText = text;
        this.fontSize = fontSize;
        txt = new Message(text, Text.BOLD, dimensions, Color.WHITE, fontSize);
        this.dimensions = dimensions;
        this.ID = ID;
        borderActive = true;
        usesImage = false;
    }

    public Button(String filePath, FloatRect dimensions, String ID, boolean borderActive){
        this.borderActive = borderActive;
        this.ID = ID;
        buttonImage = new Image(filePath, new Vector2f(dimensions.left, dimensions.top));

        if( buttonImage.getRectofImage().width < dimensions.width
                || buttonImage.getRectofImage().height < dimensions.height
                || buttonImage.getRectofImage().width > dimensions.width
                || buttonImage.getRectofImage().height > dimensions.height) {

            buttonImage.setOriginTopleft();
            buttonImage.setScale(dimensions.width / buttonImage.getRectofImage().width,
                    dimensions.height / buttonImage.getRectofImage().height);
        }

        usesImage = true;
        this.dimensions = dimensions;
    }

    public boolean isWithinRect(Vector2i pos) {
        if ((((float) pos.x) >= dimensions.left) && (((float) pos.x) <= (dimensions.left + dimensions.width)) &&
                (((float) pos.y) >= dimensions.top) && (((float) pos.y) <= (dimensions.top + dimensions.height))) {
            System.out.println(this.ID+" pressed");
            return true;
        }

        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

        if(!usesImage){
            txt.renderText();
        }
        else
        {
            buttonImage.render();
        }

        if(borderActive) {
            Line.drawRect(dimensions);
        }

    }

    public void toggleRenderBorder(){
        borderActive = !borderActive;
    }

    public String getButtonID(){
        return this.ID;
    }
}
