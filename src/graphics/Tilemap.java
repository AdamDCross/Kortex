package graphics;

import dijkstra.DijkstraTest;
import main.Pathfind;
import main.Render;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Window;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Tilemap Class
 */
public class Tilemap {
    org.jsfml.graphics.Texture tiles;
    private int w,h,row,col;
    private ArrayList<Integer> map;

    public Tilemap(String source,String map,int w,int h, int row,int col){
       try {
            tiles=new Texture();
            tiles.loadFromFile(Paths.get(source));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.w=w;
        this.h=h;
        this.row=row;
        this.col=col;
        this.map=new ArrayList<Integer>();
        //grabs and converts string into map data
        try {
            BufferedReader r=new BufferedReader(new FileReader(map));
            String buff;
            while((buff=r.readLine())!=null) {
                String[] mapS = buff.split("[,]");
                for (String s : Arrays.asList(mapS)) {
                    this.map.add(Integer.parseInt(s));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawTile(int x,int y){
        int m=map.get(x+y*Pathfind.GRID_WIDTH);

        int tu=w*(m%row);
        int tv=h*(m/row);

        Vertex[] vArray={
                new Vertex(new Vector2f(x*32,y*32),new Vector2f(tu,tv)),
                new Vertex(new Vector2f(x*32+32,y*32),new Vector2f(tu+32,tv)),
                new Vertex(new Vector2f(x*32+32,y*32+32),new Vector2f(tu+32,tv+32)),
                new Vertex(new Vector2f(x*32,y*32+32),new Vector2f(tu,tv+32))
        };
        main.Window.getInstance().getGameWindow().draw(vArray, PrimitiveType.QUADS,new RenderStates(tiles));
    }

    public void drawMap(){
        for (int y = 0; y < Pathfind.GRID_HEIGHT; y++) {
            for(int x = 0; x< Pathfind.GRID_WIDTH; x++) {
                drawTile(x,y);
            }
        }
    }
    public ArrayList<Integer> getMap(){
        return map;
    }
}
