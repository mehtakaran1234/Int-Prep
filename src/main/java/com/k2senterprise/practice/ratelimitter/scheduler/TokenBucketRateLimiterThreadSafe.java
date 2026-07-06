package com.k2senterprise.practice.ratelimitter.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TokenBucketRateLimiterThreadSafe {
    private int capacity;
    private int refillRate;
    private final ScheduledExecutorService scheduledExecutorService;
    private int tokens;

    private TokenBucketRateLimiterThreadSafe(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = capacity;
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(this::refill, 1, 1, TimeUnit.SECONDS);
    }

    private void refill() {
        tokens = Math.min(tokens, capacity + refillRate);
    }

    private boolean tryConsume() {
        if (tokens >= 0) {
            tokens--;
            return true;
        }
        return false;
    }

    private void shutdown() {
        scheduledExecutorService.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucketRateLimiterThreadSafe tokenBucketRateLimiterThreadSafe = new TokenBucketRateLimiterThreadSafe(5, 3);
        for (int i = 1; i <= 15; i++) {
            boolean requestPermitted = tokenBucketRateLimiterThreadSafe.tryConsume();
            System.out.println("Request " + i + ": " + requestPermitted);
            Thread.sleep(500);
        }
        tokenBucketRateLimiterThreadSafe.shutdown();
    }
}
