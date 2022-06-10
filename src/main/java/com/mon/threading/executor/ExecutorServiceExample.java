package com.mon.threading.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        try{
            executorService.submit(new Task());
            System.out.println("Shutdown executor");
            executorService.shutdown();
            System.out.println("trying to shutdown");
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            System.err.println("tasks interrupted");
        }finally {
            if(!executorService.isTerminated()){
                // isTerminated returns true if all tasks have completed following shutdown
                // if timeout occurs but the before shutdown is completed, then the executor thread is considered to have not terminated
                // termination occurs if shutdown is complete
                // The fact that all the lines in the executor thread runs doesn't mean it shutdown completely. Shutdown is a different thing.
                System.err.println("make sure you cancel all non-finished tasks");
            }
            executorService.shutdownNow();
            System.out.println("Shutdown finished");
        }
    }

    static class Task implements Runnable{

        @Override
        public void run() {
            try{
                Long duration = (long)(Math.random() * 20);
                System.out.println("Running task");
                System.out.println("duration: " +duration);
                TimeUnit.SECONDS.sleep(duration);
                System.out.println("End of running task in thread");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
