package MultipleThreads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientFactorialMT {
	public static void main(String[] args) {
		List<Integer> input = Arrays.asList(1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000);
		List<Thread> threads = new ArrayList<>();
		long start = System.currentTimeMillis();
		input.stream().parallel().forEach(num -> {
			FactorialMT fmt = new FactorialMT(num);
			Thread t = new Thread(fmt);
			t.start();
			threads.add(t);
//			try {
//				t.join();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		});
		
		threads.stream().forEach(thread -> {
			try {
				thread.join();
				System.out.println(Thread.currentThread().getState());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		System.out.println("THIS IS MAIN THREAD ");
		long end = System.currentTimeMillis();
		System.out.println("time taken for this process: "+ (end -start));
		
		System.out.println(Thread.currentThread().getState());
	}
}
