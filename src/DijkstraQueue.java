/**
 * Created by Phillip on 18/12/2015.
 */
public class DijkstraQueue {
    DQItem start;

    class DQItem{
        DQItem next;
        Cell cell;
        int priority;

        DQItem(Cell c){
            next=null;
            cell=c;
            priority =cell.distance;
        }

        public void setPriority(int i){
            priority =i;
            cell.distance=i;
        }
    }

    DijkstraQueue(){
        start=null;
    }

    public void insert(Cell cell){
        insert(new DQItem(cell));
    }

    private void insert(DQItem in){
        //DQItem in=new DQItem(cell);
        if(start==null){
            start=in;
            return;
        }
        int p=in.cell.distance;
        if(start.priority >p){
            in.next=start;
            start=in;
            return;
        }
        //int p=in.cell.distance;
        DQItem prev=null;
        for(DQItem d=start;d!=null;d=d.next){
            if(d.priority>p){
                //now we know where to insert it
                if(prev==null){
                    start=d;
                }else{
                    prev.next=in;
                }
                in.next=d;
                return;
            }else{
                if(d.next==null){
                    d.next=in;
                    return;
                }
            }
            prev=d;
        }
        System.out.println("---------------I AM ERROR!!!-----------------");
    }

    /**
     *
     * @return The Cell at the front of the queue
     */

    Cell getMin(){
        Cell pop=start.cell;
        start=start.next;
        return pop;
    }

    /**
     * The decrease priority for the priority queue.
     * Will also put the item to the correct place.
     *
     * @param cell The Cell to decrease.
     * @param priority The priority to set the new cell to
     */

    void decrease(Cell cell,int priority) {
        DQItem prev=null;
        for (DQItem d = start; d != null; d = d.next) {
            if (d.cell.equals(cell)) {
                if(prev==null){
                    start=d.next;
                }else{
                    prev.next=d.next;
                }
                d.setPriority(priority);
                insert(d);
            }
            prev=d;
        }
    }
}
