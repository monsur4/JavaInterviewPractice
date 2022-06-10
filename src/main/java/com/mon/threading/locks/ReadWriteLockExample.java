package com.mon.threading.locks;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static String message = "a";

    public static void main(String[] args) {
        Thread t1 = new Thread(new WriterA(), "Writer A");
        Thread t2 = new Thread(new WriterB(), "Writer B");
        Thread t3 = new Thread(new Reader(), "Reader");

        t1.start();
        t2.start();
        t3.start();

        try{
            t1.join();
            t2.join();
            t3.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    static class Reader implements Runnable{

        @Override
        public void run() {
            if(lock.isWriteLocked()){
                System.out.println("Write lock Present.");
            }
            lock.readLock().lock(); // multiple threads can take control of the readlock as long as the writelock has not been activated by some other thread
            // so many people can read as long as someone is not already writing

            try{
                Long duration = (long)(Math.random() * 10000);
                System.out.println(Thread.currentThread().getName() + " Time taken " + (duration/1000) + " seconds.");
                Thread.sleep(duration);
                System.out.println(Thread.currentThread().getName() + " end");
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName() + ": " + message);
                lock.readLock().unlock();
            }

        }
    }

    static class WriterA implements Runnable{
        @Override
        public void run() {
            lock.writeLock().lock(); // if no thread has taken control of the readlock or writelock, then control is passed to this thread
            // only one person can write as long as someone is not already reading or writing
            try{
                Long duration = (long) (Math.random() * 10000);
                System.out.println(Thread.currentThread().getName() + " Time taken " + (duration/1000) + " seconds.");
                Thread.sleep(duration);
                System.out.println(Thread.currentThread().getName() + " end");
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                message = message.concat("a");
                lock.writeLock().unlock();
            }
        }
    }

    static class WriterB implements Runnable{
        @Override
        public void run() {
            lock.writeLock().lock();
            try{
                Long duration = (long) (Math.random() * 10000);
                System.out.println(Thread.currentThread().getName() + " Time taken " + (duration/1000) + " seconds.");
                Thread.sleep(duration);
                System.out.println(Thread.currentThread().getName() + " end");
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                message = message.concat("b");
                lock.writeLock().unlock();
            }
        }
    }
}
