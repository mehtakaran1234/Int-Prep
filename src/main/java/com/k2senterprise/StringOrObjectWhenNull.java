package com.k2senterprise;

public class StringOrObjectWhenNull {

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        Integer arg = null;
        myClass.getMethod(arg);
        myClass.getMethod("null");
        //myClass.getMethod(null);

        myClass.doSomething(arg);
        //myClass.doSomething(null);
    }
}
class MyClass {
    public void getMethod(Object obj) {
        System.out.println("Hello, World! Object " + obj);
    }
    public void getMethod(String obj) {
        System.out.println("Hello, World! String " + obj);
    }
    public void getMethod(Integer obj) {
        System.out.println("Hello, World! Integer " + obj);
    }

    public static  void doSomething(Object obj) {
        System.out.println("Object called");
    }

    public static  void doSomething(char[] obj) {
        System.out.println("Array called");
    }

    public static  void doSomething(Integer obj) {
        System.out.println("Integer called");
    }
}
