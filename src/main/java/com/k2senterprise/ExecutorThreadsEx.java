package com.k2senterprise;

import java.util.concurrent.*;

public class ExecutorThreadsEx {

    static void runMyThread() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from ExecutorService: " + Thread.currentThread().getName());
            }
        });

        Future<?> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("Hello from Callable: " + Thread.currentThread().getName());
                return "Callable Result";
            }
        });
        System.out.println(future.get());


        System.out.println("Hello from main thread: "+ Thread.currentThread().getName());

        executorService.shutdown();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        runMyThread();
    }
}
