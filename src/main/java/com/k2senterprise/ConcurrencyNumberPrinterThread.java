package com.k2senterprise;

public class ConcurrencyNumberPrinterThread implements Runnable {

    private static int number = 1;
    private static final Object lock = new Object();
    int max = 10;

    @Override
    public void run() {

        while (number <= max) {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " : " + number);
                number++;
                lock.notify();
                if (number <= max) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int threads = 2;
        for (int i = 1; i <= threads; i++) {
            Thread t = new Thread(new ConcurrencyNumberPrinterThread(), "Thread-" + i);
            t.start();
        }
    }
}
