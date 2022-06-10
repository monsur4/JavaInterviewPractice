package com.mon.threading;

public class MySecondThread implements Runnable{
    @Override
    public void run() {
        System.out.println("Starting: MySecondThread>>>>>>>>>>>>>>>>>>>>");
        try {
            // if a thread is in the sleeping, joining or waiting state, and its interrupted flag gets set to true, then
            // and InterruptedException is thrown
            Thread.sleep(5000);
            // Thread.interrupted() >>> sets this threads interrupted state to false; also returns the former value
        } catch (Exception e) {
            e.printStackTrace();
        }

//        if(Thread.currentThread().isInterrupted()){
//            System.out.println("interrupting thread");
//            return;
//        }
        System.out.println("Ending: MySecondThread>>>>>>>>>>>>>>>>>>>>");
    }
}
