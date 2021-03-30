
public class BasicThread extends java.lang.Thread{
    String filename;
    BasicThreadedFreq table;

    BasicThread(String filename, BasicThreadedFreq table) { 
        this.filename = filename; 
        this.table = table;
    }

    public void run(){
        table.count(filename);
    }
}
