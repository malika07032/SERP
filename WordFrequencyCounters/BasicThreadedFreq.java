import java.io.*;
import java.util.*;


public class BasicThreadedFreq {

    Hashtable<String,Integer> map = new Hashtable<String,Integer>();
    
    //open the file and populate the map with words and their counts
    //preventing multiple threads from executing the same code at one time
    public void  count(String filename) {
        
        File f = new File(filename); 
        Scanner sc = null;
        try {
          sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("cannot open scanner");
            System.exit(1);
        };
        
        while (sc.hasNext()) {
            //the current word
            String word = sc.next(); 
            
            synchronized (this) {
                if (map.containsKey(word)) {
                    //increment frequency
                    int freq = (int)map.get(word);
                    map.put(word, (Integer)(freq+1));
                } else {
                    map.put(word, (Integer)1);
                }
            }
        }
    }
    
    public void print() {
        System.out.println("Word Table call");
        Set<String> set = map.keySet();
        Object[] words = set.toArray();
        Arrays.sort(words);
    
        for (Object s:words) {
            int freq = (int)map.get(s);
            System.out.println(s + ": " + freq);
        }
    }

    public static void main(String[] args) {
        //checking if args has at least one file
        if (args.length < 1) {
            System.out.println("usage: WordFreq <filename>");
            System.exit(1);
        }
        //creating an instance of the NewWordFreq class
        BasicThreadedFreq table = new BasicThreadedFreq(); 
        //creating an array of threads
        BasicThread[] firstTable = new BasicThread[args.length];
        //each thread corresponds to a file in args
        for(int i = 0 ; i < args.length ; i++){
            //for each index in args creating an instance of CountingThread
            BasicThread first = new BasicThread(args[i], table);
            //inserting a thread in the array of threads
            firstTable[i] = first;
            //running a thread
            first.start();
            
        }
        
        for(BasicThread t : firstTable){
            try{
                //holds the execution of the thread currently running until it's finished
                //next thread won't be executed, unless the first one is finished
                t.join();
            }
            catch(InterruptedException exception){
            }
        }
        table.print();
    }
}


