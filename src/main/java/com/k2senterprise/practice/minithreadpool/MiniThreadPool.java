package com.k2senterprise.practice.minithreadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * A custom, lightweight thread pool implementation.
 */
public class MiniThreadPool {

    private final BlockingQueue<Runnable> taskQueue;
    private final List<WorkerThread> workers;
    private final AtomicBoolean isShutdown = new AtomicBoolean(false);

    /**
     * Initializes the thread pool with a fixed number of threads.
     *
     * @param numberOfThreads The exact number of worker threads to spawn.
     * @param maxQueueSize    The maximum capacity of the waiting task queue.
     */
    public MiniThreadPool(int numberOfThreads, int maxQueueSize) {
        this.taskQueue = new LinkedBlockingQueue<>(maxQueueSize);
        this.workers = new ArrayList<>(numberOfThreads);

        // Pre-initialize and start worker threads
        for (int i = 0; i < numberOfThreads; i++) {
            WorkerThread worker = new WorkerThread(this.taskQueue, this.isShutdown, "MiniThreadPool-Worker-" + i);
            workers.add(worker);
            worker.start();
        }
    }

    /**
     * Submits a new task for execution.
     *
     * @param task The Runnable task to be executed.
     * @throws IllegalStateException If tasks are submitted after shutdown.
     */
    public synchronized void submit(Runnable task) {
        if (this.isShutdown.get()) {
            throw new IllegalStateException("Thread pool has been shut down. Cannot accept new tasks.");
        }
        try {
            // Blocks if the queue is full
            taskQueue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Task submission interrupted: " + e.getMessage());
        }
    }

    /**
     * Initiates an orderly shutdown.
     * Existing tasks in the queue will still be executed, but no new tasks are accepted.
     */
    public synchronized void shutdown() {
        this.isShutdown.set(true);
        for (WorkerThread worker : workers) {
            // Interrupts threads if they are blocked on an empty taskQueue.take()
            worker.interrupt();
        }
    }


}

