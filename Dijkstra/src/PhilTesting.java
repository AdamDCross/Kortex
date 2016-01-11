import org.jsfml.system.Vector2i;

/**
 * This is Phil's personal testing main function. This is where I'll be testing out my things
 * Phil ;)
 */
public class PhilTesting {

    /*public static void main(String[] args) {
        dijkstraTest();
    }*/

    public static void main(String[] args) {
        InterfaceTest i=new InterfaceTest();
    }

    static void dijkstraTest(){
        Pathfind d=new Pathfind(3,12);
        for(int cx = 0; cx< Pathfind.GRID_WIDTH; cx++) {
            for (int cy = 0; cy < Pathfind.GRID_HEIGHT; cy++) {
                System.out.print(d.cells[cx][cy].distance+"\t");
            }
            System.out.println(" ");
        }
    }
    static void dijkstraQueueTest(){
        DijkstraQueue d=new DijkstraQueue();
        int n=0;
        Cell i=new Cell(new Vector2i(-1,-1),1);
        i.distance=8;
        d.insert(i);
        for(int cx = 0; cx< Pathfind.GRID_WIDTH; cx++){
            for(int cy = 0; cy< Pathfind.GRID_HEIGHT; cy++){
                Cell c=new Cell(new Vector2i(cx,cy),1);
                c.distance=(int) (Math.random()*100);
                System.out.println("Pos "+c.pos+" distance "+c.distance);
                d.insert(c);
                n++;
            }
        }
        //d.decrease(i,50);
        System.out.println(n);
        System.out.println("----------------------------------");
        n=0;
        while(d.start!=null){
            Cell c=d.getMin();
            System.out.println("Pos "+c.pos+" distance "+c.distance);
            n++;
        }

    }
}
