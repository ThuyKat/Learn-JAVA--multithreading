package ExecutorService;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<String> {

	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		return "I am in callable using Thread ;" +Thread.currentThread().getName();
	}

}
