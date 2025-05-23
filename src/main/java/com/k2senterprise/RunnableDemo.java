package com.k2senterprise;

public class RunnableDemo {
    static String getNewThread(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("MyThread");
                System.out.println("test");
            }
        };
        r.run();
        return Thread.currentThread().getName();
    }
    public static void main(String[] args) {
        System.out.println("Thread name: " + Thread.currentThread().getName());
        System.out.println("Thread name: " + getNewThread());
    }
}
