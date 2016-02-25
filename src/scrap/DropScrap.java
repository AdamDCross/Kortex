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

 public DropScrap(Vector2f position){

        enemyLocationX = position.x;
        enemyLocationY = position.y;
        dropScrap = randomGenerator.nextInt(2);
        if(dropScrap == 1){
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

    public Button isClicked(){
        return scrap;
    }

    public int getTimer(){
        return timer;
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
        sizeX -= 0.1;
        sizeY -= 0.1;
    }
}
