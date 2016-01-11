import org.jsfml.graphics.PrimitiveType;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Vertex;
import org.jsfml.system.*;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.*;

/**
 * Created by Phillip on 07/01/2016.
 */
public class InterfaceTest {
    Pathfind d;
    RenderWindow w;
    Vector2i curr;
    InterfaceTest(){
        d=new Pathfind(9,10);
        w=new RenderWindow(new VideoMode(640,480),"Pathfind Test");
        curr=new Vector2i(0,0);

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
        while(true){
            w.clear();
            for(Event e:w.pollEvents()){
                MouseEvent m=e.asMouseEvent();
                if(m!=null){
                    if(m.type==Event.Type.MOUSE_MOVED){
                        curr=m.position;
                    }
                }
            }
            for(int i=0;i<20;i++){
                for(int j=0;j<15;j++){
                    d.cells[i][j].drawMe(w);
                }
            }
            //now to recreate the path
            Cell start=d.cells[curr.x/32][curr.y/32];
            while(start.pathNext!=null){
                w.draw(new Vertex[]{new Vertex(new Vector2f(start.pos.x*32+16,start.pos.y*32+16)),new Vertex(new Vector2f(start.pathNext.pos.x*32+16,start.pathNext.pos.y*32+16))}, PrimitiveType.LINE_STRIP);
                start=start.pathNext;
            }
            w.display();
        }
    }

}
