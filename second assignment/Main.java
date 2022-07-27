//  Momen & Yahya
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
  public static void main(String[] args) {
		ExecutorService executorMap = Executors.newCachedThreadPool();
    ThreadPoolExecutor poolMap = (ThreadPoolExecutor) executorMap;
    poolMap.setMaximumPoolSize(8);
    poolMap.setCorePoolSize(4);
    

    ExecutorService executorReduce = Executors.newFixedThreadPool(2);


    BlockingQueue<Integer> q = new LinkedBlockingQueue<>();

    
    for (int i = 0; i < 10; i++) {
      Map map = new Map(q);
      poolMap.execute(map);

      String word = word();
      System.out.println(word);
      map.put(word);

    }


    Reduce reduce = new Reduce(q);
    Reduce reduce2 = new Reduce(q);

    executorReduce.execute(reduce);
    executorReduce.execute(reduce2);

    System.out.println(reduce.count());

  }

  public static String word() {
//https://www.programiz.com/java-programming/examples/generate-random-string
    
    // create a string of all characters
    String alphabet = "abcdefghijklmnopqrstuvwxyz";

    // create random string builder
    StringBuilder sb = new StringBuilder();

    // create an object of Random class
    Random random = new Random();

    // specify length of random string
    int length = random.nextInt(15)+3;

    for(int i = 0; i < length; i++) {

      // generate random index number
      int index = random.nextInt(alphabet.length());

      // get character specified by index
      // from the string
      char randomChar = alphabet.charAt(index);

      // append the character to string builder
      sb.append(randomChar);
    }

    return sb.toString();
  }
}
