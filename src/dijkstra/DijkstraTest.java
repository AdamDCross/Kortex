package dijkstra;

import main.*;

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

    public DijkstraTest() {
        d = new Pathfind(9,10);
        currentPosition = new Vector2i(0,0);
        this.window = Window.getInstance().getGameWindow();

        for (int cy = 0; cy < Pathfind.GRID_HEIGHT; cy++) {
            for(int cx = 0; cx< Pathfind.GRID_WIDTH; cx++) {
                System.out.print(d.cells[cx][cy].weight+"\t");
            }
            System.out.println(" ");
        }
        System.out.println("\n------------------------------------------\n");

        for (int cy = 0; cy < Pathfind.GRID_HEIGHT; cy++) {
            for(int cx = 0; cx< Pathfind.GRID_WIDTH; cx++) {
                System.out.print(d.cells[cx][cy].distance+"\t");
            }
            System.out.println(" ");
        }
    }

    public void updateCurrentMousePosition(Vector2i currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    public void render() {
        for(int i=0;i<20;i++){
            for(int j=0;j<15;j++){
                d.cells[i][j].drawMe(window);
            }
        }
    }

    @Override
    public void update() {
        //now to recreate the path
        Cell start=d.cells[currentPosition.x/32][currentPosition.y/32];
        while(start.pathNext!=null){
            window.draw(new Vertex[]{new Vertex(new Vector2f(start.pos.x*32+16,start.pos.y*32+16)),new Vertex(new Vector2f(start.pathNext.pos.x*32+16,start.pathNext.pos.y*32+16))}, PrimitiveType.LINE_STRIP);
            start=start.pathNext;
        }
    }
}
