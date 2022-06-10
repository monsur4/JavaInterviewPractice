package com.mon.threading.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample {

    public static void main(String[] args) {
        int[] arr = new int[1000];

        for(int i = 0; i < arr.length; i++){
            arr[i] = i;
        }

        int noOfThreads = Runtime.getRuntime().availableProcessors();

        ForkJoinPool forkJoinPool = new ForkJoinPool(noOfThreads);
        Long result = forkJoinPool.invoke(new Sum(arr, 0, arr.length));

        System.out.println("No of processors: " + noOfThreads);
        System.out.println("Result of tasks: " + result);
    }

    static class Sum extends RecursiveTask<Long>{
        private final int[] array;
        private final int lo;
        private final int hi;

        public Sum(int[] array, int lo, int hi) {
            this.array = array;
            this.lo = lo;
            this.hi = hi;
        }

        @Override
        protected Long compute() {
            if(hi - lo <= 10){
                long sum = 0;
                for(int i = lo; i < hi; i++){
                    sum += array[i];
                }
                return sum;
            }else{
                int mid = (lo + hi) / 2;
                Sum left = new Sum(array, lo, mid);
                left.fork(); // splits the left half off to a separate thread and computes it
                Sum right = new Sum(array, mid, hi);
                long rightResult = right.compute(); // runs the right half on this same thread
                long leftResult = left.join(); // retrieves the result of the left half, therefore it keeps waiting for the subtasks to return result
                return leftResult + rightResult;
            }
        }
    }
}
