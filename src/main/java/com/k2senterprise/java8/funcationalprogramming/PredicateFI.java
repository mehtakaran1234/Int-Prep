package com.k2senterprise.java8.funcationalprogramming;

import java.util.function.Predicate;

public class PredicateFI {
    public static void main(String[] args) {

        Predicate<Integer> negative = x -> x < 0;

        Predicate<Integer> positive = x -> x > 0;

        System.out.println(negative.or(positive).test(60));

        Predicate<String> predicate = Predicate.isEqual("Java");

        System.out.println(predicate.test("java"));

    }
}
