package Test;

import graphics.Animation;
import main.Render;
import org.jsfml.system.Vector2f;

/**
 * Created by Phillip on 29/01/2016.
 */
public class Anite implements Render {

    Animation a;

    public Anite(){
        a=new Animation("assets/Tiles.gif",32,32,5,2,100,new Vector2f(320,320),3);
    }
    @Override
    public void render() {
        a.render();
    }

    @Override
    public void update() {
        a.update();
    }
}
