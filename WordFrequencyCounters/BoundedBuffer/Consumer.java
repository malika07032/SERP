import java.util.Random;

public class Consumer extends Thread{
    private final BoundedBuffer buffer;
    BoundedBufferTable table;
	Random generator;

  /**
	 * Constructs a consumer of items from a bounded buffer
	 * @param buff the bounded buffer the consumer takes items from
	 */
	public Consumer(BoundedBuffer buff, BoundedBufferTable table) {
		buffer = buff;                  // The bounded buffer
		generator = new Random();    // Used to generate a random wait time
        this.table = table;
	}

	/**
	 * Get a word from a buffer and inserts it into the hashtable
	 */
	public void run() {
		try {
			while (true) {
				//System.out.println("C: About to consume");
				String word = buffer.consume();
				if (word == null){
					//System.out.println("C: Done consuming");
					return;
				}
				//System.out.println("C: Consumed: " + word);
				table.count(word);
			} 
		} catch(InterruptedException e) {

		}
	}
}