package live.ashish.cpjava.systemdesign.mqueuev2;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final MessageQueue<Integer> queue;

    public Consumer(MessageQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int item = queue.take(); // will block if the queue is empty
                consume(item);
                System.out.println("Consumed: " + item);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void consume(int item) {
        // Implement consumption logic
    }
}
