package com.mon.threading.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CachedThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) executorService;

        // Stats before execution
        System.out.println("Maximum Allowed Threads: " + poolExecutor.getMaximumPoolSize());
        System.out.println("Largest Pool Size: " + poolExecutor.getLargestPoolSize());
        System.out.println("Current Threads in Pool: " + poolExecutor.getPoolSize()); // it might not be all the threads in the pool that are currently executing
        System.out.println("Currently Executing Threads: " + poolExecutor.getActiveCount());
        System.out.println("Total number of tasks: " + poolExecutor.getTaskCount());

        executorService.submit(new Task());
        executorService.submit(new Task());

        // Stats after execution
        System.out.println("Core pool size: " + poolExecutor.getCorePoolSize());
        System.out.println("Maximum Allowed Threads: " + poolExecutor.getMaximumPoolSize());
        System.out.println("Largest Pool Size: " + poolExecutor.getLargestPoolSize());
        System.out.println("Current Threads in Pool: " + poolExecutor.getPoolSize());
        System.out.println("Currently Executing Threads: " + poolExecutor.getActiveCount());
        System.out.println("Total number of tasks: " + poolExecutor.getTaskCount());

        executorService.shutdown();
    }



    static class Task implements Runnable{

        @Override
        public void run() {
            try{
                Long duration = (long)(Math.random() * 5);
                System.out.println("Running Task! Thread Name: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(duration);
                System.out.println("Task Completed! Thread Name: " + Thread.currentThread().getName());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
