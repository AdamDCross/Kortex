package graphics;

import dijkstra.Pathfind;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Tilemap Class
 */
public class Tilemap {
    //private Animation tiles;
    org.jsfml.graphics.Texture tiles;
    private int w,h,row,col;
    private ArrayList<Integer> map;
    //Add a way to read csv text data here
    //public Tilemap currMap;

    public Tilemap(String source,String map,int w,int h, int row,int col){
        //tiles=new Animation(source,w,h,row,col,0,new Vector2f(0,0));

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

    }

    public void drawMap(){
        for (int y = 0; y < Pathfind.GRID_HEIGHT; y++) {
            for(int x = 0; x< Pathfind.GRID_WIDTH; x++) {

                int m = map.get(x+y*Pathfind.GRID_WIDTH);

                int tu=w*(m%row);
                int tv=h*(m/row);

                Vertex[] vArray={
                        new Vertex(new Vector2f(x*32,y*32),new Vector2f(tu,tv)),
                        new Vertex(new Vector2f(x*32+32,y*32),new Vector2f(tu+32,tv)),
                        new Vertex(new Vector2f(x*32+32,y*32+32),new Vector2f(tu+32,tv+32)),
                        new Vertex(new Vector2f(x*32,y*32+32),new Vector2f(tu,tv+32))
                };
                main.Window.getInstance().getGameWindow().draw(vArray, PrimitiveType.QUADS,new RenderStates(tiles));
                //new Image(tiles,new Vector2f(x*Pathfind.GRID_SIZE,y*Pathfind.GRID_SIZE),tu,tv,0).render();

                /*Image img = tiles.getFrame(x+y*col);
                img.img.setPosition(new Vector2f(x*this.w,y*h));
                img.render();*/
            }
        }
    }
    public ArrayList<Integer> getMap(){
        return map;
    }
}
