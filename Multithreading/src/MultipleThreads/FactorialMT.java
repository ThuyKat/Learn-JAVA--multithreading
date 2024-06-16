package MultipleThreads;

import java.math.BigInteger;

public class FactorialMT implements Runnable {

	//to be able to use an argument for run(), we specify it here as a class variable because 
	//to override run() method we have to use the same method definition. We are not able to add
	//arguments. 
	private int num;
	
	public FactorialMT(int num){
		this.num = num;
	}
	
	@Override
	public void run() {
		
		BigInteger fact = BigInteger.ONE;
		for(int i =1; i<=num;i++) {
			fact = fact.multiply(BigInteger.valueOf(i));
		}
		
		System.out.println("I am in FactorialMT, current thread for " + num + " is " + Thread.currentThread().getName());
		System.out.println("factorial of " + num + " is "+ fact);
	}


}
