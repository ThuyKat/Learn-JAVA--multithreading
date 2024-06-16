package ExecutorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientST {
public static void main(String[] args) {
	ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
	
	RunnablePeriodicTask periodicTask = new RunnablePeriodicTask();
	RunnableDelayedTask delayedTask = new RunnableDelayedTask();
	
	// schedule the periodic task to run EVERY 5 seconds, initial delay is 0 seconds
	
	scheduler.scheduleAtFixedRate(periodicTask, 0, 5, TimeUnit.SECONDS);
	
	// schedule the delayed task to run ONCE after 10 second delay
	
	scheduler.schedule(delayedTask, 10, TimeUnit.SECONDS);
	
	// to demonstrate clean shutdown, a shutdownTask is scheduled to shutdown the scheduler after 30 seconds
	
	scheduler.schedule(new Runnable() {

		@Override
		public void run() {
			System.out.println("Shutting down scheduler at: " + System.currentTimeMillis());
			scheduler.shutdown();
		}
		
	}, 30, TimeUnit.SECONDS);
	System.out.println(Thread.currentThread().getState());
	
}
}
