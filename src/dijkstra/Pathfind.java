package dijkstra;

import graphics.Tilemap;
import org.jsfml.system.Vector2i;

/**
 * Created by Phillip on 05/12/2015.
 */

public class Pathfind {
    public static final int GRID_SIZE=32;
    public static final int MAX_TILES_X = 20;
    public static final int MAX_TILES_Y = 15;
    public static final int GRID_WIDTH = MAX_TILES_X;
    public static final int GRID_HEIGHT = MAX_TILES_Y;
    private Cell[][] cells;
    private static Pathfind instance=null;


    private Pathfind(){
        //TODO when the main playing field is created, merge these classes, to reduce the amount of wasted variables;
        //BASIC SETUP
        cells=new Cell[GRID_WIDTH][GRID_HEIGHT];
        for(int cx=0;cx<GRID_WIDTH;cx++){
            for(int cy=0;cy<GRID_HEIGHT;cy++){
                getCells()[cx][cy]=new Cell(new Vector2i(cx,cy),((int)(Math.random()*5)==1)?5:1);
            }
        }
        dijkstra(GRID_WIDTH/2,GRID_HEIGHT/2);
    }

    public static Pathfind getInstance(){
        if(instance==null){
            instance=new Pathfind();
        }
        return instance;
    }

    public void setWeightMap(int[][] map){
        for(int cx=0;cx<GRID_WIDTH;cx++){
            for(int cy=0;cy<GRID_HEIGHT;cy++){
                getCells()[cx][cy].weight=map[cx][cy];
            }
        }
    }
    public void setWeightMapFromTiles(Tilemap t){
        for(int cx=0;cx<GRID_WIDTH;cx++){
            for(int cy=0;cy<GRID_HEIGHT;cy++){
                cells[cx][cy]= new Cell(new Vector2i(cx,cy), (t.getMap().get(cy*GRID_WIDTH+cx)!=2) ? 10:1 );
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    /**
     * A Dijkstra's Algorithm implementation in java
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
            }
        }
    }
}




