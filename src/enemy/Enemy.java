package enemy;

import main.Render;
import org.jsfml.system.Vector2f;

/**
 * Abstract Enemy interface class which contains the basic enemy
 * functionality every enemy type needs to have.
 */
public interface Enemy extends Render {
    @Override
    void render();

    @Override
    void update();

    boolean isInCollision();

    void spawn();

    void spawn(Vector2f pos);

    void kill();

    void kill(float delayBeforeReSpawn);
}
