package com.mon.threading.locks;

public class DeadLockTest {
    public static Object lock1 = new Object();
    public static Object lock2 = new Object();

    public static void main(String[] args) {
        ThreadDemo1 threadDemo1 = new ThreadDemo1();
        ThreadDemo2 threadDemo2 = new ThreadDemo2();
        threadDemo1.start();
        threadDemo2.start();
    }

    private static class ThreadDemo1 extends Thread{
        public void run(){
            synchronized (lock1){
                System.out.println("ThreadDemo1 holding lock1");
                System.out.println("ThreadDemo1 waiting for lock2");
                synchronized (lock2){
                    System.out.println("ThreadDemo1 holding lock1 and lock2");
                }
            }
        }
    }

    private static class ThreadDemo2 extends Thread{
        public void run(){
            synchronized (lock2){
                System.out.println("ThreadDemo2 holding lock2");
                System.out.println("ThreadDemo2 waiting for lock1");
                synchronized (lock1){
                    System.out.println("ThreadDemo2 holding lock2 and lock1");
                }
            }
        }
    }
}
