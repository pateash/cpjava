package live.ashish.cpjava.systemdesign.mqueuev2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


// this is thread safe messaging queue
public class MessageQueue<T> {
    private final BlockingQueue<T> queue;

    public MessageQueue(int capacity) {
        // ArrayBlockingQueue is a bounded, blocking queue that stores the elements internally in an array.
        // It is thread-safe and can be used to implement producer-consumer scenarios.
        this.queue = new ArrayBlockingQueue<>(capacity);
    }

    public void put(T message) throws InterruptedException {
        // Put the message in the queue; wait for space to become available if the queue is full.
        queue.put(message);
    }

    public T take() throws InterruptedException {
        // Take a message from the queue; wait if necessary until an element becomes available.
        return queue.take();
    }

}



