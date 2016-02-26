package enemy;

import dijkstra.Cell;
import dijkstra.Pathfind;
import main.Beacon;
import main.GameMaths;
import main.Render;
import main.Window;
import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.Color;
import org.jsfml.system.Vector2f;
import scrap.DropScrap;

public class Enemy implements Render{
        private Vector2f previous;
    private Vector2f heading;
    private Vector2f currentPosition;
    private Cell oldCell;
    private int health;
    private int maxHealth;

    //Time Holder
    //Deal with death

    private float lerpVal;//=distance/speed
    private float lerpCurr;

    private boolean isDead;
    private int attack;

    //temp code
    private int radius;
    CircleShape circle;


    public Enemy(Vector2f startPosition, Vector2f endingPosition, float speed){
        this.previous = startPosition;
        this.heading = endingPosition;
        this.currentPosition = previous;

        Vector2f direction = Vector2f.sub(this.heading, this.previous);
        float magnitude = (float)Math.sqrt((direction.x * direction.x) + (direction.y * direction.y));

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

    public Enemy(Cell start, float speed, int health, int att){


        //Extracts the translation
        float drawX=NPCHandle.getInstance().getHUD().getGameWindowRect().left;
        float drawY=NPCHandle.getInstance().getHUD().getGameWindowRect().top;
        //this is the width/height of the grid.
        float drawW=(NPCHandle.getInstance().getHUD().getGameWindowRect().width/ Pathfind.GRID_WIDTH);
        float drawH=(NPCHandle.getInstance().getHUD().getGameWindowRect().height/Pathfind.GRID_HEIGHT);

        oldCell=start;
        this.previous = new Vector2f((oldCell.pos.x+0.5f)*drawW+drawX, (oldCell.pos.y+0.5f)*drawH+drawY);
        this.heading = new Vector2f((oldCell.pathNext.pos.x+0.5f)*drawW+drawX,(oldCell.pathNext.pos.y+0.5f)*drawH+drawY);
        this.currentPosition = previous;

        Vector2f direction = Vector2f.sub(this.heading, this.previous);
        float magnitude = (float)Math.sqrt((direction.x * direction.x) + (direction.y * direction.y));
        //velocity = Vector2f.mul(direction,speed/magnitude);

        lerpCurr=0;
        lerpVal=speed/magnitude;

        //temp
        radius = 5+(int)(attack/100);
        circle = new CircleShape(radius);
        circle.setFillColor( new Color(Color.RED, 255) );
        circle.setOrigin(radius, radius);
        circle.setPosition(currentPosition);

        isDead=false;
        attack=att;

        this.health=health;
        maxHealth=health;

    }

    @Override
    public void update() {
        if(!isDead){
            if(lerpCurr>=1){
                //reached next point
                //TODO sort shit out here
                try {
                    //Extracts the translation
                    float drawX=NPCHandle.getInstance().getHUD().getGameWindowRect().left;
                    float drawY=NPCHandle.getInstance().getHUD().getGameWindowRect().top;
                    //this is the width/height of the grid.
                    float drawW=(NPCHandle.getInstance().getHUD().getGameWindowRect().width/ Pathfind.GRID_WIDTH);
                    float drawH=(NPCHandle.getInstance().getHUD().getGameWindowRect().height/Pathfind.GRID_HEIGHT);
                    oldCell = oldCell.pathNext;
                    this.previous = new Vector2f((oldCell.pos.x+0.5f)*drawW+drawX, (oldCell.pos.y+0.5f)*drawH+drawY);
                    this.heading = new Vector2f((oldCell.pathNext.pos.x+0.5f)*drawW+drawX,(oldCell.pathNext.pos.y+0.5f)*drawH+drawY);
                }catch(NullPointerException n){
                    //dead clause
                    isDead=true;
                    Beacon.getInstance().takeDamage(attack);

                }
                lerpCurr=0;
            }
            //System.out.println(lerpCurr);
            lerpCurr+=lerpVal;
            currentPosition=Vector2f.add(previous,Vector2f.mul(Vector2f.sub(heading,previous),lerpCurr));
            //System.out.println(oldCell.pos);
            circle.setPosition(currentPosition);
            int color=255*(int)(health/maxHealth);
            circle.setFillColor(new Color(255-color,color,0,255));
        }

    }

    @Override
    public void render() {
        //TODO add dead state
        if(!isDead) {
            main.Window.getInstance().getGameWindow().draw(circle);
        }
        else {

            //System.out.println("timer"+timer);
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


            //respawnTime= Window.getInstance().getElapsedTime()+ regenTime;
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