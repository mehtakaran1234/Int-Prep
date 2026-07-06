package com.k2senterprise.java8.funcationalprogramming;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerFI {

    interface CFI{

    }

    public static void main(String[] args) {

        Consumer<String> consumer = (name) -> System.out.println(name);
        consumer.accept("Krishna");



        Consumer<List<Integer>> consumer1 = (list) -> {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        };
        consumer1.accept(Arrays.asList(1, 2, 3));

        Consumer<String> consumer2 = (name) -> {
            System.out.println(name);

        };
        Consumer<String> consumer3 = (name) -> {
            System.out.println(name);

        };
        Consumer<String> pipeLine = consumer2.andThen(consumer3);
        pipeLine.accept("FirstName");
    }
}
