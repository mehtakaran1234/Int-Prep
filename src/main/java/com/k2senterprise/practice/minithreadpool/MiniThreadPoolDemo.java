package com.k2senterprise.practice.minithreadpool;

public class MiniThreadPoolDemo {
    /**
     * Demonstration entry point.
     */
    public static void main(String[] args) throws InterruptedException {
        // Instantiate the custom pool with 3 threads and a max capacity of 5 tasks
        MiniThreadPool pool = new MiniThreadPool(3, 5);

        // Submit 10 tasks to the pool
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            pool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " is processing Task " + taskId);
                try {
                    Thread.sleep(500); // Simulate a computation delay
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Allow some time for execution, then initiate a graceful shutdown
        Thread.sleep(2000);
        System.out.println("Shutting down thread pool...");
        pool.shutdown();
    }
}
