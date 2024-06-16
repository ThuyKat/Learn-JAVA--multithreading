package ExecutorService;

public class ClientSynchronized {
public static void main(String[] args) {
	RunnableSynchronizedTask synTask = new RunnableSynchronizedTask();
	
	Thread t1 = new Thread(synTask,"thread 1");
	Thread t2 = new Thread(synTask, "thread 2");
	
	t1.start();
	t2.start();
}
}
