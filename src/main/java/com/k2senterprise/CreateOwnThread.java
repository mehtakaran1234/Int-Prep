package com.k2senterprise;

public class CreateOwnThread implements Runnable {

    @Override
    public void run() {
        System.out.println("Custom Thread is running");
        // Add your thread logic here
        try {
            Thread.sleep(2000); // Simulate some work with sleep
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Create a new thread using the Runnable interface
        Thread thread = new Thread(new CreateOwnThread());
        thread.start(); // Start the thread

        try {
            thread.join(); // Wait for the thread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Thread has finished execution");
    }
}