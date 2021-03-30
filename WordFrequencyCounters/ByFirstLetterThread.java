
public class ByFirstLetterThread extends java.lang.Thread{
    String filename;
    ByFirstLetterFreq table;

    public ByFirstLetterThread(String filename, ByFirstLetterFreq table) { 
        this.filename = filename; 
        this.table = table;
    }

    public void run(){
        table.count(filename);
    }
}