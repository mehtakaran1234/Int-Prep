package com.k2senterprise.practice.retryservices.exponential;

import java.util.concurrent.ThreadLocalRandom;
@FunctionalInterface
interface RetryableOperation<T> {
    T execute() throws Exception;
}
public class ExponentialRetryTemplate {

    public static <T> T executeWithRetry(
            RetryableOperation<T> operation,
            int maxAttempts,
            long initialDelayMs,
            long maxDelayMs) throws Exception {

        int attempt = 0;
        long currentDelay = initialDelayMs;

        while (true) {
            try {
                attempt++;
                return operation.execute();
            } catch (Exception e) {
                if (attempt >= maxAttempts) {
                    throw new RuntimeException("Max retry attempts reached. Operation failed.", e);
                }

                // Add random jitter to prevent thundering herd
                long jitter = ThreadLocalRandom.current().nextLong(0, currentDelay / 2 + 1);
                long sleepTime = Math.min(currentDelay + jitter, maxDelayMs);

                System.out.printf("Attempt %d failed. Retrying in %d ms... Error: %s%n",
                        attempt, sleepTime, e.getMessage());

                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Retry interrupted", ie);
                }

                // Double the delay for the next iteration
                currentDelay = Math.min(currentDelay * 2, maxDelayMs);
            }
        }
    }
}
