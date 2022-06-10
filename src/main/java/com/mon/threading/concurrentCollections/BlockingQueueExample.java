package com.mon.threading.concurrentCollections;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

        new Thread(new Producer(blockingQueue)).start();
        new Thread(new Consumer(blockingQueue)).start();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Producer implements Runnable{
        private final BlockingQueue<Integer> queue;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            Random random = new Random();

            try{
                int result = random.nextInt(100);
                Thread.sleep(1000);
                queue.put(result);
                System.out.println("Added: " + result);

                result = random.nextInt(100);
                Thread.sleep(1000);
                queue.put(result);
                System.out.println("Added: " + result);

                result = random.nextInt(100);
                Thread.sleep(1000);
                queue.put(result);
                System.out.println("Added: " + result);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable{
        private final BlockingQueue<Integer> queue;
        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try{
                System.out.println("Removed: " + queue.take()); // take() will wait till forever for an element to be available to be removed
                System.out.println("Removed: " + queue.take());
                System.out.println("Removed: " + queue.take());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
