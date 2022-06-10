package com.mon.threading.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
    public static void main(String[] args) throws InterruptedException {
        /*AtomicInteger atomicInteger = new AtomicInteger(0);
        //System.out.println(atomicInteger.incrementAndGet());
//        System.out.println(atomicInteger.compareAndSet(4, 5));
//        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.compareAndExchange(0, 7));
        System.out.println(atomicInteger.get());*/

        final Counter counter = new Counter();
        for(int i=0; i<1000; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    counter.increment();
                }
            }).start();
        }
        Thread.sleep(6000);
        System.out.println("Final number (should be 1000): " + counter.value());


    }

    /*static class Counter{
        private int c = 0;

        public void increment(){
            c++;
        }

        public int value(){
            return c;
        }
    }*/

    static class Counter{
        private AtomicInteger c = new AtomicInteger();

        public void increment(){
            c.incrementAndGet();
        }

        public int value(){
            return c.intValue();
        }
    }
}
