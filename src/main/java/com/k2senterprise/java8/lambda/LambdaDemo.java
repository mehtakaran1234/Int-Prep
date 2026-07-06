package com.k2senterprise.java8.lambda;

interface MyFunctionalInterface {
    int add(int a, int b);

    //String print(String a);
}

public class LambdaDemo {

    public static void main(String[] args) {
        MyFunctionalInterface my =  (a, b) -> a + b;
        System.out.println(""+my.add(1, 2));
    }
}
