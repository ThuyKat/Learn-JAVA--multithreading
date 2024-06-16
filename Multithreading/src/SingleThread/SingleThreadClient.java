package SingleThread;

public class SingleThreadClient {
public static void main(String[] args) {
	
	// run single thread created with Thread class
	TaskUsingThread task = new TaskUsingThread();
	
	task.start();
	
	
	System.out.println("This is child thread of Thread class " + task);
	
	// run single thread created with Runnable class 
	
	TaskUsingRunnable task2 = new TaskUsingRunnable();
	
	Thread t = new Thread(task2, "Thuy thread"); 
	
	System.out.println("This is the thread instance that runs the runnable task " + t);
	
	t.start();
	
	//check which thread we are on
	System.out.println("I am in main on thread " + Thread.currentThread().getName());

}
}
