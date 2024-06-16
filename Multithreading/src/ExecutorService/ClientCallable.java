package ExecutorService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ClientCallable {
public static void main(String[] args) {
	ExecutorService ex = Executors.newFixedThreadPool(4);
	
	CallableTask ct = new CallableTask();
	
	Future<String> res = ex.submit(ct);
	
	System.out.println(res);
	
	try {
		String res1 = res.get(); 
		System.out.println(res1);
	// This is blocking main from starting until the operation is completed
	} catch (InterruptedException e) {
		
		e.printStackTrace();
	} catch (ExecutionException e) {
		
		e.printStackTrace();
	}
	System.out.println(res);
	
	System.out.println("MAIN");
}
}
