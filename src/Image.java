import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Vector;

/**
 * Created by Vince on 14/01/2016.
 */
public class Image implements Render {
    Texture imgTexture;
    Sprite img;

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

    @Override
    public void render() {
        Window.getInstance().getGameWindow().draw(img);
    }

    @Override
    public void update() {
    }
}
