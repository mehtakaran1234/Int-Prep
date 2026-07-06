package com.k2senterprise.java8.funcationalprogramming;

import java.util.function.Function;

public class FunctionFi {
    public static void main(String[] args) {
        Function<Integer, Integer> function = (x) -> x * 2;

        function = function.andThen(x -> x * 3);

        System.out.println(function.apply(5));
    }
}
