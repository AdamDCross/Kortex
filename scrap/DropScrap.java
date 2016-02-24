package Scrap;

/**
 * Created by iiisiv on 23/02/16.
 */
import graphics.Image;
import main.Button;
import main.Render;
import org.jsfml.graphics.FloatRect;
import org.jsfml.system.Vector2f;

import java.util.Random;
import java.util.Vector;

public class DropScrap implements  Render{
    private Random randomGenerator = new Random();
    private Button scrap;
    FloatRect dimensions;
    float enemyLocationX;
    float enemyLocationY;
    int sizeX = 10;
    int sizeY = 10;
    int dropScrap;

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

       if(dropLocation == 0)posX++;
       if(dropLocation == 1) posY++;
       if(dropLocation == 2 & posX != 0) posX--;
       if(dropLocation == 3 & posY != 0) posY--;

       scrap = new Button("src/assets/scrap.jpg",
               new FloatRect( posX,posY,sizeX,sizeY),"", true);



   }


    public void updateEnemyPos(Vector2f pos){
        enemyLocationX = pos.x;
        enemyLocationY = pos.y;
    }

    @Override
    public void render() {

        if(dropScrap == 1) scrap.render();





    }

    @Override
    public void update() {

    }
}
