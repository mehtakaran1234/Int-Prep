package com.k2senterprise;

@FunctionalInterface
interface Work {
    void doWork(String n);
}
public class AnonymousDemo {
    public static void main(String[] args) {
        Work work  = (n) -> System.out.println("Doing work in anonymous class: " + n);
        work.doWork("test");

        work  = new Work() {
            @Override
            public void doWork(String n) {
                System.out.println("Doing work in anonymous class: " + n);
            }
        };
        work.doWork("test2");
    }
}
