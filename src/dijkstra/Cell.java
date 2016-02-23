package dijkstra;

import main.Window;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.*;



public class Cell{
    public int distance;
    public int weight;
    public Cell pathNext;//This is mainly what you need.
    public Vector2i pos;// this gives a vector based off of position

    /**
     * The Basic Cell Structure. This is used to handle the paths taken by opponents
     *
     * @param pos The position vector of the cell
     * @param weight The weight of the cell. Used in the Pathfind's Algorithm.
     *               The higher the value, the harder it is to move through
     */

    public Cell(Vector2i pos, int weight) {
        distance = Integer.MAX_VALUE;
        this.weight = weight;
        this.pos = pos;
        pathNext=null;
    }

    //resets the variabhles for stuff

    void dReset(){
        distance = Integer.MAX_VALUE;
        pathNext=null;
    }

    //debug drawing

    public void drawMe(){
        if(weight==1){return;}
        RectangleShape r=new RectangleShape(new Vector2f(32,32));
        r.setFillColor(Color.BLUE);
        r.setPosition(pos.x*32,pos.y*32);
        Window.getInstance().getGameWindow().draw(r);
    }
}
