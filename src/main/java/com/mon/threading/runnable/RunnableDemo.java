package com.mon.threading.runnable;

public class RunnableDemo implements Runnable{
   public Thread t;
    private String threadName;
    boolean suspended = false;
    public RunnableDemo(String name) {
        this.threadName = name;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);

        try{
            for (int i = 10; i>0; i--){
                System.out.println("Thread " + threadName +", " +i);
                Thread.sleep(300);
                synchronized (this){// wait and notify must be called in a synchronized block to avoid illegal monitor state exception
                    while(suspended){
                        wait();
                    }
                }
            }
        }catch (InterruptedException  e){
            System.out.println("Thread " + threadName +" interrupted.");
        }
        System.out.println("Thread " + threadName +" exiting.");
    }

    public void start(){
        System.out.println("Starting " + threadName);

        if(t == null){
            t = new Thread(this, threadName);
            t.start();
        }
    }

    public void suspend(){
        this.suspended = true;
    }

    public synchronized void resume(){
        this.suspended = false;
        notify();
    }
}

class TestThread{
    public static void main(String[] args) {
        RunnableDemo R1 = new RunnableDemo("Thread-1");
        R1.start();
        RunnableDemo R2 = new RunnableDemo("Thread-2");
        R2.start();

        try{
            Thread.sleep(1000);
            R1.suspend();
            System.out.println("Suspending First Thread");
            Thread.sleep(1000);
            R1.resume();
            System.out.println("Resuming First Thread");

            R2.suspend();
            System.out.println("Suspending Second Thread");
            Thread.sleep(1000);
            R2.resume();
            System.out.println("Resuming Second Thread");

        }catch (InterruptedException e){
            System.out.println("Main thread Interrupted.");
        }
        try{
            System.out.println("Waiting for threads to finish.");
            R1.t.join();// makes the parent thread (main thread) sleep and wait for the child thread to complete execution
            R2.t.join();
        }catch (InterruptedException e){
            System.out.println("Main thread interrupted");
        }
        System.out.println("Main thread exiting");
    }
}
