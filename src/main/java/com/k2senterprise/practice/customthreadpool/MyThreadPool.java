package com.k2senterprise.practice.customthreadpool;

import java.util.concurrent.LinkedBlockingQueue;

// Class 2
// Helper class extending to MyExecutorService interface
class MyThreadPool implements MyExecutorService {

    // Member variables of this class
    static int capacity;
    static int currentCapacity;

    // Creating object of LinkedBlockingQueue class
    // Declaring object of type Runnable
    static LinkedBlockingQueue<Runnable>
            linkedTaskBlockingQueue;

    // Member variables of this class
    Execution e;

    // Method 1
    public MyThreadPool(int capacity)
    {

        // Member variables of this class

        // this keyword refers to current instance itself
        this.capacity = capacity;
        currentCapacity = 0;

        // Creating a linked blocking queue which will block
        // if its empty
        // and it will perform thread safe operation.
        linkedTaskBlockingQueue
                = new LinkedBlockingQueue<Runnable>();

        // Creating the object of execution class
        e = new Execution();
    }

    // Method 2
    // @Override
    public void execute(Runnable r)
    {

        // Declaring and adding tasks to
        // blocking queue using add() method
        linkedTaskBlockingQueue.add(r);

        // executeMyMethod() method of Execution class
        // which will execute the tasks
        e.executeMyMethod();
    }
}