package live.ashish.cpjava.systemdesign.test;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Job implements Runnable {
    int id;

    public Job(int id) {
        this.id = id;
    }

    public synchronized void hello(){
        System.out.println("Hello"+id);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Running job:"+id);
            try {
                hello();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class Test {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i=0;i<6;i++){
            Job j = new Job(i+1);
            executorService.execute(j);
        }
        executorService.shutdown();
    }
}
