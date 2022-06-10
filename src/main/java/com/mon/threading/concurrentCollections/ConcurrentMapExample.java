package com.mon.threading.concurrentCollections;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapExample {

    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();

        map.put("1", "One");
        map.put("2", "Two");
        map.put("3", "Three");
        map.put("5", "Five");
        map.put("6", "Six");

        System.out.println("Initial ConcurrentHashMap: " + map);
        Iterator<String> iterator = map.keySet().iterator();

        try{
            while (iterator.hasNext()) {
                String key = iterator.next();
                if (key.equals("3")) {
                    map.put("4", "Four");
                }
            }
            System.out.println("ConcurrentHashMap after modification: " + map);
            // concurrentMap will never throw ConcurrentModificationException during iteration
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }

        map = new HashMap<>();
        map.put("1", "One");
        map.put("2", "Two");
        map.put("3", "Three");
        map.put("5", "Five");
        map.put("6", "Six");

        System.out.println("Initial HashMap: " + map);
        iterator = map.keySet().iterator();

        try{
            while (iterator.hasNext()) {
                String key = iterator.next();
                if (key.equals("3")) {
                    map.put("4", "Four");
                }
            }
            System.out.println("HashMap after modification: " + map);
            // if concurrent modification happens during iteration, hashMap will throw ConcurrentModificationException
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }
    }
}
