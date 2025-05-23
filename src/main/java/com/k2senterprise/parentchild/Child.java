package com.k2senterprise.parentchild;
class Parent {

    Parent() {
        this("Constructor");
        System.out.println("Parent class Default Constructor");
    }

    Parent(String s) {
        System.out.println("Parent Class Param " + s);
    }
}

public class Child extends Parent {

    Child() {
        super("Constructor");
        System.out.println("Child Class Default Constructor");
    }

    Child(String s) {
        System.out.println("Child Class Param Constructor " + s);
    }

    public static void main(String[] args) {
        Child obj = new Child();
    }
}

