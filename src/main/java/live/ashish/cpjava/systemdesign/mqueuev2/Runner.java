package live.ashish.cpjava.systemdesign.mqueuev2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Runner {
    public static void main(String[] args) {

        MessageQueue<Integer> queue = new MessageQueue<>(20);
        // Start multiple producers
        for (int i = 0; i < 3; i++) {
            new Thread(new Producer(queue)).start();
        }

        // Start multiple consumers
        for (int i = 0; i < 3; i++) {
            new Thread(new Consumer(queue)).start();
        }
    }

}
