package com.mon.generics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WildCardGenerics {
    // you always use extends with generics wildcard
    public static <T extends Comparable<T>> void sort(List<T> list){

    }
    public <T extends Comparable<? super T>> T sort(List<T> list, int n){
        return null;
    }
    public <T extends Comparable<? super T>> Comparable<? super T> sort(List<T> list, String s){
        // returns an object T where either or its superclass implements Comparable
        return null;
    }

    public static <E> Comparator<E> run(){
        return (Comparator<E>)null;
    }
    class DD implements Comparable{

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    public static <T extends Comparable<? super T>> void test(T list){
        list.compareTo(null);
    }

    /*public void run(int h){} // Compilation Error: Both methods have the same erasure
    public int run(int h){
        return 9;
    }*/

//    Object

    public <M> M count(M value){
        return value;
    }
    //    Collections
    class hhh implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }

    public static void main(String[] args) {

//        Comparator

//        List<?> foo = new ArrayList<Number>();
        List<Number> numberList = new ArrayList<>();
        numberList.add(1);
        numberList.add(2);
        numberList.add(3);

        /**
         * PECS::
         * Producer Extends; Consumer Super
         */
        List<? extends Number> list1 = numberList; // we can assign a list of numbers to the wildcard type

        /**
         * assigning an already predefined list of Integers to the wildcard List<? extends Number>
         * however after assignment, we can no longer add an integer to the list
         */
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);

        List<? extends Number> list2 = integerList;
        // list2.add(4); => Compilation Error:: required type -> capture of ? extends Number; provided -> int
        // therefore, we can't add anything to the list2, making it not writeable
        // Integer number = list2.get(1);
        List<? extends Number> foo = new ArrayList<>();
        List<? extends Number> foo1 = new ArrayList<Number>();
        List<? extends Number> foo2 = new ArrayList<Integer>();
        List<? extends Number> foo3 = new ArrayList<Double>();
//        foo.add(1);
//        foo.add(2);
//        foo.add(3);
        System.out.println(list1);
        System.out.println(list2);

        /**
         * PECS::
         * Producer Extends; Consumer Super
         */
        List<? super Integer> foo4 = new ArrayList<Object>();
        foo4.add(1);
        Object o = foo4.get(0);
        // Number n = foo4.get(0); => Compilation error
    }
}
