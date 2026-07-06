package com.k2senterprise.practice.retryservices.exponential;

public class Main {
    public static void main(String[] args) {
        try {
            String result = ExponentialRetryTemplate.executeWithRetry(
                    () -> callExternalService(),
                    5,      // Max attempts
                    500,    // Start with 500ms delay
                    4000    // Cap delay at 4000ms
            );
            System.out.println("Success! Result: " + result);
        } catch (Exception e) {
            System.err.println("Final execution failed: " + e.getMessage());
        }
    }

    private static int failureCount = 0;

    private static String callExternalService() throws Exception {
        if (failureCount < 3) {
            failureCount++;
            throw new RuntimeException("Service Unavailable (503)");
        }
        return "Target Data Received";
    }
}

