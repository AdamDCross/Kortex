package main;

import assets.ArtAsset;
import graphics.Animation;
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
    private Animation animation;
    private boolean usesImage;
    private boolean isAnimation;

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

    public Button(Image buttonImage, FloatRect dimensions, String ID, boolean borderActive){
        this.borderActive = borderActive;
        this.ID = ID;
        this.buttonImage = buttonImage;

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

    public Button(ArtAsset asset, FloatRect dimensions, boolean borderActive){
        this.borderActive = borderActive;
        this.ID = asset.getAssetID();
        buttonImage = asset.getImage();
        buttonImage.setPosition(new Vector2f(dimensions.left, dimensions.top));

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

    public Button(String filePath, int width, int height, int columns, int rows, int delay, FloatRect dimensions, String ID, boolean borderActive, boolean isAnimation){
        this.borderActive = borderActive;
        this.ID = ID;
        animation = new Animation(filePath, width, height, columns, rows, delay, new Vector2f(dimensions.left, dimensions.top), 1);

        if( animation.getSprite().getLocalBounds().width < dimensions.width
                || animation.getSprite().getLocalBounds().height < dimensions.height
                || animation.getSprite().getLocalBounds().width > dimensions.width
                || animation.getSprite().getLocalBounds().height > dimensions.height) {

            animation.getSprite().setOrigin(0.0f, 0.0f);
            animation.getSprite().setScale(dimensions.width / animation.getSprite().getLocalBounds().width,
                    dimensions.height / animation.getSprite().getLocalBounds().height);
        }

        usesImage = false;
        this.dimensions = dimensions;
        this.isAnimation = isAnimation;
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
        if(isAnimation){
            animation.update();
        }
    }

    @Override
    public void render() {

        if(!usesImage && !isAnimation){
            txt.renderText();
        }
        else if(usesImage)
        {
            buttonImage.render();
        }
        else if(isAnimation){
            animation.render();
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
