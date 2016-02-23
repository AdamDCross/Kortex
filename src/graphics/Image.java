package graphics;

import main.Render;
import main.Window;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Image class
 */
public class Image implements Render {
    public Texture imgTexture;
    public Sprite img;

    /**This class draws a sprite on screen at given position,The function gets the sprite image from a a specified directory
     * and draws that to a given position
     * @param filePath This string specifies the directory location of the sprite image
     * @param position This specifies the location onscreen of the sprite
     */

    public Image(String filePath, Vector2f position){
        imgTexture = new Texture();

        try {
            imgTexture.loadFromFile(Paths.get(filePath));
        } catch (IOException ex) {
            ex.printStackTrace( );
        }
        imgTexture.setSmooth(true);

        img = new Sprite(imgTexture);
        //img.setOrigin(Vector2f.div(
                //new Vector2f(imgTexture.getSize()), 2));
        img.setPosition(position);
    }

    /**
     * This class draws a texture over the sprite at a given position
     * @param texture idk
     * @param position This specifies the location onscreen of the sprite
     */

    public Image(org.jsfml.graphics.Image texture,Vector2f position){
        imgTexture=new Texture();
        try {
            imgTexture.loadFromImage(texture);
        }catch(TextureCreationException e){
            e.printStackTrace();
        }
        imgTexture.setSmooth(true);

        img = new Sprite(imgTexture);
        //img.setOrigin(Vector2f.div(new Vector2f(imgTexture.getSize()), 2));
        img.setPosition(position);
    }

    /**
     * This renders both the sprite and texture to screen
     *
     */
    @Override
    public void render() {
        Window.getInstance().getGameWindow().draw(img);
    }


    @Override
    public void update() {
    }

    public void setPosition(Vector2f pos){
        img.setPosition(pos);
    }

    public void rotate(float angle){
        img.rotate(angle);
    }

    public void setRenderRect(IntRect rect){
        img.setTextureRect(rect);
    }

    public void setOrigin(Vector2f orig){
        img.setOrigin(orig);
    }

    public void setOriginTopleft(){
        FloatRect localBounds = img.getLocalBounds();
        img.setOrigin(new Vector2f(localBounds.left, localBounds.top));
    }

    public void setOriginCentre(){
        FloatRect localBounds = img.getLocalBounds();
        img.setOrigin(new Vector2f( (localBounds.left + localBounds.width) / 2,
                                                                (localBounds.top + localBounds.height) / 2));
    }

    public void setScale(float scaleFactorX, float scaleFactorY){
        img.setScale(new Vector2f(scaleFactorX, scaleFactorY));
    }

    public void setColourMask(Color col){
        img.setColor(col);
    }

    public FloatRect getRectofImage(){
        return img.getLocalBounds();
    }

    public Sprite getSprite(){
        return img;
    }
}
