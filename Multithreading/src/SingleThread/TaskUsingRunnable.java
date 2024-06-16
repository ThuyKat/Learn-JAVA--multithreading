package SingleThread;

public class TaskUsingRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println("RUNNABLE: I am running " + Thread.currentThread().getName());
		
	}

}
