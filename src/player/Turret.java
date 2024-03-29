package player;

import enemy.Enemy;
import enemy.NPCHandle;
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
    private int AOESize;
    private boolean shieldActive; //TODO: implement shield functionality
    private int shieldTimer;
    private int enemyKillCount;
    private long XPForEnemyKills;
    private int XPMultiplier;
    private long attackScore;
    private String ID;
    private Animation explosion;
    private long prevTime;
    private long currentTime;
    private long localElapsedTime;
    private Vector2f position;

    // Variables for shooting
    private long shotTime;
    private long rechargeTime;
    private Enemy target;
    private int att;
    private int def;
    private float range;
    private NPCHandle handle;
    private boolean active;



    // TODO: 08/02/2016 Shoot method. Shoot method needs to take into account what enemy it's shooting etc. so it can calculate the correct XP and score gained etc
    // TODO: Attack score is calculated from things like how long the turret has been hitting the enemy for etc.

    public Turret(String top, String bottom, boolean visible, int health, float angle, float rotationAngle, int rotationDelay, FloatRect dimensions,
                  int scrapCost, int xpRequirement, int range, int AOESize, boolean shieldActive, int shieldTimer,
                  String ID, String explosion, int width, int height, int row, int col, int delay,NPCHandle handle) {
        this.visible = visible;
        destroyed = false;
        this.health = 100;
        this.rotationDelay = rotationDelay;
        this.currentAngle = angle;
        this.rotationAngle = rotationAngle;

        position=new Vector2f(dimensions.left,dimensions.top);

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
        this.range = 300;
        this.AOESize = AOESize;
        this.shieldActive = shieldActive;
        this.shieldTimer = shieldTimer;
        this.enemyKillCount = 0;
        this.ID = ID;
        localElapsedTime = 0;
        this.explosion = new Animation(explosion, width, height, row, col, delay, new Vector2f(dimensions.left, dimensions.top), 1, dimensions, false);
        currentTime = 0;
        prevTime = 0;
        XPForEnemyKills = 0;
        attackScore = 0;
        XPMultiplier = 1;
<<<<<<< HEAD
        target=null;
        shotTime=0;
        rechargeTime=2000;
        att=20;
        def=10;
        range=64;
        this.handle=handle;
=======
        active = true;
    }

    public void setActive(boolean active){
        this.active = active;
        localElapsedTime = 0;
    }

    public void setDimensions(FloatRect dimensions){
        float x = 0.2f * (dimensions.left + dimensions.width);
        float y = 0.2f * (dimensions.top + dimensions.height);
        float w = 0.8f * dimensions.width;
        float h = 0.8f * dimensions.height;
        this.top.setDimensions(new FloatRect(x, y, w, h));
        this.top.setOriginCentre();
        this.top.setPositionOfImage(new Vector2f(dimensions.left + dimensions.width / 2, dimensions.top + dimensions.height / 2));

        this.bottom.setDimensions(dimensions);
>>>>>>> master
    }

    @Override
    public void update() {
        prevTime = currentTime;
        currentTime = Window.getInstance().getElapsedTime();

        localElapsedTime += currentTime - prevTime;

<<<<<<< HEAD
        if(destroyed){
            explosion.update();
        }
        else if(visible){
            if(Window.getInstance().getElapsedTime()>shotTime){
                //System.out.println("Explosion animation to add later.");
                if(handle.turretShoot(this)) {
                    shotTime = Window.getInstance().getElapsedTime() + shotTime;
                }
            }
            if( localElapsedTime >= rotationDelay ){
                localElapsedTime = 0;
                currentAngle += rotationAngle;
                top.setAngleOfImage(currentAngle);

                if(currentAngle >= (360.0f * 3.14 / 180)){
                    currentAngle = 0.0f;
=======
        if(active) {
            if (destroyed) {
                explosion.update();
            } else if (visible) {
                if (localElapsedTime >= rotationDelay) {
                    localElapsedTime = 0;
                    currentAngle += rotationAngle;
                    top.setAngleOfImage(currentAngle);

                    if (currentAngle >= (360.0f * 3.14 / 180)) {
                        currentAngle = 0.0f;
                    }
>>>>>>> master
                }

                top.update();
                bottom.update();
            }

            if (health <= 0) {
                health = 0;
                visible = false;
                destroyed = true;
            }
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

    public void resetAttackScore(){
        attackScore = 0;
    }

    public void resetXP(){
        XPForEnemyKills = 0;
    }

    public long getAttackScore(){
        return attackScore;
    }

    //TODO: XP multiplier needs to be implemented properly based on scrap invested into research
    public long getXPForEnemyKills(){
        return XPForEnemyKills * XPMultiplier;
    }

    public void setXPMultiplier(int value){
        XPMultiplier = value;
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

    public void repairTurret(int healthBoost){
        health += healthBoost;
    }

    public void toggleVisibility(){
        visible = !visible;
    }
<<<<<<< HEAD
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
=======

}
>>>>>>> master
