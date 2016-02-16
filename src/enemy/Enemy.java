package enemy;

import main.Cell;
import main.GameMaths;
import main.Render;
import main.Window;
import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;
import states.Game;

public class Enemy implements Render{
    //private Vector2f previous;
    //private Vector2f heading;
    private Vector2f start;
    private Vector2f previous;
    private Vector2f heading;
    private Vector2f currentPosition;
    private Vector2f velocity;
    private Cell oldCell;
    private float speed;
    private int health;

    //Time Holder
    //Deal with death
    private long regenTime;
    private long respawnTime;
    //
    private long shootTime;
    private long reloadTime;

    private float lerpVal;//=distance/speed
    private float lerpCurr;

    private boolean isDead;

    //temp code
    private int radius;
    CircleShape circle;

    public Enemy(Vector2f startPosition, Vector2f endingPosition, float speed){
        this.previous = startPosition;
        this.heading = endingPosition;
        this.currentPosition = previous;

        Vector2f direction = Vector2f.sub(this.heading, this.previous);
        float magnitude = (float)Math.sqrt((direction.x * direction.x) + (direction.y * direction.y));
        //velocity = Vector2f.mul(direction,speed/magnitude);

        lerpCurr=0;
        lerpVal=speed/magnitude;

        //temp
        radius = 5;
        circle = new CircleShape(radius);
        circle.setFillColor( new Color(Color.RED, 255) );
        circle.setOrigin(radius, radius);
        circle.setPosition(currentPosition);

        isDead=false;
    }

    public Enemy(Cell start, float speed){
        this.previous = new Vector2f(start.pos.x*32+16,start.pos.y*32+16);
        this.heading = new Vector2f(start.pathNext.pos.x*32+16,start.pathNext.pos.y*32+16);
        this.currentPosition = previous;

        oldCell=start;

        Vector2f direction = Vector2f.sub(this.heading, this.previous);
        float magnitude = (float)Math.sqrt((direction.x * direction.x) + (direction.y * direction.y));
        //velocity = Vector2f.mul(direction,speed/magnitude);

        lerpCurr=0;
        lerpVal=speed/magnitude;

        //temp
        radius = 5;
        circle = new CircleShape(radius);
        circle.setFillColor( new Color(Color.RED, 255) );
        circle.setOrigin(radius, radius);
        circle.setPosition(currentPosition);

        isDead=false;
    }

    @Override
    public void update() {
        if(!isDead){
            if(lerpCurr>=1){
                //reached next point
                //TODO sort shit out here
                try {
                    oldCell = oldCell.pathNext;
                    this.previous = new Vector2f(oldCell.pos.x * 32 + 16, oldCell.pos.y * 32 + 16);
                    this.heading = new Vector2f(oldCell.pathNext.pos.x * 32 + 16, oldCell.pathNext.pos.y * 32 + 16);
                }catch(NullPointerException n){
                    //dead
                    isDead=true;
                }
                lerpCurr=0;
            }
            System.out.println(lerpCurr);
            lerpCurr+=lerpVal;
            currentPosition=Vector2f.add(previous,Vector2f.mul(Vector2f.sub(heading,previous),lerpCurr));
            System.out.println(currentPosition);
            circle.setPosition(currentPosition);
        }else {
            //check for regen, then respawn.
            if(Window.getInstance().getElapsedTime()>respawnTime){
                spawn();
            }
        }
    }

    @Override
    public void render() {
        //TODO add dead state
        if(!isDead) {
            main.Window.getInstance().getGameWindow().draw(circle);
        }
    }

    /*
    public boolean isInCollision() {
        return false;
    }*/

    public void spawn() {
        //todo add spawning
    }

    /*public void spawn(Vector2f pos) {
    }*/


    public boolean damage(int damage){
        health-=damage;
        if(health<=0){
            isDead=true;
            respawnTime= Window.getInstance().getElapsedTime()+ regenTime;
        }
        return isDead;
    }

    public boolean getState(){
        return isDead;
    }

    public Vector2f getPos(){
        return currentPosition;
    }


}