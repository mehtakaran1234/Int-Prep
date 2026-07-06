package com.k2senterprise.practice.minithreadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

// Worker threads moved to separate class `WorkerThread` in the same package.

/**
 * Worker thread for MiniThreadPool. Pulled out as a separate top-level class so
 * it can be tested and reused independently.
 */
public class WorkerThread extends Thread {

    private final BlockingQueue<Runnable> taskQueue;
    private final AtomicBoolean isShutdown;

    public WorkerThread(BlockingQueue<Runnable> taskQueue, AtomicBoolean isShutdown, String name) {
        super(name);
        this.taskQueue = taskQueue;
        this.isShutdown = isShutdown;
    }

    @Override
    public void run() {
        while (true) {
            // If shutdown has been requested and there are no more queued tasks, exit cleanly
            if (isShutdown.get() && taskQueue.isEmpty()) {
                break;
            }

            try {
                Runnable task = taskQueue.take();
                try {
                    task.run();
                } catch (RuntimeException ex) {
                    // Catch and log to avoid worker thread dying
                    System.err.println(getName() + " - task threw exception: " + ex.getMessage());
                    ex.printStackTrace();
                }
            } catch (InterruptedException ie) {
                // If interrupted and shutdown requested, break; otherwise, re-check conditions
                if (isShutdown.get()) {
                    break;
                }
                // restore interrupt status and exit loop
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
