package com.k2senterprise.synchronization.sync;
// Multiple threads execute the same method
// but in synchronized way
class Line
{
    // Synchronized method ensures that only one thread
    // can execute this method at a time on the same object
    synchronized static public void getLine()
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
    // Reference variable
    Line line;

    Train(Line line)
    {
        this.line = line;
    }

    @Override
    public void run()
    {
        line.getLine();
    }
}

public class SynchronizationLineEx {

    public static void main(String[] args)
    {

        // Object of Line class shared among the threads
        Line obj = new Line();

        // Creating threads that share the same object
        Train t1 = new Train(obj);
        Train t2 = new Train(obj);

        t1.start();
        t2.start();
    }
}

