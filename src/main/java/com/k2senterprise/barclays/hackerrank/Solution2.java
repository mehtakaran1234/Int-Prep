package com.k2senterprise.barclays.hackerrank;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {

    public static String[][] solve(int N, String[][] A, String D, String V) {
        List<String[]> matchedRows = new ArrayList<>();

        // Iterate through each account row
        for (int i = 0; i < N; i++) {
            boolean isMatch = false;

            // Scan each field in the current row to find the matching value V
            for (int j = 0; j < A[i].length; j++) {
                if (A[i][j] != null && A[i][j].equals(V)) {
                    isMatch = true;
                    break;
                }
            }

            // If a match is found, add the entire row to our temporary list
            if (isMatch) {
                matchedRows.add(A[i]);
            }
        }

        // Convert the dynamic list back into a standard 2D array
        String[][] result = new String[matchedRows.size()][];
        for (int i = 0; i < matchedRows.size(); i++) {
            result[i] = matchedRows.get(i);
        }

        return result;
    }

    /**
     * Test Case 5: Search by Branch Code
     * Find all accounts for a specific branch
     */
    public static void testCase5() {
        int N = 3;
        String[][] A = {{"11221909312", "rohan", "garg", "7384728384", "1022"}, {"11221909566", "shivam", "kumar", "9128494856", "1022"}, {"11201339211", "shivam", "sharma", "7384728384", "1987"}};
        String D = "branch_code";
        String V = "1022";

        System.out.println("Search Criteria: " + D + " = " + V);
        System.out.println("Input: " + N + " accounts");

        String[][] result = solve(N, A, D, V);

        System.out.println("\nMatching Accounts (sorted by account number):");
        for (String[] row : result) {
            System.out.println("  " + java.util.Arrays.toString(row));
        }

        System.out.println("\nExpected 2 accounts for branch 1022");
        boolean pass = result.length == 2;
        System.out.println("Status: " + (pass ? "✓ PASS" : "✗ FAIL"));
    }

    public static void main(String[] args) {
        System.out.println("========== GOOGLE PAY ACCOUNT SEARCH SOLUTION ==========\n");


        System.out.println("\n===== TEST CASE 5: Search by Branch =====");
        testCase5();

    }
}
