package com.k2senterprise;

public class CounterIssue extends Thread {
    private /*synchronized*/ static int x;

    public static void increment() {
        int counter = x;
        counter++;
        x = counter;
        System.out.println("Counter: " + x);

    }


    @Override
    public void run() {
        increment();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new CounterIssue().start();
        }
    }
}
