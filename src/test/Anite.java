package test;

import graphics.Animation;
import main.Render;
import org.jsfml.system.Vector2f;

/**
 * Phil's animation test thing (we assume)
 */
public class Anite implements Render {

    Animation a;

    public Anite(){
        a=new Animation("src/assets/Tiles.png",32,32,5,2,100,new Vector2f(320,320),3, false);
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
