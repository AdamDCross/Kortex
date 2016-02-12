package player;

import enemy.Enemy;
import graphics.Animation;
import main.Render;
import org.jsfml.system.Vector2f;

import java.util.Vector;

/**
 * Turret class for representing turrets.
 */
public class Turret implements Render {
    private boolean visible;
    private boolean destroyed;
    private int health;
    private float rotationSpeed;
    private float angle;
    private float speed;
    private Animation top;
    private Animation bottom;
    private Vector2f position;

    // TODO: 08/02/2016 Shoot method

    //variables for shooting

    private long shotTime;
    private long rechargeTime;
    private Enemy target;
    private int att;
    private int def;
    private float range;

    public Turret(Vector2f pos) {
        visible = false;
        destroyed = false;
        health = 0;
        rotationSpeed = 0.0f;
        angle = 0.0f;
        speed = 0.0f;
        top = null;
        bottom = null;
        position = new Vector2f(0.0f,0.0f);
        target=null;
        shotTime=0;
        rechargeTime=1000;
        att=10;
        def=10;
        range=200;
    }

    public void spawn(boolean visible, int health , Animation top, Animation bottom, Vector2f position){
        top = new Animation("src/assets/Tiles.jpg",32,32,5,2,1000,position,3);

        // TODO: 08/02/2016 Change this to a static image rather than an animation?
        bottom = new Animation("src/assets/Tiles.jpg",32,32,5,2,1000,position,3);
    }

    @Override
    public void update() {
        if(destroyed){
            //TODO 9/2/16 add explosion
            System.out.println("Explosion animation to add later.");
            //explosion.update();
        }
        else if(visible){
            top.update();
            bottom.update();
        }

        if(health <= 0) {
            health = 0;
            destroy();
        }
    }

    @Override
    public void render() {
        if(visible){
            top.render();
            bottom.render();
        }
        else if(destroyed){
            System.out.println("Boom!");
            //explosion.render();
        }
    }

    public void takeDamage(int damageCount){
        health -= damageCount;
    }

    public void destroy(){
        visible = false;
        destroyed = true;
        position = new Vector2f(0.0f, 0.0f);
    }

    public void toggleVisibility(){
        if (visible){
            visible = !visible;
            System.out.println("Turret hidden");
        }
        // Print to say that turret is visible / not visible.
        System.out.println("Turret visible: " + visible);

    }

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;

    }

    public Vector2f getPos(){
        return position;
    }
    public float getRange(){
        return range;
    }

    public int getAtt() {
        return att;
    }
}
