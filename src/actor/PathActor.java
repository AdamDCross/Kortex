package actor;

import main.Render;
import org.jsfml.system.Vector2i;

/**
 * An extention for path following Actors
 */
public class PathActor extends Actor {
    PathActor(Vector2i vel, Vector2i pos, int dir, Render img) {
        super(vel, pos, dir, img);
    }
    @Override
    public void update(){
        /*
        Follows path until hits next part. calc new velocity, then call super for main update
        */

        super.update();
    }
}
