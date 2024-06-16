package ExecutorService;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import SingleThread.TaskUsingRunnable;

public class ClientCustom {
public static void main(String[] args) {
	// assume we want a ThreadPool with:
	// corepoolsize = 4
	//maxpoolsize =10
	//arrayBlockingQueue size 3
	
	ExecutorService ex = new ThreadPoolExecutor( 1,10,60,TimeUnit.SECONDS,new ArrayBlockingQueue<>(3));
	
	TaskUsingRunnable task = new TaskUsingRunnable();
	
	for(int i =1;i<10;i++) {
		ex.submit(task);
	}
	
	ex.shutdown();
	
	// make main thread to wait for all spawn threads for maximum 10 seconds
	try {
		ex.awaitTermination(10, TimeUnit.SECONDS);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	System.out.println("MAIN: I am running");
	
}
}
