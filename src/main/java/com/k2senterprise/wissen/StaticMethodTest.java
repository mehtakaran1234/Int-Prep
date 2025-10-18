package com.k2senterprise.wissen;

/******************************************************************************

 ind Out the Correct Output for Below Code.

 *******************************************************************************/
class Parent1 {
    static int x=10;
    public static void staticMethod() {
        x++;
        System.out.println("Value of X in Parent : " + x);
    }
}

class Child1 extends Parent1 {
    public static void staticMethod() {
        x++;
        System.out.println("Value of X in Child : " + x);
    }
}

public class StaticMethodTest {
    public static void main(String[] args) {
        Parent1.staticMethod();
        Child1.staticMethod();
        Parent1 parent = new Child1();
        parent.staticMethod();
    }
}
