package com.k2senterprise.practice.customthreadpool;

// Class 1
// Helper class
class MyExecutors {

    // Member variables of this class
    int capacity;

    // Passing the number of threads that
    // will be in the thread pool
    static MyExecutorService
    myNewFixedThreadPool(int capacity)
    {

        return new MyThreadPool(capacity);
    }
}