package scrap;

/**
 * Created by iiisiv on 23/02/16.
 */
import graphics.Image;
import main.Button;
import main.Render;
import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;

import java.util.Random;
import java.util.Vector;

public class DropScrap implements  Render{
    private Random randomGenerator = new Random();
    private Button scrap;
    FloatRect dimensions;
    float enemyLocationX;
    float enemyLocationY;
    int sizeX = 30;
    int sizeY = 30;
    int dropScrap;
    int timer;
    boolean active = false;
    boolean viewToggle;



 public DropScrap(Vector2f position){

        enemyLocationX = position.x;
        enemyLocationY = position.y;
        dropScrap = randomGenerator.nextInt(10);
        if(dropScrap == 1){
            active = true;
            int dropLocation = randomGenerator.nextInt(4);
            drawButton(dropLocation);

       }

    }
   private void drawButton(int dropLocation){
       float posX = enemyLocationX;
       float posY = enemyLocationY;



       scrap = new Button("src/assets/scrap.png",
               new FloatRect( posX,posY,sizeX,sizeY),"", false);




   }


    public void updateEnemyPos(Vector2f pos){
        enemyLocationX = pos.x;
        enemyLocationY = pos.y;

    }
    public  boolean isActive(){
        return active;
    }

    public Button isClicked(){
        return scrap;
    }

    public int getTimer(){
        return timer;
    }
    public void clickFeedBack(){
        Image i = new Image("src/assets/scrap2.png",new Vector2f(enemyLocationX,enemyLocationY));
        scrap.setImage(i);
        timer = 900;
    }


    @Override
    public void render() {

        if(dropScrap == 1) {
            scrap.render();

        }

    }


    @Override
    public void update() {

        timer ++;

    }
}
