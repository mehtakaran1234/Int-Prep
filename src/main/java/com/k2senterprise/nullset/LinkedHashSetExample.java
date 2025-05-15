package com.k2senterprise.nullset;

import java.util.LinkedHashSet;
import java.util.Set;
public class LinkedHashSetExample {
    public static void main(String args[]) {
        Set<Integer> linkedHashSet = new LinkedHashSet<Integer>();
        //Populating the HashSet
        linkedHashSet.add(1124);
        linkedHashSet.add(3654);
        linkedHashSet.add(7854);
        linkedHashSet.add(9945);
        System.out.println(linkedHashSet);
        //Adding null elements
        linkedHashSet.add(null);
        linkedHashSet.add(null);
        linkedHashSet.add(null);
        System.out.println(linkedHashSet);
    }
}
