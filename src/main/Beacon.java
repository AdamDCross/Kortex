package main;

import graphics.Animation;
import org.jsfml.system.Vector2f;

public class Beacon implements Render {
    private static Beacon instance = null;
    private boolean visible;
    private boolean destroyed;
    private Animation explosion;
    private Animation beacon;
    private int beaconHealth;
    private Vector2f position;

    private Beacon(){
        visible = false;
        destroyed = false;
        explosion = null;
        beaconHealth = 0;
        position = new Vector2f(0.0f,0.0f);
        beacon = null;

        //initialise animation when consulted with Phil
        //explosion = new Animation(...);
    }

    public static Beacon getInstance(){
        if( instance == null ){
            instance = new Beacon();
        }

        return instance;
    }

    public void spawn(boolean visible, int beaconHealth, Vector2f position){
        if( beacon == null ){
            beacon = new Animation("src/assets/Tiles.jpg",32,32,5,2,1000,position,3);
        }

        this.visible = visible;
        destroyed = false;
        this.beaconHealth = beaconHealth;
        this.position = position;
    }

    @Override
    public void update() {
        if(destroyed){
            //explosion.update();
        }
        else if(visible){
            beacon.update();
        }

        if(beaconHealth <= 0) {
            beaconHealth = 0;
            destroy();
        }
    }

    @Override
    public void render() {
        if(visible){
            beacon.render();
        }
        else if(destroyed){
            //explosion.render();
        }
    }

    public void takeDamage(int damageCount){
        beaconHealth -= damageCount;
    }

    public int getBeaconHealth(){
        return beaconHealth;
    }

    public Vector2f getPosition(){
        return position;
    }

    public boolean getIsDestroyed(){
        return destroyed;
    }

    public void toggleVisibility(){
        visible = !visible;
    }

    public void destroy(){
        visible = false;
        destroyed = true;
        position = new Vector2f(0.0f, 0.0f);
    }
}
