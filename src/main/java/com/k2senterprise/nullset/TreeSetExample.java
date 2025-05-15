package com.k2senterprise.nullset;

import java.util.Set;
import java.util.TreeSet;
public class TreeSetExample {
    public static void main(String args[]) {
        Set<Integer> treeSet = new TreeSet<Integer>();
        //Populating the HashSet
        treeSet.add(1124);
        treeSet.add(3654);
        treeSet.add(7854);
        treeSet.add(9945);
        System.out.println(treeSet);
        //Adding null elements
        treeSet.add(null);
        treeSet.add(null);
        treeSet.add(null);
        System.out.println(treeSet);
    }
}
