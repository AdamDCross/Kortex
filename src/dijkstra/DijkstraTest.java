package dijkstra;

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

    public DijkstraTest() {
        d = new Pathfind(9,10);
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
        for(int i=0;i<20;i++){
            for(int j=0;j<15;j++){
                d.getCells()[i][j].drawMe(window);
            }
        }
    }

    @Override
    public void update() {
    }
}