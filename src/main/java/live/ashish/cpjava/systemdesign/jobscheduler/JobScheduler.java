package live.ashish.cpjava.systemdesign.jobscheduler;



/* This class creates a ASync Job scheduler which will take many jobs and run them
*
* */


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Data @AllArgsConstructor
class Job implements Runnable{
    private String name;
    private long seconds; // for how long it will run ( to emulate the working of scheduler )
    public static int jobsRunning=0;

    @Override
    public void run() {
        jobsRunning++;
        System.out.println("Executing - "+name);
        this.execute();
        System.out.println("Completed - "+name);
        jobsRunning--;
    }

    private void execute(){
        try {
            Thread.sleep(seconds*1000); // taking as job execution
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class JobScheduler {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        //generating 10 jobs to be submitted.
        for(int i=0;i<20;i++){
            Job j=new Job("job".concat(String.valueOf(i+1)), 5L);
            System.out.println("Jobs Running in parallel: "+Job.jobsRunning);
            try {
                Thread.sleep(2*500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.execute(new Thread(j));
            // we can also use submit and get the future
//            Future<?> submit = executorService.submit(new Thread(j));
        }

        executorService.shutdown();
        while(!executorService.isShutdown()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Executor service is shutdown...");
    }

}
