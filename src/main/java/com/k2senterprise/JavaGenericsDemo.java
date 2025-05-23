package com.k2senterprise;

public class JavaGenericsDemo <T>{
    public void show(T t) {
        System.out.println("The value is: " + t);
    }

    public static void main(String[] args) {
        JavaGenericsDemo <Integer> integerDemo = new JavaGenericsDemo<>();
        integerDemo.show(10);

        JavaGenericsDemo <String> stringDemo = new JavaGenericsDemo<>();
        stringDemo.show("Hello Generics");

    }

}
