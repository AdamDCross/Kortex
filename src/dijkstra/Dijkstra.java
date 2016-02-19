package dijkstra;

import assets.ArtAsset;
import assets.AssetManager;
import enemy.NPCHandle;
import graphics.Tilemap;
import main.*;

import main.Window;
import org.jsfml.graphics.PrimitiveType;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Vertex;
import org.jsfml.system.*;
import player.Player;

import java.util.Vector;

/**
 * TODO add to HUD, then pass through the stuff from the HUD into here
 * TODO Make this the main game class. HUD will handle the inputs.
 */
public class Dijkstra implements Render {
    private Pathfind d;
    private Vector2i currentPosition;
    private RenderWindow window;
    private Tilemap tiles;
    private NPCHandle handle;
    public Player player;

    public Dijkstra() {
        Vector<ArtAsset> asset = AssetManager.getInstance().getArtAssetByAssetType("TILE_MAP");
        player=new Player("Phil",100);
        handle=new NPCHandle(player);

        tiles=new Tilemap(asset.elementAt(0).getAssetPath(),"assets/MapTest2.csv",32,32,5,2);
        d = Pathfind.getInstance();
        currentPosition = new Vector2i(0,0);
        this.window = Window.getInstance().getGameWindow();

        /*
        //DEBUGGING
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
        }*/
    }

    public void updateCurrentMousePosition(Vector2i currentPosition) {
        //fixes the mouse coords when the window is increased/decreased, and clamps them into the current view
        Vector2f hold=Window.getInstance().getGameWindow().mapPixelToCoords(currentPosition);
        this.currentPosition = new Vector2i((int)GameMaths.clamp(hold.x,0,Pathfind.GRID_WIDTH*Pathfind.GRID_SIZE),
                (int) GameMaths.clamp(hold.y,0,Pathfind.GRID_HEIGHT*Pathfind.GRID_SIZE));
    }

    public Vector2i getRelTileForMousePosition(){
        return currentPosition;
    }

    @Override
    public void render() {
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
}
