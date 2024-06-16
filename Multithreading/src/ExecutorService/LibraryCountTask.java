package ExecutorService;

import java.util.concurrent.atomic.AtomicInteger;

public class LibraryCountTask implements Runnable{

//	private int count;
	private AtomicInteger count;
	
	public LibraryCountTask() {
//		count = 0;
		count = new AtomicInteger(0);
	}
	
	public int getCount() {
//		return count;
		return count.get();
	}
	
	@Override
	public void run() {
//		incrementCount();
		count.addAndGet(1);
		
	}

//	private void incrementCount() {
//		synchronized(this) {
//			count++;
//		}
//		
//	}
	
	

}
