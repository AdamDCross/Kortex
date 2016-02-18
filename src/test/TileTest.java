package test;

import graphics.Tilemap;
import main.Render;

/**
 * TileTest Class
 */
public class TileTest implements Render{
    Tilemap t;

    public TileTest(){
        t=new Tilemap("assets/Tiles.gif","assets/MapTest2.csv",32,32,5,2);
    }
    @Override
    public void render() {
        t.drawMap();
    }

    @Override
    public void update() {

    }
}
