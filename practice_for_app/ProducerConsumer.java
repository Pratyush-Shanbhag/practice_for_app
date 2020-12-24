import java.lang.Thread; 
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

class Producer extends Thread { 
  
	StringBuffer buffer; 
	BufferedReader br;	

	Producer() {
		br = new BufferedReader(new InputStreamReader(System.in));	
        	buffer = new StringBuffer(); 
   	} 
  
    	public void run() { 

        	synchronized (buffer) {
			String str = "";
			while(!str.trim().equals("q")) {
				try {
					System.out.println("Enter something: ");
					str = br.readLine();
				}
				catch(IOException e) {
					e.printStackTrace();
				}
				if(buffer.length() ==  8) 	
					System.out.println("Buffer is full");
				else {
					buffer.append(str + " ");
                    			System.out.println("Produced " + "\"" + str + "\""); 
				}
				buffer.notify();
				try {
					Thread.sleep(1000);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
        	} 
    	}	 
} 


class Consumer extends Thread { 
	
	Producer p; 
  
    	Consumer(Producer temp) { 
        	p = temp; 
    	} 
  
    	public void run() {  
        	
		synchronized (p.buffer) { 
            		try { 
                		p.buffer.wait(); 
            		} 
            		catch (Exception e) { 
                		e.printStackTrace(); 
            		} 
  
//            		for (int i = 0; i < 4; i++) { 
//                System.out.print(p.buffer.charAt(i) + " "); 
//                  		System.out.println(p.buffer);

  	    		if(p.buffer.length() == 0) 
            			System.out.println("\nBuffer is Empty"); 
        		else {
				System.out.println("Consumed " + "\"" + p.buffer.substring(0, p.buffer.indexOf(" ")) + "\"");
				p.buffer.delete(0, p.buffer.indexOf(" ")+1);
			}
			try {
				Thread.sleep(1000);
                        }
			catch(Exception e) {
				e.printStackTrace();
			}	
		} 
    	} 
} 
  

public class ProducerConsumer {

	public static void main(String[] args) {
		
		Producer p = new Producer(); 
        	Consumer c = new Consumer(p); 
        	Thread t1 = new Thread(p); 
        	Thread t2 = new Thread(c); 
  
        	t2.start(); 
        	t1.start(); 	
	}
			
}
