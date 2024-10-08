# Learn-JAVA--multithreading

# PROCESS VS THREAD
1. Process is a software or a program which include multiple functionalities. Each process will have a separate memory space, resources allocated so that you can complete tasks. 
2. Thread is a segment of a process. In a process, multiple threads can present. Each thread can perform, for example, a functionality of the software. 
3. Threads will be working sequentially or concurrently in order to complete tasks of the process. 

# MULTITASKING
1. Means doing multiple tasks simlutenously. In computing,we want to multitasking because we want to utilise the CPU processing power efficiently. We can complete more tasks within shorter time and reduce the idle time. This comes down to handling user-request at a faster pace.
2. Multitasking includes multiprocessing and multithreading
3. Multi-processing means multiple programs/ applicaitons running concurrently on your computer. Each time you start an applications ( browser, MS word, etc) a process is created. When you start multiple applications, multiple processes are created and running cocurrently on your system. If a process is crashed, it wont affect the others. 
4. Multi-threading allows parts of the program running concurrently ( e.g: watching the video + listening to the audio of the video).One thread might be responsible for playing the video, another thread is responsible for playing the audio. If one thread is crashed, it will affect the other thread because threads need to work together to complete the major task.  

# MULTI-THREADING IN JAVA 

1. In Java, it can be achieved via Thread class. 
2. Thread scheduler decides which thread to run and which thread to wait
3. All of this are pretty similar to process scheduler in your operating system.
4. When processes are swapping so fast, we think multiple processes are running in parallel. However it is actually concurrent running. Parallelism is only possible with mulitple cores eithier on single or multiple processors.This is because each thread is a process wihci runs on a core and one core can accommodate only one thread at a time. In order to check how many available processors: 

```java
Runtime.getRuntime().availableProcessors();
```

# MAIN THREAD
1. When Java program starts, one thread is created by JVM automatically and it run immediately. This thread is "main" thread. When we write a program and run without creating a new thread, we are running in single thread environment.
2. Main thread is responsible for starting the program, setting up necs resources and spawning child threads to perform concurent tasks.   
3. Main thread is normally where we create a new thread ( spawn a new thread). The new thread created is called a child thread of the main thread
4. Main thread normally the last thread to finish execution because it performs various shutdown actions. The main thread waits for all other threads to complete their executions before it itself finishes. This is to ensure that all parallel tasks have been completed and resources are properly managed. 
5. Various shutdown actions such as: cleaning up resources ( closing files, releasing network connections); ensuring all child threads are terminated gracefully; performing final logging or cleanup tasks
6. One common way to ensure the main thread waits for child threads to complete is by using the join() method. This method blocks the main thread until the specified child thread terminates.

# TWO TYPES OF THREAD

1. User-defined thread: is responsible for the execution of something required for your program such as calculation, get output, etc.

2. Daemon thread: this thread is created when we start the program. It is created by JVM. 

In order to make a thread daemon thread: 

```java
thread.setDaemon(true);
...
```

- Characteristics of daemon thread: this thread will run in the background, hence your program will not wait for it to get completed. In other words, this is a low priority thread created by JVM to perform additional tasks such as gabage collection. Daemon thread will keep running eventhough your program has terminated. 

- When to use daemon thread: when we don't want the task to block the main task in execution. 

- One thing to note is that if we use thread.sleep() in other threads and let the daemon thread happens then it will happen before those other threads. Otherwise, if daemon has not completed its task when the main thread completed, it will keep running and not blocking the main thread, even in the case that daemon might throw an exeption. Daemon can complete its task either before or after main, but it wont affect main's operation. 

- Daemon threads are also called service provider threads which provide service to the user thread

# USER DEFINED THREAD
 Two ways of creating a Thread: extending Thread class or implementing Runnable interface. 

 ## creating a thread as a child of the Thread class

 Step 1: Create a class that extends the Thread class: 

 ```java
 public class A extends Thread {

 }
 ```

 Step 2: Override the run method of the Thread class. This is the function that we want this new child thread to run. Note that: to override any method, we needs to keep the method definition as it is, no changing to parameters or return type. 

 ```java
 public class A extends Thread {
    @Override
    public void run(){
        //code
    }
 }
 ```
Step 3: Write logic of run() method . 

Step 4: In main method, create an instance of the newly created child Thread class. At this stage, an instance of Thread class is created but still a single thread is running only which is the main thread

```java
A t1 = new A();
```

Now we want to inform JVM that t1 is the new thread created and you can execute it when you are free. To inform JVM, we call .start() method of the Thread class

Step 5: start the thread : t1.start() 

When you start() the thread t1.start() multiple times, Illegal Thread State Exception will be thrown. This is caused inside the .start() method by: 

```java
...
if (threadStatus != 0)
            throw new IllegalThreadStateException();

```

when you create a thread, the thread has "new" status. It becomes 0 when you start it. When you start() again, the status is modified from 0, so it results in !=0 and exeption is thrown. 

If no exeption is thown, private native void start0() is evoked. The return type " native" indicates that this methods involves directly with the CPU and memory allocation related. Start0 () is responsible for creating your new thread and evoke run() method. Test: if we evoke run() diretly, e.g: t1.run() without going through t1.start() --> no new thread is created. 

The run() method we overridden in the thread child class is executed instead of the parents' run method. 

After start() is called, two threads are running. Thread scheduler will handle two threads simultaneously

In order to know which Thread are running, we use:

```java
System.out.println(Thread.currentThread().getName())

```

ADVANTAGES of creating a threat by extending the thread class:  We do not need to create object of thread class in main

However, it will be less flexible if later we would like the implemented class to extend other class than Thread. 

## creating a thread as implementation of Runnable interface

Step 1: Create a class that implements Runnable interface

```java
 public class B implements Runnable {

 }
 ```

Runnable is a functional interface with only 1 abstract method : run()

Step 2: Override the run() method

Step 3: Write the logic of run() method

Step 4: In main method, create an object of the class we created in previous step

```java
main() {
    B t2 = new B();
}
```

To inform JVM about this new Thread and whenever possible execute this new thread. Since Runnable does not have any .start() method to create new thread like Thread class, we have to create an instance of Thread class to use .start()

Step 5: Create an instance of Thread class and supply the runnable instance we created in step 4 as its argument

Step 6: Start the thread

```java
main() {
    B t2 = new B();
    Thread thread = new Thread(t2);
    t2.start()
}
```

thread will be responsible for execute the task you wrote inside the Runnable implementation class. 

Whenever you provide runnable task, that task is assigned to FieldHolder in its constructor which assign your task to its task parameter

## If we have two threads both execute the same task, is there any order that threads start? 

The second thread.start() is not happening after the first thread.start() because it does not wait for the first one to finish to start its operation. 

## THREAD - PROPERTIES

1. Name: this is the thread's name and we can specify this as argument when we call an instance of the Thread class. For example: 

```java
Thread t  = new Thread ("thread1");
```

2. Priority: Every thread has priority spanning from 1 to 10. The default value if we do not specify this property is 5. Thread has higher priority run first. We can specify this: 

```java
t.setPriority(10);
```
3. Group: provide the parents' thread from which this thread is created.

![alt text](SS/image.png)

# MULTIPLE THREADS

## When to use multi-threading: I/O tasks vs CPU intensive tasks

- I/O task : for every task, there is a certain waiting time. When dealing with I/O tasks, it makes sense to have more concurrency/parallelism.
- CPU intensive task: very little waiting time. When dealing with just CPU intensive tasks, increasing thread size beyond the CPU cores will degrade the performance

In production environment, CPU cores are not limited to just 8 cores because in each core there are logical cores as well. This type of hardware allows hyperthreading. Depending on how many logical cores that the computer system have, we can define the maximum number of threads. 

**OPTIMAL NUMBER OF THREADS: is calculated using this formula**

* I/O Bound Tasks: 

Threads = number of cores * ( 1 + wait time / service time)

* CPU Bound Tasks:

Threads = number of cores + 1 

Note: wait time is close to 0 in this case

# MULTI-THREADING DEMONSTRATION: 

Questions: calculate and return factorial of a number given in a list

## Using stream and lambda expresion, we have solution : 

![alt text](SS/image1.png)

1. Now if we give bigger number list : {1000,2000,3000...10000}. Results will be 0 because their values come out of Integer range. 

2. To fix this, we need to change from int to BigInteger type

![alt text](SS/image2.png)

3. However we can notice that time taken to execute increased significantly. To reduce the time taken, we use parallel stream

```java
input.stream().parallel().forEach(num ->{
//		int res = factorial(num);
		BigInteger res = factorial(num);
		System.out.println("factorial of "+ num + " is "+ res); 
	});
```

Time taken to perform now reduced from over 500 to 365 milliseconds!

## implementing solution using Runnable 

1. Instead of writting function factorial() in main as above, we create a class implementing Runnable

![alt text](SS/image3.png)

2. In main, instead of calling factorial() function, we implement steps to create an instance of the Runnable implemented class and Thread class.

![alt text](SS/image4.png)

3. As we can see in the results above, multiple threads are created but it runs without any order. Similarly, the main thread will not wait for all spawn threads to complete their operations before it starts. In terms of Lentency, if we place start_time and end_time in main, it will not give the accurate result of how much time does it takes to complete the whole process. 

4. In order to make Main thread waits for spawn threads, we use .join() method

![alt text](SS/image5.png)

When you join(), the current thread will not execute until the thread on which the .join() method was called has completed its execution. This is particularly useful when you need to ensure that certain threads have finished their tasks before proceeding further in the program

5. Notes that even though the order of threads are not mixed up in the screenshot, the join() method only ensures that the main thread waits for spawn threads, but does not influence the order in which spawn threads execute their tasks. The execution order of the threads is determined by the JVM and the underlying OS's thread scheduler, not by the order of the join() calls. 

6. One disadvantage of using join() is that since we make the main thread to wait and each spawn thread execute in start() then join() sequence, it takes more time or increase the lentency of the program. 

7. Now to fix that, we want to make this process parallel: start() all the threads then join() later: start() start() stat() ... join() join()...join(). 
First, we will create an ArrayList of threads and whatever thread we initiate, we add to that list:

```java
Thread t = new Thread();
threads.add(t);
```

Then secondly, instead of using join() after start(), we create another stream to join all the threads we have started in the thread list. 

```java
thread.stream().forEach(thread -> thread.join());
```

As a result, the end time calculation will not be executed until we start all threads and complete all of their operations.

8. We can also enhance the performance by using parallel streams instead of iterating sequentially

![alt text](SS/image6.png)

# THREAD POOL 

This is another way of creating multiple threads environment. Instead of manually creating threads, we will use Executor Service: 

![alt text](SS/image7.png)


- When user sends requests, blocking queue holds the requests unless and untill there is a thread in Thread pool can pick up and perform the task. For example, in the thread pool, if thread 1 is in waiting stage, thread 1 will pick up a task from the blocking queue and concurrently handle two tasks. 
- When you create an executable service, you can specify number of threads you need for all the tasks you would submit to the executor service ( corepoolsize and maxpoolsize), also you can specify the size of the blocking queue - how many tasks can wait in the blocking queue (arrayblockingqueue, linkedblockingqueue,etc)

- corepoolsize: is the number of threads that you want to create
- maxpoolsize: you can expand your thread pool until this size if your threadpool is full and also is your blocking queue
- keepalivetime: if no task is assigned to a thread in the extended part of the threadpool, it will get terminated. 

## FixedThreadPool

![alt text](SS/image8.png)

- maxpoolsize = corepoolsize so keepAliveTime = 0
- time uit : seconds
- blocking queue : LinkedBlockingQueue - dynamic size which accepts all requests. Requests will never get rejected. 

The previous question can be solved using ExecutorService and FixedThreadPool:

![alt text](SS/image9.png)

- ex.submit() takes either Callable or Runnable as its parameter. Runnable is used for methods which do not have any return ( void return type). Callable is used when you want to return some value

## SingleThreadExecutor : used when you want to create just 1 thread and have sequential actions.

For example, if you want to solve the previous question with a single thread, you can either have: 

```java
ExecutorService ex = Executors.newSingleThreadExecutor();

```

OR: 

```java
ExecutorService ex = Executors.newFixedThreadPool(1);
```

## CachedThreadPool

- corepoolsize: 0
- maxpoolsize: Integer.MAX_VALUE
- keepAliveTime: 60
- unit: SECONDS
- BlockingQueue : Synchronousqueue

This type of Thread Pool keeps expanding to the maximum value of Integer type depends on number of requests sent from user.

However, Synchronousqueue can only hold 1 request and until the request gets picked up by the ThreadPool, this type of blocking queue will not hold more any request. 

## ScheduledThreadPool

- corepoolsize : n
- maxpoolsize: Integer.MAX_VALUE
- keepAliveTime: 10
- TimeUnit: SECONDS
- BlockingQueue: DelayedWorkQueue

- The DelayedWorkQueue hold the task for a given time before the ThreadPool pick it up. It is typically used in cases where tasks need to be executed after a certain delay relative to when they were submited or based on specific conditions. It is not primarily about managing threads but rather managing the timing of task execution
- Note that the return type of this Method is **ScheduledExecutorService** instead of normal ExecutorService like previous methods. This ScheduledExecutorService is a child of ExecutorService which inherited .submit() method from its parents and also have .schedule( runnableTask,delay_time, TimeUnit.SECONDS) of its own
- A scheduled thread pool is designed for scheduling tasks to run periodically or after a delay, provides flex in terms of scheduling recurring tasks, fixed interval tasks or tasks with timing patterns like every hour, every  day, etc..

**DEMONSTRATION OF SCHEDULED EXECUTOR SERVICE**

![alt text](SS/image10.png)

## SingleThreadScheduledExecutor

- corepoolsize : 1
- maxpoolsize : Integer.MAX_VALUE

This is equivalent to : newScheduledThreadPool(1)

## CUSTOM EXECUTOR SERVICE

Assume that we want: 
- corepoolsize =4
- maxpoolsize = 10
- BlockingQueue : ArrayBlockingQueue

![alt text](SS/image11.png)

1. If we change the number of submits to <30, exeption will be thrown because all threads and blockingqueue are full. To fix the issue, we change BQ type to LinkedBlockingQueue without specifiying its capacity. 

2. Which thread is responsible in case there is exeption while submittin task? 
--> main thread

## Termination of executor service

1. When we do not want to accept any more task, we use ex.shutdown() because the executor service will not automatically close itself. When we implement .shutdown(), JVM will wait for all requests in blockingqueue to finish then shutdown. It is different to **shutdownNow()** which will cause interruption to the running service. 

2. Remember that we use .join() method to make main  wait for other threads to complete before starting. There is no such .join() method in executor service. Instead, we use: 

```java
ex.awaitTermination(timeout: 4000,TimeUnit.MILLISECONDS);

```
It is pretty similar to .join() with a timeout 

3. If executor service is able to complete within the timeout period ( i.e 3 seconds) then main would start after 3 seconds; if executor service completes within 6 seconds then main starts one timeout is over (i.e 4 seconds)

4. In case you want to wait for executor service to complete then start the main, you can use .close() method

# ISSUES WITH MULTI-THREADING

## Race Condition

**Question: Let's imagine we have a library with 4 entrances. We want to implement executor service to count the total number of people who have entered the library**

1. We create a class implementing Runnable to count the number of people

![alt text](SS/image12.png)

2. Then in main: 

![alt text](SS/image13.png)

3. With small number i =20, the results is correct. However when we increase it to 20000 people, the result is 19900 ?!
![alt text](SS/image14.png)

4. This happens because of race condition: assume 2 people coming through different gates : 1 and 2, at the same time : 10 am. This will make the program to count as +1 only because : 

Thread -1 ( gate 1) :
* fetch : i = 5 (assume total number counted before above 2 people is 5)
* increment: 5++ = 6
* stored value: 6

Thread -2 ( gate 2) :
* fetch : i = 5 (assume total number counted before above 2 people is 5)
* increment: 5++ = 6
* stored value: 6

Since data is fetched to these two threads at the same time, it wont get updated correctly and both thread returns stored value of 6 people. This results in less counts than expected !

**fixing the issue with synchronized**

5. To avoid this, we will use synchronized() keywords to allow only 1 thread execute run() logic of the task at a time. The synchronized mechanism in Java is not a method but rather a keyword that provides a way to synchronize blocks of code or methods to control access by multiple threads. The synchronized keyword is handled by JVM and the Java language itself. When compiled, the Java code containing synchronized blocks or methods includes special bytecode instructions that manage the lock acquisition and release 
Bytecode Instruction: 
* monitorenter: acquires the lock
* monitorexit: releases the lock

These instructions are generated by the Java compiler whenever synchronized is used in the code.
* Decompiled Bytecode: 
Using tool like javap, we can decompile the bytecode and see the internal instruction: 

```
javap -c name_of_class_having_synchronized

```

6. When you use synchronized in Java, it involves the use of a monitor ( also called a lock). Every object in Java has an associated monitor. When a thread enters a synchronized block or method, it acquires the monitor of the specified object.

7. We will demonstrate how synchronized works by creating a synchronized task, which has Thread.sleep(2000):

![alt text](SS/image15.png)

In main, we create 2 threads which both starts on this task

![alt text](SS/image16.png)

As we can see in the result thread 1 started the task, completed then thread 2 started and completed the task. At a time, only 1 thread will execute the logic of the task.

8. Back to our Library Counting task issue, there are several ways that we can implement synchronized keyword in Runnable implementing class: 

- OPTION 1: synchronized method - we replace count++ in run() method with a synchronized method - here we create and call it incrementCount()

```java
@Override
public void run(){
    incrementCount();
}

private synchronized void incrementCount(){
    count++;
}
```

- OPTION 2: synchronized block of code

```java
@Override
public void run(){
    incrementCount();
}
private void incrementCount(){
    synchronized(this){
        count++;
    }
}
```

**NOTE**: Synchronized method is less favourable because synchronize has disadvantage that it slows down the process or introduces some lentency. Hence, when synchronized is unavoidable, for example, when we use Singleton pattern which only allows creation of 1 instance of the Singleton class at a time. In such case, we will limit the scope of synchronized as much as possible using only synchronized block of code

In libraryCountTask: 

```java
package ExecutorService;

public class LibraryCountTask implements Runnable{

	private int count;
	
	public LibraryCountTask() {
		count = 0;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public void run() {
		incrementCount();
		
	}

	private void incrementCount() {
		synchronized(this) {
			count++;
		}
		
	}
	
}
```

Now the results return 20000 people correctly.

**how synchronized keyword is used in Singleton**

In multithreading environment, even with null check condition, if 2 threads at the same time evoke the getInstance() method - the global method of Singleton class and if at that time instance == null, both threads will try to create new instance of Logger class. 

As mention above, we can either use synchronized keyword in method declaration, for example : public synchronised static Logger getInstance() OR we add a synchronized block inside the method: 
```java
synchronized(Logger.class){
    if(instance == null){
    instance = new Logger();
    }
}
```
NOTE: in Java, every class has a corresponding "class" object that represents the class' meta data. This class object is shared among all instances of that class. It is unique for a given calss within a JVM. This, Logger.class referes to the same object no matter where it is referenced. 
By synchronizing on the "Logger.class" object, we are efectively ensure that the sync block is executed one thread at a time across all instances of the 'Logger' class. This is because the JVM uses the Logger.class object as a lock and allows only 1 thread to hold the lock at any given moment. Only the thread that holds the lock can evoke the instantiation.

**thread-safe singleton with synchronized block**
```java
public class Logger{
    private static Logger instance;

    //private constructor to prevent instantiation
    private Logger(){

    }

    public static Logger getInstance(){
        if(instance == null){
            synchronized(Logger.class){
                if(instance == null){ // Double-checking locking
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(String message){
        synchronized(Logger.class){
            //Code to write the log message to a file or console
            System.out.println(message);
        }
    }
}
```
**fixing the issue with atomic data type**

1. Instead of using "private int count;", now we can change it to " private AtomicInteger count;"

2. In constructor: 

```java
count = new AtomicInteger(0);
```

3. In getCount() method: 

```java
return count.get();

```
4. In run() method: 

```java
count.addAndGet(delta: 1);

```

FULL CODE: 

```java
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
```
Using Atomic data type avoids issue with Synchronized that it improves performance of process. 

Using Atomic also helps with data inconsitency issue
## Stale data/ data inconsistency

Data inconsistency happens when  there are two or more threads, for example, t1 and t2 working on the same data at the same time. What happens is that T1 will not get any updates from what T2 is working on, because T2 has its own copy of the data. 

We can demonstrate how this happen by creating a process that involves two tasks: 
-  Read data and Write data performing on a single common data source. 
-  Read-data is only evoked when the data is changed. This happens only when data is updated by the Write-data method. Hence the connectness of Read-data result depends on whether Read-data happens at the right time, right after Write-data is done. 
-  These two tasks will be performed by different Threads 

```java
package ExecutorService;

public class RunnableMemoryConsistency implements Runnable {

	// specify the type of task: "R" or "W"
	private String type;

	// this represents the data shared between instances of the class
	private static int data = 0;

	RunnableMemoryConsistency(String type) {
		this.type = type;
	}

	// if "W" : increment data to 5
	// if "R" : read the data whenever it is incremented

	@Override
	public void run() {
		if ("W".equals(type)) {
			while (data != 5) {
				data++;
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
				if (temp != data) {
					temp = data;
					System.out.println("Have read new data: " + temp);
				}
			}
		}

	}

}
```

We use ExecutorService to create a FixedThreadPool(2): 

![alt text](SS/image17.png)

As you can see the return results, Read-data is not correctly evoked  when data is updated to 4, and it is even called before Write-data's operation. Notice that we even implement Thread.sleep(1000) in Write-data to wait for Read-data to update value.

Since these two tasks are performed by two different threads, operated in different locations in system memory on different copies of the data, data information is reported inconsistently and incorrectly. 

**fixing the issue with volatile data**

By using volatile type data, we can keep the data used for tasks' operation at the same place so there is no local copies which are maintained by multiple threads. 

![alt text](SS/image18.png)

We can see now the result is read correctly. 

NOTE: data inconsistency is different to race condition. In previous example about race condition, we have 1 instance of Runnable implementing class being run on 2 different Threads to perform the same task. In this example, we have two instances of the Runnable implementing class being submited and run on 2 different Threads to perform a sequence of two tasks. 

**fixing the issue with atomic data type**

Though data inconsistency is different to race condition, it can both happen in a process. Using atomic data type ( like previously shown) handle them both. However using valatile data, we can only handle data inconsistency issue. 

![alt text](SS/image19.png)

NOTE: when dealing with basic data type such as int, long, double, etc, it is recommended to use Atomic data type. However when you have custom built object and you want to make sure multiple threads are not updating the value at the same time, we need to use **optimistic locking**

The use of some database such as DynamoDB provides abilities to use optimistic locking and transactions ( will be discussed later in toptic about DB connection)

# CALLABLE

Callable works as the same maner as Runnable in creating tasks that can be submited to a threadpool. 

With callable, you can specify the checked exception that you can throw and also the return type of your method. 

![alt text](SS/image20.png)

**callable V/S runnable**

* Callable tasks can only be submited to ExecutorService, it cannot be used with a custom created Thread. 
* Runnable implementing class has void return type, while callable has Future<T> return type. Future data type is used when you want to store the return value for future use ( by calling .get() method )

**timeout**
* In the example, the string res will wait for 1 second before starting next line
* we can also specify waiting time : 
```java
String res1 = res.get(500,TimeUnit.MILLISECONDS);

```
NOTE: The .get() call only blocks the thread that calls it(the main thread), it does not block other threads in ExecutorService from running.

#  FUTURE, COMPLETABLE FUTURE

## FUTURE

Future provides a way to retrieve the result of a computation once it is completed

Methods of Futures include: 
1) .get() : retrieve the result of the computation, waiting if it is necessary for the computation to finish
2) .get(long timeout, TimeUnit.unit): retrieve the result, waiting up to the specified timeout if necessary
3) .isDone(): checks if the computation is completed

Issues with Futures: 
1) it is impossible to chain multiple Futures together. In other words, you canno easily create a sequence of dependent asynchronous task where the output of one task is fed into the next.
2) Lack of exception handling
3) Everytime when you  want a response from Future call, you must block the main thread then invoke the next.

## COMPLETABLE FUTURE
Completable Future represents a future result of an asynchronous computation. It resolves issues that previously unable to handle with Future. 

**CHAINING MULTIPLE FUTURES**

Completable Future provides callbalck functions which allow you to specify actions that should be taken when the future is completed after runasync() or supplyasync() 

**runasync() : is used when you do not want to return anything from your code

![alt text](SS/image21.png)

**supplyasync() : is used when you want to return something from your code

```java
	CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {
				// TODO Auto-generated method stub
				return "SUPPLY ASYNC";
			}
			
		});
```
We notice that since cf1 does not have any further callback functions after supplyAsync(), return of get() method should match to whaever type you specify in CompletableFuture declaration for cf1. 

If you do have callback functions, the type you declared for cf1 does not depend on what .supplyAsync is returning, it depends on what **your last method of execution returning**.

Now if you want to perform further actions on the response of supplyAsync, Completable Future provides these callback function to create a chain of operations; 

1. thenApply(): take output of supplyAsync and perform transformation on it and return back another output

```java
			CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(new Supplier<String>() {

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

		});

		System.out.println(cf1.get());
	}
```
NOTE: if we dont call response with .get(), main() can start before .thenApply() or whatever the callback functions. This is because supplyAsync() and thenApply() is running on other thread than main thread. However, supplyAsync() and thenApply() are running on the same thread ( just not the main thread)

2. thenAccept() : takes the response from previous operations either supplyAsync or thenApply(), return nothing ( VOID return type). This operation is used over .thenApply(), for example, when you are getting a response after evoking an API call, you just want to add it to a List of responses to use later and dont want to return anything.

```java
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
	}

	
}
```

3. thenRun(): does not take the response from previous operation, and does not return anyhing. This is used over thenAccept() and thenApply() when you want to perform an action which is independent of the response from previous step, e.g, print out metrics. 

**MULTITHREADING IN COMPLETABLE FUTURE**

We notice that all callback functions mentioned above are running on the same thread

Now if we want it to be on different thread, we will create ExecutorService, for example: 
```java
ExecutorService ex = Executors.newFixedThreadPool(4);
```
Next, we add the executor service as an argument of supplyAsync, for example: 

```java
CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(new Supplier<String>() {

			@Override
			public String get() {
				// TODO Auto-generated method stub
				return "SUPPLY ASYNC";
			}
			
		}, ex );
```

About the remaining callback functions, we use: .thenApplyAsync() instead of thenApply(), thenAcceptAsync() instead of thenAccept(), and thenRunAsync() instead of thenRun(). 

By including executor service as an argument, call back functions will use a thread created from the executor service. We can now control how many thread you are creating. 

One important note is that eventhough each of the method is using different Thread, they still execute in order we wrote. 

**EXCEPTION HANDLING**

Notice that whatever exception we stated at main method, we will throw errors at .get() method. 

For example, we have a Runtime Exception in supplyAsync like below: 

![alt text](SS/image22.png)

However now we can use: . exceptionally() and .handle() to provide a way to handle such exception. 

1. exceptionally() : can be used after supplyAsync() or any other methods to handle exception. It will only be evoked when there is an exception. 

![alt text](SS/image23.png)

As we can see, now .get() will not throw any exception and exception from previous supplyAsync operation is captured by Throwable t. 

2. handle() : different to exceptionally(), handle() will always be evoked because it has two arguments : a return value if previous operations run successfully, and a throwable if there is any exception occurs. 

![alt text](SS/image24.png)

- return value "s" in the code is the return value from previous operation supplyAsync. s is null because the previous operation returns an exception.

- NOTE: both .exceptionally() and .handle() is not necessary the last block, it can be put multiple times after any callback methods to handle exception. 

- If we want to handle resource, we should put inside a try() block rather than relying on Completable Future's methods to ensure resources to be cleaned up for closed once operations are completed, regardless of the outcome ( either exception or a valid return value). While .exceptionally() and handle() can handle exceptions, they do not manage the resources directly, which leads to complex code because resource can open longer than required when .handle() deals with multiple potential outcomes.In this case, try-with-resource guarantees that resources are closed in all execution paths. 

