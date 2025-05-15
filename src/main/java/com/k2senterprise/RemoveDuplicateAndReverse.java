package com.k2senterprise;

import com.sun.source.tree.Tree;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class RemoveDuplicateAndReverse {
    public static void main(String[] args) {
        String str = "Hello World";
        String[] split = str.split(" ");
        Set<Character> set = new LinkedHashSet<>();
        for(String s: split){
            for(int i = s.length()-1; i>=0; i--){
                set.add(s.charAt(i));
            }
            set.add(' ');
        }

        System.out.println(set);
    }
}
