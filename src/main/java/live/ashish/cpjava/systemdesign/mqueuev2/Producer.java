package live.ashish.cpjava.systemdesign.mqueuev2;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final MessageQueue<Integer> queue;

    public Producer(MessageQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int item = produce();
                queue.put(item); // will block if the queue is full
                System.out.println("Produced: " + item);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int produce() {
        // Implement production logic
        return (int) (Math.random() * 100);
    }
}
