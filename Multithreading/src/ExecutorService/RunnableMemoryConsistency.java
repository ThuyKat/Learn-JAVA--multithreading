package ExecutorService;

import java.util.concurrent.atomic.AtomicInteger;

public class RunnableMemoryConsistency implements Runnable {

	// specify the type of task: "R" or "W"
	private String type;

	// this represents the data shared between instances of the class
//	private static int data = 0;
//	private static volatile int data = 0;
	private static AtomicInteger data = new AtomicInteger(0);

	RunnableMemoryConsistency(String type) {
		this.type = type;
	}

	// if "W" : increment data to 5
	// if "R" : read the data whenever it is incremented

	@Override
	public void run() {
		if ("W".equals(type)) {
//			while (data != 5) {
//				data++;
			while (data.get() != 5) {
				data.addAndGet(1);
				System.out.println("updated data to: " + data);
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
		} else {
			int temp = 0;
			while (true) {
//				if (temp != data) {
//					temp = data;
				if (temp != data.get()) {
					temp = data.get();
				
					System.out.println("Have read new data: " + temp);
				}
			}
		}

	}

}
