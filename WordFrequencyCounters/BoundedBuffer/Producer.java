import java.io.*;
import java.util.*;

public class Producer extends Thread {
    private final BoundedBuffer buffer;
    String[] fileNames;

    /**
     * Create a Producer
     * 
     * @param buff The buffer into which the producer queues
     */
    public Producer(BoundedBuffer buff, String[] fileNames) {
        this.buffer = buff;
        this.fileNames = fileNames;
    }

    /**
     * Open up files given the file names from an array of Strings. Every word from
     * files gets inserter into BoundedBuffer
     */
    public void run() {
        try {
            Scanner sc = null;
            for (String s : fileNames) {
                //System.out.println("P: " + s);
                File f = new File(s);
                try {
                    sc = new Scanner(f);
                    while (sc.hasNext()) {
                    String word = sc.next();
                    //produce a batch of words (array or list)
                    buffer.produce(word);
                    //System.out.println("P: " + word);
                }
                } catch (FileNotFoundException e) {
                    System.out.println("Cannot open scanner");
                    System.exit(1);
                } 
             } 
             //System.out.println("P: Producing null");
             buffer.produce(null);
             //System.out.println("P: Done producing");
            
        } catch (InterruptedException e) {
        }
    }
}