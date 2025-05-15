package com.k2senterprise;

import java.util.Collections;
import java.util.List;

public class StringExample {
    public static void main(String[] args) {
        String s1 = "Karan";
        String s2 = s1;
        String s3 = new String("Karan");

        System.out.println("" + (s1 == s2));
        System.out.println("" + s1.equals(s2));
        System.out.println("" + s1.equals(s3));
        List<String> list = List.of("Karan", "Karan", "test");
        //List<String> stringList = Collections.unmodifiableList(list);
        //list.add("new");
        //stringList.add("new2");
        System.out.println(list);
        //System.out.println(stringList);
    }
}
