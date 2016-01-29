package graphics;

import main.Render;
import main.Window;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.TextureCreationException;
import org.jsfml.graphics.Vertex;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Vince on 14/01/2016.
 */


//Old version

public class Image implements Render {
    public Texture imgTexture;
    public Sprite img;

    public Image(String filePath, Vector2f position){
        imgTexture = new Texture();

        try {
            imgTexture.loadFromFile(Paths.get(filePath));
        } catch (IOException ex) {
            ex.printStackTrace( );
        }
        imgTexture.setSmooth(true);

        img = new Sprite(imgTexture);
        img.setOrigin(Vector2f.div(
                new Vector2f(imgTexture.getSize()), 2));
        img.setPosition(position);
    }

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

    @Override
    public void render() {
        Window.getInstance().getGameWindow().draw(img);
    }

    @Override
    public void update() {
    }
}
