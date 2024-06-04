package live.ashish.cpjava.systemdesign.jobscheduler;


import lombok.Data;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

enum State {
    RUNNING,
    FINISHED,
    WAITING
}

@Data
class JobV2 implements Runnable {
    UUID jobid;
    String name;
    int timeToRunInSec;
    State state;

    public JobV2(String name, int timeToRunInSec) {
        this.jobid = UUID.randomUUID();
        this.state = State.WAITING;
        this.name = name;
        this.timeToRunInSec = timeToRunInSec;
    }

    @Override
    public void run() {
        System.out.println("Running job:" + this);
        this.state=State.RUNNING;
        try {
            System.out.println("sleep job for (seconds):" + timeToRunInSec);
            Thread.sleep(timeToRunInSec * 1000L); // seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        this.state=State.FINISHED;
        System.out.println("Finished job:" + this);
    }

    @Override
    public String toString() {
        return "JobV2{" +
                jobid +
                "," + name +
                "," + timeToRunInSec +
                "," + state +
                '}';
    }
}

@Data
class Scheduler {
    private ExecutorService executorService;
    private Map<String, JobV2> tasks = new HashMap<>();
//    private Map<String, JobFuture> taskFutures = new HashMap<>();

    private Scheduler(ExecutorService executorService) {
        this.executorService = executorService;
    }

    static public Scheduler getScheduler(int threadPoolSize) {
        if (threadPoolSize < 1)
            return null;
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);
        System.out.println("Creating a scheduler of size " + threadPoolSize);
        return new Scheduler(executorService);
    }

    public void submit(String jobName, int timeTorun) {
        JobV2 job = new JobV2(jobName, timeTorun);
        System.out.println("Submitting job: " + job);
        tasks.put(job.name, job);
        tasks.put(job.name, job);
        Future<?> submit = executorService.submit(job);
    }
//    }

    public void showJobs() {
        if (tasks.isEmpty()) {
            System.out.println("No jobs present");
            return;
        }
        System.out.println("Showing all jobs\n=============\n");
        tasks.values().forEach(System.out::println);
    }

    public void shutdown() {
        this.executorService.shutdown();
    }
}

public class JobSchedulerV2 {
    public static void main(String[] args) {
        // we will have jobs which will be submitted,
        // these jobs must run ASYNC
        System.out.println("Creating a Scheduler for you, please enter number of threads");
        Scanner input = new Scanner(System.in);
        int threadPoolSize = input.nextInt();
        Scheduler scheduler = Scheduler.getScheduler(threadPoolSize);

        // we should not do user interaction in main thread as it will not allow async
        Thread userThread = new Thread(() -> handleUserInput(scheduler));
        userThread.start();
    }
    private  static void handleUserInput(Scheduler scheduler){
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("choose an option\n 1- Show jobs\n 2- Submit JobV2 \n 3-Exit");
            int option = input.nextInt();

            switch (option) {
                case 1:
                    scheduler.showJobs();
                    break;
                case 2:
                    System.out.println("name of the job");
                    String jobName = input.next();
                    System.out.println(jobName);
//                    input.next();
                    System.out.println("Time to run the job(seconds)");
                    int timeTorun = input.nextInt();
                    scheduler.submit(jobName, timeTorun);
                    break;
                case 3:
                    System.out.println("Closing application");
                    scheduler.shutdown();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invald Option please try again..");
            }
            System.out.println("\n=============\n");
        }
    }
}
