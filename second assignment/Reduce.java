//  Momen & Yahya
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Reduce extends Thread{
    private BlockingQueue<Integer> queue;
    public Reduce(BlockingQueue<Integer> queue){
      this.queue = queue;
    }
    // int highest = 0;
    int[] words= new int[10];
    public int count() {
      AtomicInteger highest = new AtomicInteger(0);
        try {
          for (int i = 0; i < 10; i++) {
            
            int x= queue.take();
            if(x>highest.get()){
             highest.set(x);
            }
          }
          
        } catch (Exception e) { }
        return highest.get();
      }
      @Override
      public void run() {
        count();
      }
  }