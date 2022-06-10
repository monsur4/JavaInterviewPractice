package com.mon.threading.locks;

import java.util.LinkedList;
import java.util.Queue;

public class ExecutionQueue {
    static Queue<Integer> queue = new LinkedList<>();
    private Object notFull = new Object();
    private Object notEmpty = new Object();
    /*ReentrantLock lock = new ReentrantLock(true);
    Condition notFull = lock.newCondition();
    Condition notEmpty = lock.newCondition();*/
    public void add(int num){
        // lock.lock();
        /*if (queue.size() == 10){
            try {
                //Thread.sleep(3000);
                notFull.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.add(num);
        notEmpty.signalAll();
        lock.unlock();*/

        /*try{
            if (queue.size() == 10) {
                notFull.await();
            }
            queue.add(num);
            notEmpty.signalAll();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }*/
//        synchronized (notEmpty){
//
//        }
        try{
            synchronized (notFull){
                if (queue.size() == 10) {

                    notFull.wait();
                }
            }

            synchronized (notEmpty){
                queue.add(num);
                notEmpty.notifyAll();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public int remove() {
        // lock.lock();
        /*while (queue.size() == 0) {
            try{
                //Thread.sleep(3000);
                notEmpty.await();
            }catch (InterruptedException e){}
        }
        Integer n = queue.remove();
        notFull.signalAll();
        lock.unlock();
        return n;*/
        /*Integer n = -1;
        try{
            //Thread.sleep(3000);
            while (queue.size() == 0) {
                notEmpty.await();
            }
             n = queue.remove();
            notFull.signalAll();
            return n;
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //notFull.signalAll();
            lock.unlock();
        }

        return n;*/

        Integer n = -1;
        try{
            //Thread.sleep(3000);
            synchronized (notEmpty){
                while (queue.size() == 0) {

                    notEmpty.wait();
                }
            }

            synchronized (notFull){
                n = queue.remove();
                notFull.notifyAll();
            }
            return n;
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        return n;
    }
}

class Producer implements Runnable{
    ExecutionQueue executionThread;
    public Producer(ExecutionQueue executionThread){
        this.executionThread = executionThread;
    }
    public void run(){
        for(int i = 0; i<100; i++){
            try {
                System.out.println("Starting: Adding "+ i+ " to list");
                Thread.sleep(3000);
                executionThread.add(i);
                System.out.println("Ending: Adding "+ i+ " to list");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

class Consumer implements Runnable{
    ExecutionQueue executionThread;
    public Consumer(ExecutionQueue executionThread){
        this.executionThread = executionThread;
    }
    public void run(){
        try {
            System.out.println("Starting: Removing from list");
            Thread.sleep(3000);
            executionThread.remove();
            System.out.println("Ending: Removing from list");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
