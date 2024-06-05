package live.ashish.cpjava.systemdesign.genericreaderwriter;

import live.ashish.cpjava.systemdesign.genericreaderwriter.interfaces.Writer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.BlockingQueue;

@Data
@AllArgsConstructor
public class KafkaWriter implements Writer, Runnable {
    BlockingQueue<Message> queue;

    //
    @Override
    public void writes() throws InterruptedException {
        // reading continously from a queue and writing messages to sink
        while (true) {
            // get a message from queue
            Message message = queue.take();// this will wait for any message
            System.out.println("\n[WRITER] Got message: " + message);
            // writes into kafka
        }
    }

    @Override
    public void run() {
        try {
            writes();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

