package ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ClientMemoryConsistency {
public static void main(String[] args) {
	ExecutorService ex = Executors.newFixedThreadPool(2);
	RunnableMemoryConsistency write = new RunnableMemoryConsistency("W");
	RunnableMemoryConsistency read = new RunnableMemoryConsistency("R");
	
	ex.submit(write);
	ex.submit(read);
	
	ex.shutdown();
	try {
		ex.awaitTermination(10, TimeUnit.MINUTES);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	System.out.println("MAIN");
	
}
}
