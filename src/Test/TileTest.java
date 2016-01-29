package Test;

import graphics.Tilemap;
import main.Render;

/**
 * Created by Phillip on 26/01/2016.
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
