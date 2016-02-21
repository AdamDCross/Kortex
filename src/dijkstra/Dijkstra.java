package dijkstra;

import assets.ArtAsset;
import assets.AssetManager;
import enemy.NPCHandle;
import graphics.HUD;
import graphics.Tilemap;
import main.*;

import main.Window;
import org.jsfml.graphics.FloatRect;
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
    private HUD hud;
    private int w,h;
    private boolean active;

    public Dijkstra(HUD parent) {
        Vector<ArtAsset> asset = AssetManager.getInstance().getArtAssetByAssetType("TILE_MAP");
        player=new Player("Phil",100);
        handle=new NPCHandle(player);
        hud=parent;

        active=false;
        tiles=new Tilemap(asset.elementAt(0).getAssetPath(),"src/assets/MapTest2.csv",32,32,5,2);
        d = Pathfind.getInstance();
        //currentPosition = new Vector2i(0,0);
        this.window = Window.getInstance().getGameWindow();
    }

    public void setActive(boolean act){

    }

    /*public void updateCurrentMousePosition(Vector2f currentPosition) {
        //fixes the mouse coords when the window is increased/decreased, and clamps them into the current view
        //Vector2f hold=Window.getInstance().getGameWindow().mapPixelToCoords(currentPosition);
        //currentPosition=Vector2f.sub(currentPosition,new Vector2f(hud.getGameWindowRect().top)
        System.out.println(hud.getGameWindowRect());
        //Vector2f hold=Vector2f.sub(currentPosition,new Vector2f(hud.getGameWindowRect().left,hud.getGameWindowRect().top));
        this.currentPosition = new Vector2i((int)GameMaths.clamp(currentPosition.x-hud.getGameWindowRect().left,0,Pathfind.GRID_WIDTH*(hud.getGameWindowRect().width/Pathfind.GRID_SIZE)),
                (int) GameMaths.clamp(currentPosition.y-hud.getGameWindowRect().top,0,Pathfind.GRID_HEIGHT*(hud.getGameWindowRect().height/Pathfind.GRID_SIZE)));
    }*/

    /*public Vector2i getRelTileForMousePosition(){
        return currentPosition;
    }*/

    @Override
    public void render() {
        //TODO need to set size and other shit here
        //Extracts the translation
        float drawX=hud.getGameWindowRect().left;
        float drawY=hud.getGameWindowRect().top;
        //this is the width/height of the grid.
        float drawW=(hud.getGameWindowRect().width/Pathfind.GRID_WIDTH);
        float drawH=(hud.getGameWindowRect().height/Pathfind.GRID_HEIGHT);
        //System.out.println(drawW+" - "+drawH);
        Beacon.getInstance().spawn(true,100,new Vector2f(drawX+(4*drawW),drawY+(4*drawH)),new FloatRect(0,0,drawW,drawH));
        for (int y = 0; y < Pathfind.GRID_HEIGHT; y++) {
            for (int x = 0; x < Pathfind.GRID_WIDTH; x++) {
                tiles.drawTile((int)(drawX+x*drawW),(int)(drawY+y*drawH),(int)drawW,(int)drawH,x,y);
            }
        }
    }

    @Override
    public void update() {
    }
}
