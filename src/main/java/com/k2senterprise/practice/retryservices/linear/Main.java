package com.k2senterprise.practice.retryservices.linear;

public class Main {
    // Counter to simulate a service recovering after a few attempts
    private static int failureCount = 0;

    public static void main(String[] args) {
        System.out.println("Starting Linear Retry Test...");

        try {
            // Will attempt up to 4 times, waiting exactly 1500ms between each failure
            String result = LinearRetryTemplate.executeWithRetry(
                    () -> callFlakyService(),
                    4,      // Max attempts
                    1500    // Constant delay of 1500ms between attempts
            );

            System.out.println("\n🎉 Success! Result: " + result);

        } catch (Exception e) {
            System.err.println("\n❌ Final execution completely failed: " + e.getMessage());
        }
    }

    /**
     * Simulates an external HTTP or database call.
     * Fails 2 times and succeeds on the 3rd attempt.
     */
    private static String callFlakyService() throws Exception {
        if (failureCount < 2) {
            failureCount++;
            throw new RuntimeException("Temporary Network Timeout (504)");
        }
        return "Database Connection Established";
    }
}
