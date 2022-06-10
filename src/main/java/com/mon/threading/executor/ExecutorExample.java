package com.mon.threading.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorExample {

    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(new Task()); // sort of like creating a new thread and starting the task on the thread
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) executor;
        poolExecutor.shutdown();
        System.out.println("Done from  main");
    }

    static class Task implements Runnable{

        @Override
        public void run() {
            try{
                Long duration = (long) (Math.random()*5);
                System.out.println("Running Task!");
                TimeUnit.SECONDS.sleep(duration);
//                Thread.sleep(duration);
                System.out.println("Task Completed");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
