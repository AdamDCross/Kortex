package dijkstra;

import graphics.Tilemap;
import main.*;

import main.Window;
import org.jsfml.graphics.PrimitiveType;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Vertex;
import org.jsfml.system.*;

/**
 * Originally 'InterfaceTest' by Phil
 * Tweaked by Vince on 14/01/2016 by separating window functionality from core functionality.
 */
public class DijkstraTest implements Render {
    private Pathfind d;
    private Vector2i currentPosition;
    private RenderWindow window;
    private Tilemap tiles;

    public DijkstraTest() {
        tiles=new Tilemap("assets/Tiles.gif","assets/MapTest2.csv",32,32,5,2);
        d = new Pathfind(9,10,tiles);
        currentPosition = new Vector2i(0,0);
        this.window = Window.getInstance().getGameWindow();

        for (int cy = 0; cy < Pathfind.GRID_HEIGHT; cy++) {
            for(int cx = 0; cx< Pathfind.GRID_WIDTH; cx++) {
                System.out.print(d.getCells()[cx][cy].weight+"\t");
            }
            System.out.println(" ");
        }
        System.out.println("\n------------------------------------------\n");

        for (int cy = 0; cy < Pathfind.GRID_HEIGHT; cy++) {
            for(int cx = 0; cx< Pathfind.GRID_WIDTH; cx++) {
                System.out.print(d.getCells()[cx][cy].distance+"\t");
            }
            System.out.println(" ");
        }
    }

    public void updateCurrentMousePosition(Vector2i currentPosition) {
        //fixes the mouse coords when the window is increased/decreased, and clamps them into the current view
        Vector2f hold=Window.getInstance().getGameWindow().mapPixelToCoords(currentPosition);
        this.currentPosition = new Vector2i((int)GameMaths.clamp(hold.x,0,Pathfind.GRID_WIDTH*Pathfind.GRID_SIZE),
                (int) GameMaths.clamp(hold.y,0,Pathfind.GRID_HEIGHT*Pathfind.GRID_SIZE));
    }

    @Override
    public void render() {
        /*for(int i=0;i<Pathfind.GRID_WIDTH;i++){
            for(int j=0;j<Pathfind.GRID_HEIGHT;j++){
                d.getCells()[i][j].drawMe(window);
            }
        }*/
        tiles.drawMap();
        Cell start=d.getCells()[GameMaths.clamp(currentPosition.x/32,0,Pathfind.GRID_WIDTH-1)][GameMaths.clamp(currentPosition.y/32,0,Pathfind.GRID_HEIGHT-1)];
        while(start.pathNext!=null){
            window.draw(new Vertex[]{new Vertex(new Vector2f(start.pos.x*32+16,start.pos.y*32+16)),new Vertex(new Vector2f(start.pathNext.pos.x*32+16,start.pathNext.pos.y*32+16))}, PrimitiveType.LINE_STRIP);
            start=start.pathNext;
        }
    }

    @Override
    public void update() {
    }

    public Cell getCell(int x,int y){
        return d.getCells()[x][y];
    }
}
