package com.k2senterprise;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class Java8LambaExpExample {
    public static void main(String[] args) {
        // Lambda expression Example 1
        List<Integer> myList = Arrays.asList(1, 2, 3, 6, 7, 5, 9, 3, 8, 4, 5);
        List<Integer> collect = myList.stream().filter(n -> n % 2 == 0).toList();
        collect.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println);

        // Lambda expression Example 2
        Runnable lambdaRunnable = () -> {
            System.out.println("My Runnable lambda expression");
            myMtheod();
        };
        lambdaRunnable.run();
    }

    private static void myMtheod() {
        System.out.println("My method: " + Thread.currentThread().getName());
    }
}
