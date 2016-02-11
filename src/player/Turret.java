package player;

import graphics.Animation;
import graphics.Image;
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
    private Image top;
    private Image bottom;
    private Vector2f position;
    private int scrapCost;
    private int xpRequirement;
    private int range;
    private int AOESize;
    private int shieldActive;
    private int shieldTimer;

    // TODO: 08/02/2016 Shoot method

    private Turret() {
        visible = false;
        destroyed = false;
        health = 0;
        rotationSpeed = 0.0f;
        angle = 0.0f;
        speed = 0.0f;
        top = null;
        bottom = null;
        position = new Vector2f(0.0f,0.0f);
    }

    public void spawn(boolean visible, int health , Image top, Image bottom, Vector2f position){
        this.top = top;

        // TODO: 08/02/2016 Change this to a static image rather than an animation? 
        this.bottom = bottom;
    }
    
    @Override
    public void update() {
        if(destroyed){
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

    public void rotate(float angle){


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
        } else {
            visible = visible;
        }

        // Print to say that turret is visible / not visible.
        System.out.println("Turret visible: " + visible);

    }
}
