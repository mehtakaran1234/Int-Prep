package com.k2senterprise.practice.ratelimitter.scheduler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TokenBucketRateLimiterForClientId {
    private final int capacity;
    private final int refillRate;
    private final ScheduledExecutorService scheduler;
    private final Map<String, ClientLimiter> limiters = new ConcurrentHashMap<>();

    public TokenBucketRateLimiterForClientId(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::refillAllTokens, 1, 1, TimeUnit.SECONDS);
    }

    private void refillAllTokens() {
        limiters.values().forEach(ClientLimiter::refill);
    }

    public boolean allowRequest(String clientId) {
        return limiters.computeIfAbsent(clientId, k -> new ClientLimiter(capacity))
                .consume();
    }

    public void shutdown() {
        scheduler.shutdown();
    }

    private class ClientLimiter {
        private int tokens;
        private final ReentrantLock lock = new ReentrantLock(true);

        ClientLimiter(int initialTokens) {
            this.tokens = initialTokens;
        }

        void refill() {
            if (lock.tryLock()) {
                try {
                    tokens = Math.min(capacity, tokens + refillRate);
                } finally {
                    lock.unlock();
                }
            }
        }

        boolean consume() {
            lock.lock();
            try {
                if (tokens > 0) {
                    tokens--;
                    return true;
                }
                return false;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TokenBucketRateLimiterForClientId rateLimiter = new TokenBucketRateLimiterForClientId(10, 2);
        String[] clients = {"client-1", "client-2", "client-3","client-4", "client-5", "client-6"};

        for (int i = 0; i < 100; i++) {
            String clientId = clients[i % clients.length];
            boolean isAllowed = rateLimiter.allowRequest(clientId);
            System.out.println("Request " + (i + 1) + " from " + clientId + ": "
                    + (isAllowed ? "✅ Allowed" : "❌ Blocked"));
            //Thread.sleep(300); // Simulate requests every 300ms
        }
        rateLimiter.shutdown();
    }

}
