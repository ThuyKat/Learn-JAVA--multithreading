package Future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureClient {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFuture<Void> cf = CompletableFuture.runAsync(new Runnable() {

			@Override
			public void run() {
				System.out.println(" RUNASYNC ");
				
			}
			
		});
		//Retrieve the result of CompletableFuture computation
		// This is the blocking call - main will wait for response then start
		cf.get();

		CompletableFuture<Void> cf1 = CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {
				// TODO Auto-generated method stub
				return "SUPPLY ASYNC";
			}

		}).thenApply(new Function<String, String>() {

			@Override
			public String apply(String s) {
				// TODO Auto-generated method stub
				return s.concat(" - THEN APPLY ");
			}

		}).thenAccept(new Consumer<String>() {

			@Override
			public void accept(String t) {
				// TODO Auto-generated method stub

			}

		})

		;

		System.out.println(cf1.get());

		CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {
				throw new RuntimeException("Test");
			}

		}).exceptionally(new Function<Throwable, String>() {

			@Override
			public String apply(Throwable t) {
				System.out.println("Received exception: " + t);

				return "Default handling for exception";
			}

		})

		;

		System.out.println(cf2.get());
		
		CompletableFuture<String> cf3 = CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {
				throw new RuntimeException("Test");
			}

		}).handle(new BiFunction<String, Throwable, String>() {

			@Override
			public String apply(String s, Throwable t) {
				System.out.println("return string: " + s);
				System.out.println("throwable " + t);
				return s == null ? "default" : s; // this will be passed to .get()
			}

		})

		;

		System.out.println(cf3.get());
	}

}
