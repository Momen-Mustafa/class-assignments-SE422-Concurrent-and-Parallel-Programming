import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

//Yahya Raaed Faleh         yr19102@auis.edu.krd
//Momen Mustafa Alhafidh    mm17273@auis.edu.krd
//Sources: https://stackoverflow.com/questions/58846035/java-how-to-input-multiple-lines-of-text-in-one-input

public class Assign1new{
    private static int coreCount = Runtime.getRuntime().availableProcessors();// source
    public static void main(String...args) {

        int count = 0;
        Task t = new Task();
        try {
            ExecutorService e = Executors.newCachedThreadPool();
            ThreadPoolExecutor();
            
            for (int i = 0; i < coreCount; i++) {
                if(i == 7){
                    e.execute(t);
                    statThread st = new statThread(count);
                    st.start();
                } else{
                    break;
                }
                e.execute(t);
            }
                
        } catch (Exception e) {}
    }

    private static ExecutorService ThreadPoolExecutor() {
        return new ThreadPoolExecutor(coreCount, 
                                      coreCount+2, 
                                      1L, 
                                      TimeUnit.SECONDS, 
                                      new SynchronousQueue<>());
    }
}

class Task implements Runnable{
    private static int counter;
    Scanner sc = new Scanner(System.in);
    ReentrantLock runLock = new ReentrantLock();
    ReentrantLock countLock = new ReentrantLock(); 
    ReentrantLock splitLock = new ReentrantLock();
    private String text;

    public void run() {
        runLock.lock();
        try {
            counting();
        } finally{
            runLock.unlock();
        } 
        
    }
    public int counting(){
        countLock.lock();
        try {
            System.out.println("Please write what you want: (please write 'end' when you finish)");
            while(sc.hasNextLine()){
                split(text, 8);
                String word = sc.next();
                
                if(word.equals("the") || word.equals("a") || word.equals("an")){
                    counter++;
                }

                if(sc.nextLine().equals("end")){
                    notifyAll();
                    break;
                } 
            }
        } finally{
            countLock.unlock();
        } 
        return counter;
    }

    public void split(String arr, int n){
        splitLock.lock();

        try {
            int arrSize = arr.length();
            int partSize;
    
            if(arrSize % n != 0){
                System.out.println("Invalid Input, array size cannot be split");
                return;
            }
    
            partSize = arrSize/n;
    
            for (int i = 0; i < arrSize; i++) {
                if(i% partSize == 0){
                    System.out.println();
                    System.out.println(arr.charAt(i));
                }
            }
                
        } catch (Exception e) {}
       
        finally{
            splitLock.unlock();
        }
    }   
}

class statThread extends Thread{
    int count;
    ReentrantLock lock = new ReentrantLock();
    public statThread(int counter){
        this.count = counter;
    }
    public void run(){
        lock.lock();
        try{
            wait();
            System.out.println("The result of the computation: " + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
    }
}


