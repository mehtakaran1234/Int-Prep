// Java Program to illustrate Concept of
// CustomThreadPoolExecutor Executor Framework

// Importing LinkedBlockingQueue class from java.util
// package
package com.k2senterprise.practice.customthreadpool;
import java.util.concurrent.LinkedBlockingQueue;

// Interface
// Custom interface for which contains execute method
interface MyExecutorService {

    // Method
    void execute(Runnable r);
}