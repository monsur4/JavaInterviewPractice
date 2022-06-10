package com.mon.staticblocksandgenerics;

public class Manipulator {
    static int[] arr;
    public Manipulator(){
        arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    }

    public synchronized void removeIndex(int i){
        System.out.println("<<<<<<<<<<<<<<<<<starting:::: removing from the manipulator>>>>>>>>>>>>>>>");
        try {
//            addAtIndex(1, 4);
            Thread.sleep(3000);
            System.out.println("Priority of thread named "+ Thread.currentThread().getName()+" is "+Thread.currentThread().getPriority());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        arr[i] = -1;
        System.out.println("<<<<<<<<<<<<<<<<<ending:::::: removing from the manipulator>>>>>>>>>>>>>>>");
    }

    public synchronized void addAtIndex(int index, int val){
        System.out.println("<<<<<<<<<<<<<<<<<starting:::: adding to the manipulator>>>>>>>>>>>>>>>");
        if(index < 0 || index > arr.length-1){
            throw new IllegalArgumentException("index out of bounds of the array");
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        arr[index] = val;
        System.out.println("<<<<<<<<<<<<<<<<<ending:::::: adding to the manipulator>>>>>>>>>>>>>>>");
    }

    /*public void removeIndex(int i){
        // System.out.println(Thread.currentThread());
        synchronized (this){
            System.out.println("<<<<<<<<<<<<<<<<<starting:::: removing from the manipulator>>>>>>>>>>>>>>>");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arr[i] = -1;
            System.out.println("<<<<<<<<<<<<<<<<<ending:::::: removing from the manipulator>>>>>>>>>>>>>>>");
        }
    }

    public void addAtIndex(int index, int val){
        // System.out.println(Thread.currentThread());
        synchronized (this){
            System.out.println("<<<<<<<<<<<<<<<<<starting:::: adding to the manipulator>>>>>>>>>>>>>>>");
            if(index < 0 || index > arr.length-1){
                throw new IllegalArgumentException("index out of bounds of the array");
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            arr[index] = val;
            System.out.println("<<<<<<<<<<<<<<<<<ending:::::: adding to the manipulator>>>>>>>>>>>>>>>");
        }
    }*/

//    public void removeIndex(int i){
//        synchronized (Manipulator.class){
//            System.out.println("<<<<<<<<<<<<<<<<<starting:::: removing from the manipulator>>>>>>>>>>>>>>>");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            arr[i] = -1;
//            System.out.println("<<<<<<<<<<<<<<<<<ending:::::: removing from the manipulator>>>>>>>>>>>>>>>");
//        }
//    }
//
//    public void addAtIndex(int index, int val){
//        synchronized (Manipulator.class){
//            System.out.println("<<<<<<<<<<<<<<<<<starting:::: adding to the manipulator>>>>>>>>>>>>>>>");
//            if(index < 0 || index > arr.length-1){
//                throw new IllegalArgumentException("index out of bounds of the array");
//            }
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            arr[index] = val;
//            System.out.println("<<<<<<<<<<<<<<<<<ending:::::: adding to the manipulator>>>>>>>>>>>>>>>");
//        }
//    }
    public static void main(String[] args) {
    /*GenericStatic genericStatic = new GenericStatic("answer");
    GenericStatic genericStatic2 = new GenericStatic(34);
        System.out.println(genericStatic.getData());
        System.out.println(genericStatic2.getData());*/
/*
        GenericStatic2 genericStatic2 = new GenericStatic2("answer1", "answer2");
        GenericStatic2 genericStatic21 = new GenericStatic2("answer3", 33);
        GenericStatic2 genericStatic22 = new GenericStatic2(899, 33);
        System.out.println(genericStatic2.getFirstData());
        System.out.println(genericStatic2.getSecondData());
        System.out.println(genericStatic21.getFirstData());
        System.out.println(genericStatic21.getSecondData());*/

        StaticBlock2 sb = new StaticBlock2(88);
        GenericStatic3 genericStatic3 = new GenericStatic3(sb);
        StaticBlock s = genericStatic3.getData();
        System.out.println(s.k);

    }
}

class GenericStatic<E>{
    E data;
    public GenericStatic(E data){
        this.data = data;
    }

    public E getData() {
        return data;
    }
}
//class GenericStatic4<T extends E>{
//    E data;
//    public GenericStatic4(E data){
//        this.data = data;
//    }
//
//    public E getData() {
//        return data;
//    }
//}

class GenericStatic2<E,T>{
    E firstData;
    T secondData;
    public GenericStatic2(E firstData, T secondData){
        this.firstData = firstData;
        this.secondData = secondData;
    }

    public E getFirstData() {
        return firstData;
    }
    public T getSecondData(){
        return secondData;
    }
}

class GenericStatic3<T extends StaticBlock>{
    T data;
    public GenericStatic3(T data){
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
