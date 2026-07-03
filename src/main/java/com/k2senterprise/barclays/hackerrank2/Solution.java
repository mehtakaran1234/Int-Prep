package com.k2senterprise.barclays.hackerrank2;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    // Inventory mapping to [20, 50, 100, 200, 500]
    private static final int[] availableNotes = new int[5];
    private static final int[] DENOMINATIONS = {20, 50, 100, 200, 500};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Process inputs line-by-line until end of stream
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            // Split line by whitespace to parse integers
            String[] tokens = line.split("\\s+");
            int[] tokensInt = new int[tokens.length];
            for (int i = 0; i < tokens.length; i++) {
                tokensInt[i] = Integer.parseInt(tokens[i]);
            }

            // Route execution based on deposit (-1) or withdraw (positive number)
            if (tokensInt[0] == -1) {
                processDeposit(tokensInt);
            } else {
                int[] result = processWithdraw(tokensInt[0]);
                printArray(result);
            }
        }
        scanner.close();
    }

    private static void processDeposit(int[] request) {
        // Deposit updates the 5 denomination counts and produces no output
        for (int i = 0; i < 5; i++) {
            availableNotes[i] += request[i + 1];
        }
    }

    private static int[] processWithdraw(int amount) {
        int[] dispensed = new int[5];
        int remainingAmount = amount;

        // Strict greedy approach: highest value denominations prioritized first
        for (int i = 4; i >= 0; i--) {
            int needed = remainingAmount / DENOMINATIONS[i];
            int taken = Math.min(needed, availableNotes[i]);
            dispensed[i] = taken;
            remainingAmount -= taken * DENOMINATIONS[i];
        }

        // Transaction succeeds only if amount is fully reduced to 0
        if (remainingAmount == 0) {
            for (int i = 0; i < 5; i++) {
                availableNotes[i] -= dispensed[i];
            }
            return dispensed;
        } else {
            // Transaction fails: system state remains completely untouched
            int[] failure = new int[5];
            Arrays.fill(failure, -1);
            return failure;
        }
    }

    private static void printArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}

