package ExecutorService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import MultipleThreads.FactorialMT;

public class Client {
public static void main(String[] args) {
	
	List<Integer> input = Arrays.asList(1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000);

	long start = System.currentTimeMillis();
	ExecutorService ex = Executors.newFixedThreadPool(4);
	
	input.stream().parallel().forEach(num ->{
		FactorialMT fmt = new FactorialMT(num);
		ex.submit(fmt);
		
		System.out.println("MAIN: "+ Thread.currentThread().getName());
	});
	
	long end = System.currentTimeMillis();
	System.out.println("Time taken : " + (end - start));
	
} 
}
