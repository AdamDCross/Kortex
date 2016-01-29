package main;

import dijkstra.DijkstraQueue;
import org.jsfml.system.Vector2i;

import java.util.ArrayList;

/**
 * Contains the functions for pathfinding.
 */
public class Pathfind {
    public static final int GRID_SIZE=32;
    public static final int GRID_WIDTH=Window.getInstance().getScreenWidth()/GRID_SIZE;
    public static final int GRID_HEIGHT=Window.getInstance().getScreenHeight()/GRID_SIZE;
    private Cell[][] cells;

    /**
     * A Pathfind's Algorithm implementation in java
     *
     * @param cx The center cell's X position
     * @param cy The center cell's Y position
     */
    void dijkstra(int cx,int cy) {
        DijkstraQueue hold = new DijkstraQueue();
        for (int x = 0; x < GRID_WIDTH; x++) {
            for (int y = 0; y < GRID_HEIGHT; y++) {
                getCells()[x][y].dReset();
                hold.insert(getCells()[x][y]);
            }
        }
        hold.decrease(getCells()[cx][cy],0);
        getCells()[cx][cy].pathNext = null;
        while(hold.start!=null) {
            Cell currCheck = hold.getMin();
            int dist = currCheck.distance + currCheck.weight;
            int x = currCheck.pos.x;
            int y = currCheck.pos.y;
            //path(currCheck);
            if (currCheck.pos.y != 0) {
                //up
                if (getCells()[x][y - 1].distance > dist) {
                    hold.decrease(getCells()[x][y - 1],dist);
                    getCells()[x][y - 1].pathNext = currCheck;
                }
            }
            if (currCheck.pos.x != 0) {
                //left
                if (getCells()[x - 1][y].distance > dist) {
                    hold.decrease(getCells()[x - 1][y],dist);
                    getCells()[x - 1][y].pathNext = currCheck;
                }
                //Uncomment for diagonals
                /*if (currCheck.pos.y != 0) {
                    //up
                    if (cells[x-1][y - 1].distance > dist) {
                        hold.decrease(cells[x-1][y - 1],dist);
                        cells[x-1][y - 1].pathNext = currCheck;
                    }
                }
                if (currCheck.pos.y != (GRID_HEIGHT-1)) {
                    //down
                    if (cells[x-1][y + 1].distance > dist) {
                        hold.decrease(cells[x-1][y + 1],dist);
                        cells[x-1][y +1].pathNext = currCheck;
                    }
                }*/
            }
            if (currCheck.pos.y != (GRID_HEIGHT-1)) {
                //down
                if (getCells()[x][y + 1].distance > dist) {
                    hold.decrease(getCells()[x][y + 1],dist);
                    getCells()[x][y +1].pathNext = currCheck;
                }
            }
            if (currCheck.pos.x != (GRID_WIDTH-1)) {
                //right
                if (getCells()[x + 1][y].distance > dist) {
                    hold.decrease(getCells()[x + 1][y],dist);
                    getCells()[x + 1][y].pathNext = currCheck;
                }
                //Uncomment for diagonals
                /*if (currCheck.pos.y != 0) {
                    //up
                    if (cells[x+1][y - 1].distance > dist) {
                        hold.decrease(cells[x+1][y - 1],dist);
                        cells[x+1][y - 1].pathNext = currCheck;
                    }
                }
                if (currCheck.pos.y != (GRID_HEIGHT-1)) {
                    //down
                    if (cells[x+1][y + 1].distance > dist) {
                        hold.decrease(cells[x+1][y + 1],dist);
                        cells[x+1][y +1].pathNext = currCheck;
                    }
                }*/
            }
        }
    }

    void path(Cell start){
        Cell checkPoint = start;
        Cell currentPoint = start.pathNext;
        if(currentPoint==null){
            return;
        }
        while (currentPoint.pathNext != null) {
            //Make the walkable
            if (walkable(checkPoint,currentPoint.pathNext)) {
                currentPoint = currentPoint.pathNext;
                checkPoint.pathNext=currentPoint;
            } else {
                checkPoint = currentPoint;
                currentPoint = currentPoint.pathNext;
            }
        }
    }

    ArrayList<Cell> linePoints(Cell a,Cell b){
        ArrayList<Cell> hold = new ArrayList<Cell>();
        int x0=a.pos.x,x1=b.pos.x,y0=a.pos.y,y1=b.pos.y;
        if(x1>x0){
            int temp=x0;
            x0=x1;
            x1=temp;
        }
        if(y1>y0){
            int temp=x0;
            x0=x1;
            x1=temp;
        }
        int dx=x1-x0;
        int dy=y1-y0;

        //check vertical (as dy/dx=infinity when dx=0)
        if(dx==0){
            for(int i=y0;i<=y1;i+=dy/Math.abs(dy)){
                hold.add(getCells()[x0][i]);
            }
            return hold;
        }
        //sorted out the nasty stuff
        int err=0;
        int y=y0;
        for(int x=x0;x<=x1;x++){
            hold.add(getCells()[x][y]);
            if((err+dy)<<1<dx){
                err+=dy;
            }else {
                y++;
                err += (dy - dx);
            }
        }
        return hold;
    }

    boolean walkable(Cell a,Cell b){
        for (Cell c:linePoints(a,b)) {
            if(c.weight!=1){
                return false;
            }
        }
        return true;
    }

    public Pathfind(int Cx, int Cy){
        //TODO when the main playing field is created, merge these classes, to reduce the amount of wasted variables;
        cells=new Cell[GRID_WIDTH][GRID_HEIGHT];
        for(int cx=0;cx<GRID_WIDTH;cx++){
            for(int cy=0;cy<GRID_HEIGHT;cy++){
                getCells()[cx][cy]=new Cell(new Vector2i(cx,cy),((int)(Math.random()*5)==1)?5:1);
            }
        }
        dijkstra(Cx,Cy);
    }


    public Cell[][] getCells() {
        return cells;
    }
}






