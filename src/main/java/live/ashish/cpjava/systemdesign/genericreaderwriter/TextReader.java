package live.ashish.cpjava.systemdesign.genericreaderwriter;

import live.ashish.cpjava.systemdesign.genericreaderwriter.interfaces.Reader;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
public class TextReader implements Reader, Runnable {
    BlockingQueue<Message> queue;
    String path;
    static long tsOffset = 0;// epochtime

    @Override
    public void reads() throws IOException {
        // reading continously from a file and writing messages to queue
        while (true) {
            File filepath = new File(path);
//            FileFilter fileFilter
            Stream<File> fileStream = Arrays.stream(filepath.listFiles()); // assumption sorted by modifiedts
            List<File> filesToProcess = fileStream.filter(file -> file.lastModified() > tsOffset).collect(Collectors.toList());
            for (File f : filesToProcess) {
                // processing this file and read it add to message
                BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
//                    tsOffset = f.lastModified();
                    try {
                        Message message = new Message(line);
                        System.out.println("\n[READER] Sending message: " + message);
                        queue.put(message); // waits until a message available
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                tsOffset = f.lastModified(); // this can lead to duplicates in case of in bw file reboots
            }
        }
    }

    @Override
    public void run() {
        try {
            reads();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
