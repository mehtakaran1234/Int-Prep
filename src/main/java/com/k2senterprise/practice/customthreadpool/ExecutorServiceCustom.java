package com.k2senterprise.practice.customthreadpool;

// Main Class
public class ExecutorServiceCustom {
    // Main driver method
    public static void main(String[] args)
    {
        // Getting the object of MyExcutorService by using
        //  the factory method myNewFixedThreadPool

        // Passing number of threads as 3
        MyExecutorService service
                = MyExecutors.myNewFixedThreadPool(3);

        for (int i = 0; i < 20; i++) {

            // Creating 20 tasks and passing them to execute
            service.execute(new Mytask());
        }

        Runnable runnableTask = null;
    }
}