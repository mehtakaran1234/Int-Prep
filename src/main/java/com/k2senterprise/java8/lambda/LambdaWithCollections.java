package com.k2senterprise.java8.lambda;

import java.util.*;

public class LambdaWithCollections {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(10, 2, 30, 4, -5);
        Collections.sort(integerList, (o1, o2) -> (o1 > o2) ? 1 : (o1 < o2) ? -1 : 0);
        System.out.println(integerList);

        Set<Integer> h =
                new TreeSet<>((o1, o2) -> (o1 > o2) ?
                        -1 : (o1 < o2) ? 1 : 0);
        h.add(850);
        h.add(235);
        h.add(1080);
        h.add(15);
        h.add(5);
        System.out.println("Elements of the TreeSet after" +
                " sorting are: " + h);

        TreeMap<Integer, String> m =
                new TreeMap<Integer, String>((o1, o2) -> (o1 > o2) ?
                        -1 : (o1 < o2) ? 1 : 0);
        m.put(1, "Apple");
        m.put(4, "Mango");
        m.put(5, "Orange");
        m.put(2, "Banana");
        m.put(3, "Grapes");
        System.out.println("Elements of the TreeMap " +
                "after sorting are : " + m);
    }
}
