package main;

import assets.ArtAsset;
import graphics.Animation;
import graphics.Image;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
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

    public Button(String text, FloatRect dimensions, int fontSize, String ID, boolean borderActive){
        stringText = text;
        this.fontSize = fontSize;
        txt = new Message(text, Text.BOLD, dimensions, Color.WHITE, fontSize);
        this.dimensions = dimensions;
        this.ID = ID;
        this.borderActive = borderActive;
        usesImage = false;
        buttonImage = null;
        animation = null;
    }

    public Button(String text, FloatRect dimensions, int fontSize, String ID, boolean borderActive, Color c){
        stringText = text;
        this.fontSize = fontSize;
        txt = new Message(text, Text.BOLD, dimensions, c, fontSize);
        this.dimensions = dimensions;
        this.ID = ID;
        this.borderActive = borderActive;
        usesImage = false;
        buttonImage = null;
        animation = null;
    }

    public Button(String filePath, FloatRect dimensions, String ID, boolean borderActive){
        this.borderActive = borderActive;
        this.ID = ID;
        buttonImage = new Image(filePath, new Vector2f(dimensions.left, dimensions.top));

        if( needsScaling(buttonImage.getRectofImage(), dimensions) ) {
            scale(buttonImage.getSprite(), dimensions.width, dimensions.height);
        }

        usesImage = true;
        this.dimensions = dimensions;
        animation = null;
    }

    public Button(Image buttonImage, FloatRect dimensions, String ID, boolean borderActive){
        this.borderActive = borderActive;
        this.ID = ID;
        this.buttonImage = buttonImage;

        if( needsScaling(buttonImage.getRectofImage(), dimensions) ) {
            scale(buttonImage.getSprite(), dimensions.width, dimensions.height);
        }

        usesImage = true;
        this.dimensions = dimensions;
        animation = null;
    }

    public Button(ArtAsset asset, FloatRect dimensions, boolean borderActive){
        this.borderActive = borderActive;
        this.ID = asset.getAssetID();
        buttonImage = asset.getImage();
        buttonImage.setPosition(new Vector2f(dimensions.left, dimensions.top));

        if( needsScaling(buttonImage.getRectofImage(), dimensions) ) {
            scale(buttonImage.getSprite(), dimensions.width, dimensions.height);
        }

        usesImage = true;
        this.dimensions = dimensions;
        animation = null;
    }

    public Button(String filePath, int width, int height, int columns, int rows, int delay, FloatRect dimensions, String ID, boolean borderActive, boolean isAnimation){
        this.borderActive = borderActive;
        this.ID = ID;
        animation = new Animation(filePath, width, height, columns, rows, delay, new Vector2f(dimensions.left, dimensions.top), 1, true);

        if( needsScaling(animation.getSprite().getLocalBounds(), dimensions) ) {
            scale(animation.getSprite(), dimensions.width, dimensions.height);
        }

        usesImage = false;
        this.dimensions = dimensions;
        this.isAnimation = isAnimation;
        buttonImage = null;
    }

    private boolean needsScaling(FloatRect imageDimensions, FloatRect borderDimensions){
        if( imageDimensions.width < borderDimensions.width
                || imageDimensions.height < borderDimensions.height
                || imageDimensions.width > borderDimensions.width
                || imageDimensions.height > borderDimensions.height) {
            return true;
        }

        return false;
    }

    private void scale(Sprite imgToScale, float scaleToWidth, float scaleToHeight ){
        imgToScale.setOrigin(0.0f, 0.0f);
        imgToScale.setScale(scaleToWidth / imgToScale.getLocalBounds().width,
                scaleToHeight / imgToScale.getLocalBounds().height);
    }

    public void setOriginCentre(){
        if(buttonImage != null){
            buttonImage.setOriginCentre();
        }
    }

    public void setAngleOfImage(float angle){
        if(buttonImage != null){
            buttonImage.rotate(angle);
        }
    }

    public void setPositionOfImage(Vector2f pos){
        if(buttonImage != null){
            buttonImage.setPosition(pos);
        }
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

    public FloatRect getDimensions(){
        return dimensions;
    }

    public void toggleRenderBorder(){
        borderActive = !borderActive;
    }

    public String getButtonID(){
        return this.ID;
    }
}
