package MultipleThreads;


import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class ClientFactorial {
	
public static void main(String[] args) {
	
	long start = System.currentTimeMillis();
	
//	List<Integer>input = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
	
	List<Integer>input = Arrays.asList(1000,2000,3000,4000,5000,6000,7000,8000,9000,10000);
	
	input.stream().parallel().forEach(num ->{
//		int res = factorial(num);
		BigInteger res = factorial(num);
		System.out.println("factorial of "+ num + " is "+ res); 
	});
	
	long end = System.currentTimeMillis();
	
	System.out.println("Time taken for this operation is :" + (end - start) + " milliseconds");
	
	
	
}
//public static int factorial(int num) {
public static BigInteger factorial(int num) {
//	int fact =1;
	BigInteger fact = BigInteger.ONE;
	for (int i =1;i<=num;i++) {
//		fact = fact *i;
		fact = fact.multiply(BigInteger.valueOf(i));
	}
	return fact;
}
}
