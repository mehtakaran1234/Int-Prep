package com.k2senterprise.practice.retryservices.linear;

@FunctionalInterface
interface RetryableOperation<T> {
    T execute() throws Exception;
}

public class LinearRetryTemplate {

    public static <T> T executeWithRetry(
            RetryableOperation<T> operation,
            int maxAttempts,
            long delayMs) throws Exception {

        int attempt = 0;

        while (true) {
            try {
                attempt++;
                return operation.execute();
            } catch (Exception e) {
                if (attempt >= maxAttempts) {
                    throw new RuntimeException("Max retry attempts reached. Operation failed.", e);
                }

                System.out.printf("Attempt %d failed. Retrying in %d ms... Error: %s%n",
                        attempt, delayMs, e.getMessage());

                try {
                    Thread.sleep(delayMs);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Retry interrupted", ie);
                }
            }
        }
    }
}

