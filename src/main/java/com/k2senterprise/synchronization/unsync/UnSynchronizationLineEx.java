package com.k2senterprise.synchronization.unsync;

// Multiple threads are executing on the same object
// at same time without synchronization

class Line
{
    // if multiple threads(trains) will try to
    // access this unsynchronized method,
    // then object's  state will be corrupted
    public void getLine()
    {
        for (int i = 0; i < 10; i++)
        {
            System.out.println(i);
            try
            {
                Thread.sleep(100);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }
    }
}

class Train extends Thread
{
    // shared object
    Line line;

    Train(Line line)
    {

        // Initialize shared object
        this.line = line;
    }

    @Override
    public void run()
    {
        // Access the getLine() method
        line.getLine();
    }
}

public class UnSynchronizationLineEx
{
    public static void main(String[] args)
    {
        // Shared Line object
        Line obj = new Line();

        // creating the threads that are
        // sharing the same Object
        Train t1 = new Train(obj);
        Train t2 = new Train(obj);

        // threads start their execution
        t1.start();
        t2.start();
    }
}
