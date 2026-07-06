package com.k2senterprise.practice.ratelimitter.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TokenBucketRateLimiter {
    private final int capacity; // maximum number of tokens in a bucket
    private final int refillRate; // number of tokens added per second
    private final ScheduledExecutorService scheduler; // Scheduler for periodically refilling tokens
    private int tokens; // current count of token
    private final ReentrantLock lock; // protects tokens for thread-safety and is reentrant

    public TokenBucketRateLimiter(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = capacity;  // Initially, the bucket is full
        scheduler = Executors.newScheduledThreadPool(1);
        this.lock = new ReentrantLock(true); // fair reentrant lock
        // Schedule a task to refill tokens at a fixed rate of 1 second
        scheduler.scheduleAtFixedRate(this::refillTokens, 1, 1, TimeUnit.SECONDS);
    }

    private void refillTokens() {
        // Try to acquire lock without blocking callers. If the lock is held by a caller
        // (e.g. allowRequest), skip this refill cycle to avoid blocking request threads.
        if (lock.tryLock()) {
            try {
                tokens = Math.min(capacity, tokens + refillRate); // Ensure tokens don't exceed capacity
            } finally {
                lock.unlock();
            }
        }
    }

    public boolean allowRequest() {
        // check if token is available or not, if it is available then consume it else reject the request.
        lock.lock();
        try {
            if (tokens > 0) {
                tokens--; // consume a token
                return true;
            }
            return false; // No tokens available, request is blocked
        } finally {
            lock.unlock();
        }
    }

    public void shutdown() {
        scheduler.shutdown();
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(3, 2);
        for (int i = 0; i < 10; i++) {
            boolean isAllowed = rateLimiter.allowRequest();
            System.out.println("Request " + (i + 1) + ": " + (isAllowed ? "✅ Allowed" : "❌ Blocked"));
            Thread.sleep(300); // Simulate requests every 300ms
        }
        rateLimiter.shutdown();
    }


}
