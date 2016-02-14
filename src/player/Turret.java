package player;

import graphics.Animation;
import graphics.Image;
import main.Button;
import main.Render;
import main.Window;
import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;

import java.util.Vector;

/**
 * Turret class for representing turrets.
 */
public class Turret implements Render {
    private boolean visible;
    private boolean destroyed;
    private int health;
    private int rotationDelay;
    private float currentAngle;
    private float rotationAngle;
    private Button top;
    private Button bottom;
    private int scrapCost;
    private int xpRequirement;
    private int range;
    private int AOESize;
    private boolean shieldActive; //TODO: implement shield functionality
    private int shieldTimer;
    private int enemyKillCount;
    private String ID;
    private Animation explosion;
    private long prevTime;
    private long currentTime;
    private long localElapsedTime;

    // TODO: 08/02/2016 Shoot method.

    public Turret(String top, String bottom, boolean visible, int health, float angle, float rotationAngle, int rotationDelay, FloatRect dimensions,
                   int scrapCost, int xpRequirement, int range, int AOESize, boolean shieldActive, int shieldTimer,
                   String ID, String explosion, int width, int height, int row, int col, int delay) {
        this.visible = visible;
        destroyed = false;
        this.health = health;
        this.rotationDelay = rotationDelay;
        this.currentAngle = angle;
        this.rotationAngle = rotationAngle;

        float x = 0.2f * (dimensions.left + dimensions.width);
        float y = 0.2f * (dimensions.top + dimensions.height);
        float w = 0.8f * dimensions.width;
        float h = 0.8f * dimensions.height;
        this.top = new Button(top, new FloatRect(x, y, w, h), ID + " TOP", false);
        this.top.setOriginCentre();
        this.top.setAngleOfImage(angle);
        this.top.setPositionOfImage(new Vector2f(dimensions.left + dimensions.width / 2, dimensions.top + dimensions.height / 2));

        this.bottom = new Button(bottom, dimensions, ID + " BOTTOM", false);
        this.scrapCost = scrapCost;
        this.xpRequirement = xpRequirement;
        this.range = range;
        this.AOESize = AOESize;
        this.shieldActive = shieldActive;
        this.shieldTimer = shieldTimer;
        this.enemyKillCount = 0;
        this.ID = ID;
        localElapsedTime = 0;
        this.explosion = new Animation(explosion, width, height, row, col, delay, new Vector2f(dimensions.left, dimensions.top), 0, dimensions, false);
        currentTime = 0;
        prevTime = 0;
    }
    
    @Override
    public void update() {
        prevTime = currentTime;
        currentTime = Window.getInstance().getElapsedTime();

        localElapsedTime += currentTime - prevTime;

        if(destroyed){
            explosion.update();
        }
        else if(visible){
            if( localElapsedTime >= rotationDelay ){
                localElapsedTime = 0;
                currentAngle += rotationAngle;
                top.setAngleOfImage(currentAngle);

                if(currentAngle >= (360.0f * 3.14 / 180)){
                    currentAngle = 0.0f;
                }
            }

            top.update();
            bottom.update();
        }

        if(health <= 0) {
            health = 0;
            visible = false;
            destroyed = true;
        }
    }

    @Override
    public void render() {
        if(visible){
            bottom.render();
            top.render();

        }
        else if(destroyed){
            explosion.render();
        }
    }

    public String getID(){
        return ID;
    }

    public int getEnemyKillCount(){
        return enemyKillCount;
    }

    public void setShieldTimer(int shieldTimer){
        this.shieldTimer = shieldTimer;
    }

    public void setAOESize(int AOESize){
        this.AOESize = AOESize;
    }

    public int getAOESize(){
        return AOESize;
    }

    public void setRange(int range){
        this.range = range;
    }

    public int getRange(){
        return range;
    }

    public void setXPRequirement(int requirement){
        xpRequirement = requirement;
    }

    public int getXPRequirement(){
        return xpRequirement;
    }

    public void setScrapCost(int scrapCost){
        this.scrapCost = scrapCost;
    }

    public int getScrapCost(){
        return scrapCost;
    }

    public void setRotationDelay(int delay){
        rotationDelay = delay;
    }

    public int getTurretHealth(){
        return health;
    }

    public void takeDamage(int damageCount){
        health -= damageCount;
    }

    public void toggleVisibility(){
        visible = !visible;
    }
}
