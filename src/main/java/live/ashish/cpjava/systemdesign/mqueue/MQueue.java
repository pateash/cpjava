package live.ashish.cpjava.systemdesign.mqueue;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
Design an In-Memory Pull Based Queue Library:

Use Cases to be supported:

-Multiple queues maintained by the Library
-Each queue must support multiple publishers and subscribers.

-Each queue has a maximum retention period beyond which a message in the queue should not reside in memory.
-Each message inside the queue can have an optional TTL value. Any message with expired TTL should not be consumed by any subscriber and should not reside in the memory as well.
-Each consumer should read all the messages.

*/

class QMessage{
    private String msg;
    private LocalTime ts;
    private int ttl; // message based TTL

    public QMessage( String msg, int ttl){
        this.msg=msg;
        this.ts= LocalTime.now();
        this.ttl=ttl;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalTime getTs() {
        return ts;
    }

    public void setTs(LocalTime ts) {
        this.ts = ts;
    }

    @Override
    public String toString() {
        return "QMessage{" +
                "msg='" + msg + '\'' +
                ", ts=" + ts +
                ", ttl=" + ttl +
                '}';
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }
}

class MemQueue{
    private String name;
    private LinkedList<QMessage> queue;
    private LocalTime lastEviction;
    private long expiryTtl; // every message must follow this ( will be kicked out)
    private List<Subscriber> subscribers = new ArrayList<>();
    private List<Publisher> publishers= new ArrayList<>();

    static List<MemQueue> queues= new ArrayList<>();

    public MemQueue(String name,  long expiryTtl) {
        this.name = name;
        this.queue = queue;
        this.lastEviction = lastEviction;
        this.expiryTtl = expiryTtl;
    }

    static MemQueue get(String queueName){
        for(MemQueue queue: queues) {
            if(queue.name.equals(queueName))
                return queue;
        }
        return null;
    }
    static MemQueue getOrCreate( String name, long expiryTtl) {
        MemQueue q= MemQueue.get(name);
        if(q==null) {
            q=new MemQueue(name, expiryTtl);
            queues.add(q);
        }
        return q;
    }

    void publisher(Publisher pub){
        publishers.add(pub);
    }
    void subscriber(Subscriber sub){
        subscribers.add(sub);
    }

    void publish(Publisher pub, QMessage msg){
        if(!publishers.contains(pub))
            return;

        evictExpired();

        {
            queue.add(msg);
        }

    }

    QMessage pull(Subscriber sub){
        int offset=sub.getOffset();
        if(!subscribers.contains(sub))
            return null;
        evictExpired();
        QMessage msg =queue.listIterator(offset).next();

        if(msg.getTs().plusSeconds(msg.getTtl()).compareTo(LocalTime.now()) == -1){
            offset++;
            sub.setOffset(offset);
        }
        return msg;
    }
    void evictExpired(){
        if(this.lastEviction.compareTo(LocalTime.now().minusSeconds(this.expiryTtl))  == -1){
            evict();
        }
    }

    void evict(){

        // find the last eviction time and do eviction.
    }
}

class Publisher{
     private String name;

    public Publisher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Subscriber{
    private String name;
    private int offset;

    public Subscriber(String name) {
        this.name = name;
        this.offset=0;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

public class MQueue{
    public static void main(String[] args) {
     MemQueue queue = MemQueue.getOrCreate("finance-queue", 1);

     Publisher pub1 = new Publisher("publisher-1");
     Publisher pub2 = new Publisher("publisher-2");

     queue.publisher(pub1);
     queue.publisher(pub2);

     Subscriber sub1 = new Subscriber("subscriber-1");
     queue.subscriber(sub1);

     queue.publish(pub1,new QMessage("Hello", 2));
     queue.publish(pub2,new QMessage("world", 3));

     QMessage value = queue.pull(sub1);  // hello
     System.out.println(value);
 }
}


// Q1 -> [m1, m2,  m3] -> 2 second ( queue level), m2 -> 1 second


// 101 -> 102
//  Priority_Queue -> [(m1,105,Pointer), (m2,105,pointer) ]
//


// 100 > 102

// node-> value = NULL
