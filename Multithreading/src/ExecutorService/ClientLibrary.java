package ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ClientLibrary {
public static void main(String[] args) {
	ExecutorService ex = Executors.newFixedThreadPool(4);
	
	// assume 20 people are coming
	
	LibraryCountTask task = new LibraryCountTask();
	for(int i = 0; i<20000;i++) {
		ex.submit(task);
	}
	ex.shutdown();
	try {
		ex.awaitTermination(10, TimeUnit.SECONDS);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	System.out.println("Total number of people in the Library : "+ task.getCount());
	
	try {
		Thread.sleep(10);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
