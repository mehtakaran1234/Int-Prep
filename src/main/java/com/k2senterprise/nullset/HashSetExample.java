package com.k2senterprise.nullset;

import java.util.HashSet;
import java.util.Set;
public class HashSetExample {
    public static void main(String args[]) {
        Set<Integer> hashSet = new HashSet<Integer>();
        //Populating the HashSet
        hashSet.add(1124);
        hashSet.add(3654);
        hashSet.add(7854);
        hashSet.add(9945);
        System.out.println(hashSet);
        //Adding null elements
        hashSet.add(null);
        hashSet.add(null);
        hashSet.add(null);
        System.out.println(hashSet);
    }
}
