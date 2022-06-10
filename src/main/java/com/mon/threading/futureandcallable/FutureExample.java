package com.mon.threading.futureandcallable;

import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        System.out.println("Solving factorial of 10");
        Future<Long> factorial10 = executorService.submit(new Factorial(10));

        System.out.println("Solving factorial of 20");
        Future<Long> factorial20 = executorService.submit(new Factorial(20));

        try {
            System.out.println("10! = " + factorial10.get());
            System.out.println("20! = " + factorial20.get());
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }

        executorService.shutdown();
    }

    static class Factorial implements Callable<Long>{
        private final int num;
        public Factorial(int num) {
            this.num = num;
        }

        @Override
        public Long call() throws Exception {
            return factorial(num);
        }

        private Long factorial(int number){
            long factorial = 1;

            while(number > 0){
                factorial = factorial * number;
                number--;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return factorial;
        }
    }
}
