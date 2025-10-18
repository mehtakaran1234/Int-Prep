package com.k2senterprise.wissen;

class SuperClass {
    public static void display() {
        System.out.println("Static method in SuperClass");
    }
}

class SubClass extends SuperClass {
    public static void display() { // This hides, not overrides, SuperClass's display()
        System.out.println("Static method in SubClass");
    }
}

public class TestStaticMethods {
    public static void main(String[] args) {
        SuperClass.display(); // Calls SuperClass's display()
        SubClass.display();   // Calls SubClass's display()

        SuperClass obj = new SubClass();
        obj.display();        // Calls SuperClass's display() because of static binding
    }
}
