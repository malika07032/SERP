
public class MergeThread extends java.lang.Thread{
    String filename;
    MergeFreq table;

    public MergeThread(String filename) { 
        this.filename = filename;
        table = new MergeFreq();
    }

    public void run(){
        table.count(filename);
    }
}
