import java.lang.Thread; 
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class ProducerConsumer {
    
	public static void main(String[] args) throws InterruptedException {
        	Task task = new Task();

        	Thread producerThread = new Thread(new Runnable() {
            		public void run()
            		{
                		try {
                    			task.produce();
                		}
                		catch (InterruptedException e) {
                    		e.printStackTrace();
                		}
            		}
        	});

        	Thread consumerThread = new Thread(new Runnable() {
            		public void run()
            		{
                		try {
                    			task.consume();
                		}
                		catch (InterruptedException e) {
                    			e.printStackTrace();
                		}
            		}
        	});
		
		System.out.println();

        	producerThread.start();
        	consumerThread.start();

        	producerThread.join();
        	consumerThread.join();
    	}

    	public static class Task {


		StringBuffer buffer;
        	BufferedReader br;
		int count;

        	public Task() {
                	br = new BufferedReader(new InputStreamReader(System.in));
                	buffer = new StringBuffer();
        		count = 0;
		}


		public void produce() throws InterruptedException {
			String str = "";
			while (true) {
				synchronized (this)
				{
					try {
						System.out.println("Enter one character or \"q\" to exit. The buffer can't hold more than 4 entries at a time: ");
						str = br.readLine();
						if(str.trim().equals("q")) {
							System.out.println("\nHave a good day.\n");
							System.exit(0);
						}
					}
					catch(IOException e) {
						e.printStackTrace();
					}
					if(count == 4)
						System.out.println("Buffer is full");
					else {
						buffer.append(str + " ");
						System.out.println("Produced " + "\"" + str + "\"");
						count++;	
					}
					while(count == 4)
                        		wait();
			
                    			notify();
                    			Thread.sleep(1000);
                		}
            		}
        	}

		public void consume() throws InterruptedException {
			while (true) {
				synchronized (this)
				{
					while (buffer.length() == 0)
						wait();

					System.out.println("Consumed " + "\"" + buffer.substring(0, buffer.indexOf(" ")) + "\"");
					buffer.delete(0, buffer.indexOf(" ")+1);
					count--;

					notify();
					Thread.sleep(1000);
				}
			}
		}
	}
}
