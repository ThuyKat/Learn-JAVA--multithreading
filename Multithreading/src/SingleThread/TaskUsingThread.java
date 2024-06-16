package SingleThread;

public class TaskUsingThread extends Thread{

	@Override
	public void run() {
		System.out.println("THREAD: I am running " + Thread.currentThread().getName());
	}
}
