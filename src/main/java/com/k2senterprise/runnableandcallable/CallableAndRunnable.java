package com.k2senterprise.runnableandcallable;

import java.util.concurrent.*;

public class CallableAndRunnable implements Callable<Long> {

    private Long num ;

    public CallableAndRunnable(long i) {
        num = i;
    }


    @Override
    public Long call(){
        System.out.println("Result of call: " + num);
        return num * num;

    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableAndRunnable callableAndRunnable = new CallableAndRunnable(5);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Long> submit = executorService.submit(callableAndRunnable);
        System.out.println("Result of callable: " + submit.get());
        executorService.shutdown();
    }
}
