package com.k2senterprise.synchronization.sync;

public class StaticCounter {
    private static int count = 0;

    public synchronized static void increment() {
        count++;
    }

    public synchronized static int getCount() {
        return count;
    }
}
