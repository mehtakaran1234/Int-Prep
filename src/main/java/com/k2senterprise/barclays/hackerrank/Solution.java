package com.k2senterprise.barclays.hackerrank;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    // Adjust this index based on which of the 5 columns holds the account number
    private static final int ACCOUNT_NUMBER_INDEX = 0;

    // Maps the field name 'D' to its corresponding column index (0 to 4)
    private static int getFieldIndex(String fieldName) {
        switch (fieldName.toLowerCase()) {
            case "account number": return 0;
            // Add other field mappings here based on the problem description
            default: return 0;
        }
    }

    public static String[][] solve(int N, String[][] accounts, String D, String V) {
        int targetFieldIndex = getFieldIndex(D);
        int matchCount = 0;

        // 1. First pass: Count how many accounts match the criteria
        for (int i = 0; i < N; i++) {
            if (accounts[i][targetFieldIndex].equals(V)) {
                matchCount++;
            }
        }

        // Allocate the exact size for the filtered 2D array
        String[][] filteredAccounts = new String[matchCount][5];
        int index = 0;

        // 2. Second pass: Populate the filtered array
        for (int i = 0; i < N; i++) {
            if (accounts[i][targetFieldIndex].equals(V)) {
                filteredAccounts[index] = accounts[i];
                index++;
            }
        }

        // 3. Sort the 2D array by the account number column in ascending order
        Arrays.sort(filteredAccounts, new Comparator<String[]>() {
            @Override
            public int compare(String[] row1, String[] row2) {
                return row1[ACCOUNT_NUMBER_INDEX].compareTo(row2[ACCOUNT_NUMBER_INDEX]);
            }
        });

        return filteredAccounts;
    }
}
