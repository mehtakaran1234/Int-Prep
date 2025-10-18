package com.k2senterprise.synchronization.sync;

public class Example {
    private static int staticCount = 0;
    private int instanceCount = 0;
    private final Object lock = new Object();

    public static void incrementStatic() {
        synchronized (Example.class) {
            staticCount++;
        }
    }

    public void incrementInstance() {
        synchronized (this) {
            instanceCount++;
        }
    }
    public void incrementInstanceWithObjectLock() {
        synchronized(lock){
            instanceCount++;
        }
    }
}
