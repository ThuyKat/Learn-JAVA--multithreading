package ExecutorService;

public class RunnablePeriodicTask implements Runnable {

	@Override
	public void run() {
		System.out.println("Periodic task executed at: "+ System.currentTimeMillis());

		
	}

}
