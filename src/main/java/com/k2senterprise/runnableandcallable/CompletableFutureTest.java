package com.k2senterprise.runnableandcallable;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {
    public static void main(String[] args) throws Exception
    {
        CompletableFuture<String> helloFuture
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> greetingFuture
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<String> combinedFuture
                = helloFuture.thenCombine(
                greetingFuture, (m1, m2) -> m1 + " " + m2);

        System.out.println(combinedFuture.get());
    }
}
