package com.mon.threading.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FixedThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Cast the object to the ThreadPool type
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) executorService;

        // Stats before task execution
        System.out.println("Largest executions at a single time: " + poolExecutor.getLargestPoolSize());
        System.out.println("Maximum allowed threads: " + poolExecutor.getMaximumPoolSize());
        System.out.println("Current threads in pool: " + poolExecutor.getPoolSize());
        System.out.println("Currently executing threads: " + poolExecutor.getActiveCount());
        System.out.println("Total number of threads(ever scheduled): " + poolExecutor.getTaskCount());

        executorService.submit(new Task());
        executorService.submit(new Task());

        // Stats after task execution
        System.out.println("Core threads: " + poolExecutor.getCorePoolSize());
        System.out.println("Large executions at a single time: " + poolExecutor.getLargestPoolSize());
        System.out.println("Maximum allowed threads: " + poolExecutor.getMaximumPoolSize());
        System.out.println("Current threads in pool: " + poolExecutor.getPoolSize());
        System.out.println("Currently executing threads: " + poolExecutor.getActiveCount());
        System.out.println("Total number of threads(ever scheduled): " + poolExecutor.getTaskCount());

        executorService.shutdown();

    }

    static class Task implements Runnable{

        @Override
        public void run() {
            try{
                Long duration = (long) (Math.random() * 5);
                System.out.println("Running Task! Thread Name: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(duration);
                System.out.println("Task Completed! Thread Name: " + Thread.currentThread().getName());

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
