package com.k2senterprise.java8.lambda;

public class LambdaThread {

    public void playMusic(String musicName) throws InterruptedException {
        System.out.println("Playing Music");
        Thread.sleep(2000);
    }

    public void playGame(String gameName) throws InterruptedException {
        System.out.println("Playing Game");
        Thread.sleep(1000);
    }

    public static void main(String[] args) {

        LambdaThread lambdaThread = new LambdaThread();




        Runnable r = () -> {
           Thread.currentThread().setName("Custom Thread");
            System.out.println("" + Thread.currentThread().getName());
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
    }
}
