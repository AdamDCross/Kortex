package main;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.*;

//Also doubling this as the cell class for the Heap

public class Cell{
    public int distance;
    public int weight;
    public Cell pathNext;
    public Vector2i pos;

    /**
     * The Basic main.Cell Structure
     *
     * @param pos The position vector of the cell
     * @param weight The weight of the cell. Used in the main.Pathfind's Algorithm.
     *               The higher the value, the harder it is to move through
     */

    Cell(Vector2i pos, int weight) {
        distance = Integer.MAX_VALUE;
        this.weight = weight;
        this.pos = pos;
        pathNext=null;
    }

    void dReset(){
        distance = Integer.MAX_VALUE;
        pathNext=null;
    }

    int manhattanDistance(Cell other) {
        return Math.abs(pos.x - other.pos.x) + Math.abs(pos.y - other.pos.y);
    }

    public void drawMe(RenderWindow w){
        if(weight==1){return;}
        RectangleShape r=new RectangleShape(new Vector2f(32,32));
        r.setFillColor(Color.BLUE);
        r.setPosition(pos.x*32,pos.y*32);
        w.draw(r);
    }
}
