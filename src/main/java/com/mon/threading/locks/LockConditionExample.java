package com.mon.threading.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// implementing Producer - Consumer pattern
public class LockConditionExample {

    public static void main(String[] args) {
        ItemQueue itemQueue = new ItemQueue(10);

        Thread producer = new Producer(itemQueue);
        Thread consumer = new Consumer(itemQueue);

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class ItemQueue{
        private Object[] items = null;
        private int current = 0;
        private int placeIndex = 0;
        private int removeIndex = 0;

        private Lock lock;
        private Condition isEmpty;
        private Condition isFull;

        public ItemQueue(int capacity){
            items = new Object[capacity];
            lock = new ReentrantLock();
            isEmpty = lock.newCondition();
            isFull = lock.newCondition();
        }

        public void add(Object item) throws InterruptedException{
            lock.lock();

            while(current >= items.length){ // if the array gets full, then wait for the isFull signal to be notified
                    isFull.await();
                System.out.println("waiting at " + current);
            }

            items[placeIndex] = item;
            placeIndex = (placeIndex + 1) % items.length;
            ++current;
            isEmpty.signal();// after an item is added to the array, notify the isEmpty condition that the array is not empty

            lock.unlock();
        }

        public Object remove() throws InterruptedException{
            lock.lock();

            while (current <= 0){
                isEmpty.await();
            }

            Object item = items[removeIndex];
            removeIndex = (removeIndex + 1) % items.length;
            --current;
            isFull.signal(); // since, we removed an item, therefore at this point the array cannot be full;

            lock.unlock();
            return item;
        }

        public boolean isEmpty(){
            return (items.length == 0);
        }
    }

    static class Producer extends Thread{
        private final ItemQueue queue;

        public Producer(ItemQueue queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
            try {
                for(String number: numbers){
                    System.out.println("[Producer]: " + number);
                    queue.add(number);
                }
                queue.add(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer extends Thread{
        private final ItemQueue queue;

        public Consumer(ItemQueue queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            try{
                do{
                    Object number = queue.remove();
                    System.out.println("[Consumer]: " + number);

                    if(number == null){
                        return;
                    }
                }while (!queue.isEmpty());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
