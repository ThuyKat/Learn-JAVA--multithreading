package ExecutorService;

public class RunnableDelayedTask implements Runnable{

	@Override
	public void run() {
		System.out.println("Delayed task executed at: "+ System.currentTimeMillis());
		
	}

}
