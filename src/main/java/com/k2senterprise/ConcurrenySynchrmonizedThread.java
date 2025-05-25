package com.k2senterprise;

public class ConcurrenySynchrmonizedThread {

    // Prints numbers 1 to 100 in order using 3 threads, each printing in sequence by each thread
    public static void printSequentialNumbers() {
        final Object lock = new Object();
        final int[] counter = {1};
        int max = 10;

        Runnable[] tasks = new Runnable[3];
        for (int i = 0; i < 3; i++) {
            final int threadId = i;
            tasks[i] = () -> {
                while (true) {
                    synchronized (lock) {
                        while (counter[0] <= max && (counter[0] - 1) % 3 != threadId) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                        if (counter[0] > max) {
                            lock.notifyAll();
                            break;
                        }
                        System.out.println("Thread " + (threadId + 1) + ": " + counter[0]);
                        counter[0]++;
                        lock.notifyAll();
                    }
                }
            };
        }

        for (int i = 0; i < 3; i++) {
            new Thread(tasks[i]).start();
        }
    }
    // Prints numbers 1 to 100 in order using 3 threads, each printing in sequence by each thread
    /*public static void printSequentialNumbers() {
        final Object lock = new Object();
        final int[] counter = {1};
        int max = 10;

        Runnable printTask = (Runnable & java.io.Serializable) () -> {
            int threadId = Integer.parseInt(Thread.currentThread().getName());
            while (true) {
                synchronized (lock) {
                    while (counter[0] <= max && (counter[0] - 1) % 3 != threadId) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    if (counter[0] > max) {
                        lock.notifyAll();
                        break;
                    }
                    System.out.println("Thread " + threadId + ": " + counter[0]);
                    counter[0]++;
                    lock.notifyAll();
                }
            }
        };

        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(printTask, String.valueOf(i));
            t.start();
        }
    }*/

    public static void main(String[] args) {
        printSequentialNumbers();
    }

}
