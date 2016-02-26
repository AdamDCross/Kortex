package player;

import enemy.Enemy;
import enemy.NPCHandle;
import graphics.Animation;
import graphics.Image;
import main.Button;
import main.Line;
import main.Render;
import main.Window;
import org.jsfml.graphics.Color;
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
    private boolean active;
    private FloatRect dimensions;

    private long shotTime;
    private long rechargeTime;
    private Enemy target;
    private int att;
    private int def;
    private float range;
    private Vector2f position;

    private String topFilePath;
    private String bottomFilePath;

    // TODO: 08/02/2016 Shoot method. Shoot method needs to take into account what enemy it's shooting etc. so it can calculate the correct XP and score gained etc
    //TODO: Atack score is calculated from things like how long the turret has been hitting the enemy for etc.
    public Turret copy() {

        try {
            return (Turret) clone();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    public Turret(String top, String bottom, boolean visible, int health, float angle, float rotationAngle, int rotationDelay, FloatRect dimensions,
                  int scrapCost, int xpRequirement, int range, int AOESize, boolean shieldActive, int shieldTimer,
                  String ID, String explosion, int width, int height, int row, int col, int delay) {
        this.visible = visible;
        destroyed = false;
        this.health = health;
        this.rotationDelay = rotationDelay;
        this.currentAngle = angle;
        this.rotationAngle = rotationAngle;

        this.topFilePath = top;
        this.bottomFilePath = bottom;
        this.dimensions=dimensions;
        float x = 0.2f * (dimensions.left + dimensions.width);
        float y = 0.2f * (dimensions.top + dimensions.height);
        float w = 0.8f * dimensions.width;
        float h = 0.8f * dimensions.height;
        this.top = new Button(top, new FloatRect(x, y, w, h), ID + " TOP", false);
        this.top.setOriginCentre();
        this.top.setAngleOfImage(angle);
        this.top.setPositionOfImage(new Vector2f(dimensions.left + dimensions.width / 2, dimensions.top + dimensions.height / 2));
        this.top.setColourMask(Color.WHITE);

        this.bottom = new Button(bottom, dimensions, ID + " BOTTOM", false);
        this.scrapCost = scrapCost;
        this.xpRequirement = xpRequirement;
        this.range = range;
        this.AOESize = AOESize;
        this.shieldActive = shieldActive;
        this.enemyKillCount = 0;
        this.ID = ID;
        localElapsedTime = 0;
        this.explosion = new Animation(explosion, width, height, row, col, delay, new Vector2f(dimensions.left, dimensions.top), 1, dimensions, false);
        currentTime = 0;
        prevTime = 0;
        XPForEnemyKills = 0;
        attackScore = 0;
        XPMultiplier = 1;
        active = true;
        target=null;
        shotTime=0;
        rechargeTime=500;
        att=10;
        def=10;
        this.range=100;
        position=new Vector2f(dimensions.left,dimensions.top);
    }

    public Turret(Turret otherTurret){
        this.visible = otherTurret.getVisible();
        destroyed = otherTurret.getIsDestroyed();
        this.health = otherTurret.getTurretHealth();
        this.rotationDelay = otherTurret.getRotationDelay();
        this.currentAngle = otherTurret.getCurrentAngle();
        this.rotationAngle = otherTurret.getRotationAngle();

        this.dimensions= otherTurret.getDimensions();
        float x = 0.2f * (this.dimensions.left + this.dimensions.width);
        float y = 0.2f * (this.dimensions.top + this.dimensions.height);
        float w = 0.8f * this.dimensions.width;
        float h = 0.8f * this.dimensions.height;
        this.top = new Button(otherTurret.getTopFilePath(), new FloatRect(x, y, w, h), ID + " TOP", false);
        this.top.setOriginCentre();
        this.top.setAngleOfImage(this.currentAngle);
        this.top.setPositionOfImage(new Vector2f(this.dimensions.left + this.dimensions.width / 2, this.dimensions.top + this.dimensions.height / 2));
        this.top.setColourMask(Color.WHITE);

        this.bottom = new Button(otherTurret.getBottomFilePath(), this.dimensions, ID + " BOTTOM", false);
        this.scrapCost = otherTurret.getScrapCost();
        this.xpRequirement = otherTurret.getXPRequirement();
        this.range = otherTurret.getRange();
        this.AOESize = otherTurret.getAOESize();
        this.shieldActive = otherTurret.getIsShieldActive();

        this.enemyKillCount = 0;
        this.ID = otherTurret.getID();
        localElapsedTime = 0;

        //get animation object here
        Animation temp = otherTurret.getExplosionObj();
        this.explosion = new Animation(temp.getFilePath(), temp.getWidth(), temp.getHeight(),
                temp.getRow(), temp.getCol(), temp.getDelay(), new Vector2f(this.dimensions.left, this.dimensions.top), 1, this.dimensions, false);

        currentTime = 0;
        prevTime = 0;
        XPForEnemyKills = 0;
        attackScore = 0;
        XPMultiplier = 1;
        active = true;
        target=null;
        shotTime=0;
        rechargeTime=500;
        att=50;
        def=10;
        this.range=100;
        position=new Vector2f(this.dimensions.left,this.dimensions.top);
    }

    public Animation getExplosionObj(){
        return explosion;
    }

    public boolean getIsShieldActive(){
        return  shieldActive;
    }

    public String getBottomFilePath(){
        return bottomFilePath;
    }

    public String getTopFilePath(){
        return topFilePath;
    }

    public FloatRect getDimensions(){
        return dimensions;
    }

    public float getRotationAngle(){
        return rotationAngle;
    }

    public float getCurrentAngle(){
        return currentAngle;
    }

    public int getRotationDelay(){
        return rotationDelay;
    }

    public boolean getIsDestroyed(){
        return destroyed;
    }

    public boolean getVisible(){
        return visible;
    }

    public void setActive(boolean active){
        this.active = active;
        System.out.println(position);
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
        position=new Vector2f(dimensions.left,dimensions.top);
    }

    @Override
    public void update() {
        prevTime = currentTime;
        currentTime = Window.getInstance().getElapsedTime();

        localElapsedTime += currentTime - prevTime;

        if(active) {
            if (destroyed) {
                explosion.update();
            } else if (visible) {
                if(currentTime>shotTime){
                    if(NPCHandle.getInstance().turretShoot(this)) {
                        shotTime = Window.getInstance().getElapsedTime() + rechargeTime;
                    }
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
            try{
                Line.drawLine(Vector2f.add(position,new Vector2f(bottom.getDimensions().width/2,bottom.getDimensions().height/2)),target.getPos(),Color.RED);
            }catch(Exception e){
                //no enemy, avoid the draw
            }

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