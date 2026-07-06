package com.k2senterprise.java8.lambda;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface A {
    void absFunc(int x, int y);
    default void print() {
        System.out.println("Default method in interface A");
    }
}

public class LambdaWithDefaultMethod {
    public static void main(String[] args) {

        A multiply = (x, y) -> System.out.println( 2 * x * y );
        multiply.print();
        multiply.absFunc(10, 50);

        A add = (x, y) -> {
            System.out.println(x + y);
        };
        add.absFunc(10, 50);

        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(5);
        list.add(30);

        list.forEach(n -> {
            if(n%2==0){

                System.out.println(n);
            }
        });

        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> integerList2 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<String> nameList = Arrays.asList("Alice", "Bob", "Charlie", "Tom" , "Amit");

        nameList.stream().filter(n -> n.startsWith("A")).map(n -> n.toUpperCase()).forEach(System.out::println);


    }
}
