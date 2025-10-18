package com.k2senterprise.synchronization.sync;

public class InstanceCounter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}
