package live.ashish.cpjava.systemdesign.genericreaderwriter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
* Write an application which consumes data from any source and writes to any desired sink.
The interface should be open for any kind of source or sink.
For the scope of this interview, you can consider the source to be a continuous stream of files being added to a local directory like resources/ or tmp/.
You can assume the sink to be an event based system like PubSub/Kafka etc. For the scope of this interview, you can consider the sink to be an in-memory queue.
Focus on getting a working solution with decent abstractions and failure handling by the end of this interview.
+=====+
Source => file/sftp/s3 etc.
Target => messaging system /kafka/pubsub etc.
Data size (input) => Array of characters ( in memory )
data output => same

* Input => TextInputreader ( line delimited ) hello world

Output => ever message should be line delimited ( define by reader )
* */
public class Main {

    public static void main(String[] args) {
        String path="/Users/ashishpatel/pateash/cpjava/src/main/resources/tmp";
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(100);
        new Thread(new TextReader(queue, path)).start();
        new Thread(new KafkaWriter(queue)).start();
    }
}
