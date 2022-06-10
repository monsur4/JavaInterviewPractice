package com.mon.threading;

public class MyThread extends Thread{
    @Override
    public void run() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("Running my thread");
    }

    public void run(int num){
        System.out.println("Overloaded run method");
    }

    @Override
    public synchronized void start() {
        super.start();
        run(7);
    }
}
