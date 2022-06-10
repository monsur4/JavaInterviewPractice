package com.mon.threading.utilityclass;

import java.util.concurrent.ThreadLocalRandom;

class RunnableTest implements Runnable {
    int counter=0;
    ThreadLocal<Integer> threadLocalCounter = new ThreadLocal<>();

    public synchronized void run(){
        counter++;
        if(threadLocalCounter.get() != null){
            threadLocalCounter.set(threadLocalCounter.get() + 1);
        }else{
            threadLocalCounter.set(0);
        }
        System.out.println("Counter: " + counter);
        System.out.println("threadLocalCounter: " + threadLocalCounter.get());
        System.out.println("threadLocalRandom: " + ThreadLocalRandom.current().nextInt(7, 76));
    }
}
public class ThreadLocalExample {
    public static void main(String[] args) {
        RunnableTest commonInstance = new RunnableTest();
        Thread t1 = new Thread(commonInstance);
        Thread t2 = new Thread(commonInstance);
        Thread t3 = new Thread(commonInstance);
        Thread t4 = new Thread(commonInstance);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }catch (InterruptedException e){
            System.out.println("Interrupted");
        }
    }

}
