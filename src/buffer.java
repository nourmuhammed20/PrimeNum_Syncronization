import java.util.LinkedList;
import java.util.Queue;


public class buffer {
    private int size; // Buffer Size in Memory
    public Queue<Object> queue = new LinkedList<>();
    String item;
    public int element = 0;
    public boolean is_finished=false;

    semaphore spaces = new semaphore(size);

    semaphore elements = new semaphore(0);
    public buffer (int size){
        this.size=size;
    }
    int getSize(){return size;}
    public synchronized void produce(int value) {
        //buffer is full
        while(queue.size()==size) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //convert integer values to string
        String strvalue=Integer.toString(value);
        //add value to the queue
        queue.add(strvalue);
        //Then after it produces the element, it will notify the consumer that
        //there is an element ready for consuming.
        element++;
        notify();
    }


    public synchronized String consume()
    {

        while(queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Then after it consumes the element, it will notify the producer that
        //there is a free space in the buffer.
        item = (String) queue.remove();
        //there is an element is consumed.
        element--;
        notify();
        return item;
    }
}