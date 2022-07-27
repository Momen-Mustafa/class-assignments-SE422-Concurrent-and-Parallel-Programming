//  Momen & Yahya
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Map extends Thread{
    private BlockingQueue<Integer> queue;
    public Map(BlockingQueue<Integer> queue){
      this.queue = queue;
    }
  
    public void put(String word) {
        int length = word.length();
        try {
          queue.put(length);
        } catch (Exception e) {}
    }
    @Override
    public void run() {
  
    }
  }
  
  