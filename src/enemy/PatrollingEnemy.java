package enemy;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;

/**
 * Created by vincentdealmeida on 21/01/2016.
 */
public class PatrollingEnemy implements Enemy {
    private Vector2f startingPosition;
    private Vector2f endingPosition;
    private Vector2f currentPosition;
    private Vector2f velocity;

    //temp code
    private int radius;
    CircleShape circle;

    public PatrollingEnemy(Vector2f startPosition, Vector2f endingPosition, float speed){
        this.startingPosition = startPosition;
        this.endingPosition = endingPosition;
        this.currentPosition = startingPosition;

        Vector2f direction = Vector2f.sub(this.endingPosition, this.startingPosition);
        float magnitude = (float)Math.sqrt((direction.x * direction.x) + (direction.y * direction.y));
        velocity = Vector2f.mul(direction,speed/magnitude);

        //temp
        radius = 5;
        circle = new CircleShape(radius);
        circle.setFillColor( new Color(Color.RED, 255) );
        circle.setOrigin(radius, radius);
        circle.setPosition(currentPosition);
    }

    @Override
    public void update() {
        currentPosition = Vector2f.add(currentPosition, velocity);
        circle.setPosition(currentPosition);

        if( currentPosition.x > endingPosition.x
                || currentPosition.y > endingPosition.y){
            velocity = Vector2f.neg(velocity);
        }
        else if( currentPosition.x < startingPosition.x
                || currentPosition.y < startingPosition.y ){
            velocity = Vector2f.neg(velocity);
        }
    }

    @Override
    public void render() {
        main.Window.getInstance().getGameWindow().draw(circle);
    }

    @Override
    public boolean isInCollision() {
        return false;
    }

    @Override
    public void spawn() {

    }

    @Override
    public void spawn(Vector2f pos) {

    }

    @Override
    public void kill() {

    }

    @Override
    public void kill(float delayBeforeReSpawn) {

    }
}
