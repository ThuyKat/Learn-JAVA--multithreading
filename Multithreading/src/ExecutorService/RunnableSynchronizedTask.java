package ExecutorService;

public class RunnableSynchronizedTask implements Runnable {

	@Override
	public void run() {
		synchronized(this) {
			System.out.println(Thread.currentThread().getName() + " acquired the lock and is executing the synchronized block");
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
			}
			
			System.out.println(Thread.currentThread().getName() + " is leaving the synchronized block");
		}
		
	}

}
