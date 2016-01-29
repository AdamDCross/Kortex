package Test;

import graphics.Tilemap;
import main.Render;

/**
 * TileTest Class
 */
public class TileTest implements Render{
    Tilemap t;

    public TileTest(){
        t=new Tilemap("src/assets/Tiles.gif","src/assets/MapTest2.csv",32,32,5,2);
    }
    @Override
    public void render() {
        t.drawMap();
    }

    @Override
    public void update() {

    }
}
