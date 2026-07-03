package com.k2senterprise.barclays.hackerrank;

import java.util.*;

/**
 * Google Pay Account Search Solution
 * Find all saved accounts matching user input and return them sorted by account number
 */
public class GooglePayAccountSearch {

    /**
     * Represents a bank account with all information
     */
    static class Account implements Comparable<Account> {
        long accountNumber;
        String firstName;
        String lastName;
        long mobileNumber;
        int branchCode;

        public Account(long accountNumber, String firstName, String lastName, long mobileNumber, int branchCode) {
            this.accountNumber = accountNumber;
            this.firstName = firstName;
            this.lastName = lastName;
            this.mobileNumber = mobileNumber;
            this.branchCode = branchCode;
        }

        @Override
        public int compareTo(Account other) {
            return Long.compare(this.accountNumber, other.accountNumber);
        }

        @Override
        public String toString() {
            return accountNumber + " " + firstName + " " + lastName + " " + mobileNumber + " " + branchCode;
        }
    }

    /**
     * Main solve function to find matching accounts
     *
     * @param N - number of saved accounts
     * @param A - 2D array of account information [accountNumber, firstName, lastName, mobileNumber, branchCode]
     * @param D - field name to search by (account_number, first_name, last_name, mobile_number, branch_code)
     * @param V - value to search for
     * @return list of matching accounts sorted by account number in ascending order
     */
    public static List<Account> solve(int N, Object[][] A, String D, String V) {
        List<Account> accounts = new ArrayList<>();

        // Parse all accounts from 2D array
        for (int i = 0; i < N; i++) {
            long accountNumber = Long.parseLong(A[i][0].toString());
            String firstName = A[i][1].toString();
            String lastName = A[i][2].toString();
            long mobileNumber = Long.parseLong(A[i][3].toString());
            int branchCode = Integer.parseInt(A[i][4].toString());

            accounts.add(new Account(accountNumber, firstName, lastName, mobileNumber, branchCode));
        }

        // Filter accounts based on field and value
        List<Account> matchingAccounts = new ArrayList<>();
        for (Account account : accounts) {
            if (matches(account, D, V)) {
                matchingAccounts.add(account);
            }
        }

        // Sort by account number in ascending order
        Collections.sort(matchingAccounts);

        return matchingAccounts;
    }

    /**
     * Check if an account matches the search criteria
     */
    private static boolean matches(Account account, String fieldName, String value) {
        switch (fieldName.toLowerCase()) {
            case "account_number":
                return account.accountNumber == Long.parseLong(value);
            case "first_name":
                return account.firstName.equalsIgnoreCase(value);
            case "last_name":
                return account.lastName.equalsIgnoreCase(value);
            case "mobile_number":
                return account.mobileNumber == Long.parseLong(value);
            case "branch_code":
                return account.branchCode == Integer.parseInt(value);
            default:
                return false;
        }
    }

    // ============= TEST CASES =============

    public static void main(String[] args) {
        System.out.println("========== GOOGLE PAY ACCOUNT SEARCH SOLUTION ==========\n");

        System.out.println("===== TEST CASE 1: Search by First Name (Given Example) =====");
        testCase1();

        System.out.println("\n===== TEST CASE 2: Search by Last Name =====");
        testCase2();

        System.out.println("\n===== TEST CASE 3: Search by Mobile Number =====");
        testCase3();

        System.out.println("\n===== TEST CASE 4: Search by Account Number =====");
        testCase4();

        System.out.println("\n===== TEST CASE 5: Search by Branch Code =====");
        testCase5();

        System.out.println("\n===== TEST CASE 6: Multiple Matches Sorted =====");
        testCase6();

        System.out.println("\n===== TEST CASE 7: Single Match =====");
        testCase7();

        System.out.println("\n===== TEST CASE 8: Duplicate Mobile Number Search =====");
        testCase8();

        System.out.println("\n===== TEST CASE 9: Case Insensitive Search =====");
        testCase9();

        System.out.println("\n===== TEST CASE 10: Large Account Numbers =====");
        testCase10();

        System.out.println("\n========== ALL TESTS COMPLETED ==========");
    }

    /**
     * Test Case 1: Search by First Name (Given Example)
     * N=3
     * Accounts: [11221909312, rohan, garg, 7384728384, 1022]
     *           [11221909566, shivam, kumar, 9128494856, 1022]
     *           [11201339211, shivam, sharma, 7384728384, 1987]
     * Search by: first_name = "shivam"
     * Expected: Both shivam accounts sorted by account number
     */
    public static void testCase1() {
        int N = 3;
        Object[][] A = {
            {11221909312L, "rohan", "garg", 7384728384L, 1022},
            {11221909566L, "shivam", "kumar", 9128494856L, 1022},
            {11201339211L, "shivam", "sharma", 7384728384L, 1987}
        };
        String D = "first_name";
        String V = "shivam";

        System.out.println("Search Criteria: " + D + " = " + V);
        System.out.println("Input: " + N + " accounts");

        List<Account> result = solve(N, A, D, V);

        System.out.println("\nMatching Accounts (sorted by account number):");
        for (Account acc : result) {
            System.out.println("  " + acc);
        }

        System.out.println("\nExpected 2 accounts (both named shivam), sorted: 11201339211, 11221909566");
        boolean pass = result.size() == 2 &&
                      result.get(0).accountNumber == 11201339211L &&
                      result.get(1).accountNumber == 11221909566L;
        System.out.println("Status: " + (pass ? "✓ PASS" : "✗ FAIL"));
    }

    /**
     * Test Case 2: Search by Last Name
     * Search for all accounts with last name "sharma"
     */
    public static void testCase2() {
        int N = 5;
        Object[][] A = {
            {10001L, "ram", "sharma", 9876543210L, 1001},
            {10002L, "sita", "sharma", 9876543211L, 1002},
            {10003L, "john", "doe", 9876543212L, 1003},
            {10004L, "priya", "sharma", 9876543213L, 1004},
            {10005L, "amit", "patel", 9876543214L, 1005}
        };
        String D = "last_name";
        String V = "sharma";

        System.out.println("Search Criteria: " + D + " = " + V);
        System.out.println("Input: " + N + " accounts");

        List<Account> result = solve(N, A, D, V);

        System.out.println("\nMatching Accounts (sorted by account number):");
        for (Account acc : result) {
            System.out.println("  " + acc);
        }

        System.out.println("\nExpected 3 accounts with last name sharma");
        boolean pass = result.size() == 3 &&
                      result.get(0).lastName.equals("sharma") &&
                      result.get(1).lastName.equals("sharma") &&
                      result.get(2).lastName.equals("sharma");
        System.out.println("Status: " + (pass ? "✓ PASS" : "✗ FAIL"));
    }

    /**
     * Test Case 3: Search by Mobile Number
     * Search for account with specific mobile number
     */
    public static void testCase3() {
        int N = 4;
        Object[][] A = {
            {20001L, "alice", "wonderland", 9111111111L, 2001},
            {20002L, "bob", "builder", 9222222222L, 2002},
            {20003L, "charlie", "brown", 9111111111L, 2003},
            {20004L, "diana", "prince", 9444444444L, 2004}
        };
        String D = "mobile_number";
        String V = "9111111111";

        System.out.println("Search Criteria: " + D + " = " + V);
        System.out.println("Input: " + N + " accounts");

        List<Account> result = solve(N, A, D, V);

        System.out.println("\nMatching Accounts (sorted by account number):");
        for (Account acc : result) {
            System.out.println("  " + acc);
        }

        System.out.println("\nExpected 2 accounts with mobile 9111111111");
        boolean pass = result.size() == 2;
        System.out.println("Status: " + (pass ? "✓ PASS" : "✗ FAIL"));
    }

    /**
     * Test Case 4: Search by Account Number
     * Search for specific account by its number
     */
    public static void testCase4() {
        int N = 3;
        Object[][] A = {
            {30001L, "user1", "one", 8111111111L, 3001},
            {30002L, "user2", "two", 8222222222L, 3002},
            {30003L, "user3", "three", 8333333333L, 3003}
        };
        String D = "account_number";
        String V = "30002";

        System.out.println("Search Criteria: " + D + " = " + V);
        System.out.println("Input: " + N + " accounts");

        List<Account> result = solve(N, A, D, V);

        System.out.println("\nMatching Accounts:");
        for (Account acc : result) {
            System.out.println("  " + acc);
        }

        System.out.println("\nExpected 1 account with number 30002");
        boolean pass = result.size() == 1 && result.get(0).accountNumber == 30002L;
        System.out.println("Status: " + (pass ? "✓ PASS" : "✗ FAIL"));
    }

    /**
     * Test Case 5: Search by Branch Code
     * Find all accounts for a specific branch
     */
    public static void testCase5() {
        int N = 6;
        Object[][] A = {
            {40001L, "emp1", "branch1", 7111111111L, 4001},
            {40002L, "emp2", "branch2", 7222222222L, 4001},
            {40003L, "emp3", "branch3", 7333333333L, 4002},
            {40004L, "emp4", "branch4", 7444444444L, 4001},
            {40005L, "emp5", "branch5", 7555555555L, 4003},
            {40006L, "emp6", "branch6", 7666666666L, 4001}
        };
        String D = "branch_code";
        String V = "4001";

        System.out.println("Search Criteria: " + D + " = " + V);
        System.out.println("Input: " + N + " accounts");

        List<Account> result = solve(N, A, D, V);

        System.out.println("\nMatching Accounts (sorted by account number):");
        for (Account acc : result) {
            System.out.println("  " + acc);
        }

        System.out.println("\nExpected 4 accounts for branch 4001");
        boolean pass = result.size() == 4;
        System.out.println("Status: " + (pass ? "✓ PASS" : "✗ FAIL"));
    }

    /**
     * Test Case 6: Multiple Matches Sorted by Account Number
     * Verify sorting is correct for multiple results
     */
    public static void testCase6() {
        int N = 5;
        Object[][] A = {
            {99999L, "kumar", "singh", 6999999999L, 5001},
            {11111L, "kumar", "patel", 6111111111L, 5002},
            {55555L, "kumar", "verma", 6555555555L, 5003},
            {33333L, "kumar", "rao", 6333333333L, 5004},
            {77777L, "kumar", "nair", 6777777777L, 5005}
        };
        String D = "first_name";
        String V = "kumar";

        System.out.println("Search Criteria: " + D + " = " + V);
        System.out.println("Input: " + N + " accounts");

        List<Account> result = solve(N, A, D, V);

        System.out.println("\nMatching Accounts (sorted by account number):");
        for (Account acc : result) {
            System.out.println("  " + acc);
        }

        System.out.println("\nExpected all 5 accounts sorted: 11111, 33333, 55555, 77777, 99999");
        boolean sorted = result.size() == 5 &&
                        result.get(0).accountNumber == 11111L &&
                        result.get(1).accountNumber == 33333L &&
                        result.get(2).accountNumber == 55555L &&
                        result.get(3).accountNumber == 77777L &&
                        result.get(4).accountNumber == 99999L;
        System.out.println("Status: " + (sorted ? "✓ PASS" : "✗ FAIL"));
    }

    /**
     * Test Case 7: Single Match
     * Verify single account is returned correctly
     */
    public static void testCase7() {
        int N = 3;
        Object[][] A = {
            {50001L, "unique", "user", 5111111111L, 5001},
            {50002L, "another", "user", 5222222222L, 5002},
            {50003L, "third", "user", 5333333333L, 5003}
        };
        String D = "first_name";
        String V = "unique";

        System.out.println("Search Criteria: " + D + " = " + V);
        System.out.println("Input: " + N + " accounts");

        List<Account> result = solve(N, A, D, V);

        System.out.println("\nMatching Accounts:");
        for (Account acc : result) {
            System.out.println("  " + acc);
        }

        System.out.println("\nExpected 1 account with first name 'unique'");
        boolean pass = result.size() == 1 && result.get(0).firstName.equals("unique");
        System.out.println("Status: " + (pass ? "✓ PASS" : "✗ FAIL"));
    }

    /**
     * Test Case 8: Duplicate Mobile Number in Different Accounts
     * Note: Problem states no two accounts can have same mobile or account number
     * This tests handling of unique mobile numbers
     */
    public static void testCase8() {
        int N = 4;
        Object[][] A = {
            {60001L, "user", "a", 6000000001L, 6001},
            {60002L, "user", "b", 6000000002L, 6002},
            {60003L, "user", "c", 6000000003L, 6003},
            {60004L, "user", "d", 6000000004L, 6004}
        };
        String D = "first_name";
        String V = "user";

        System.out.println("Search Criteria: " + D + " = " + V);
        System.out.println("Input: " + N + " accounts");

        List<Account> result = solve(N, A, D, V);

        System.out.println("\nMatching Accounts (sorted by account number):");
        for (Account acc : result) {
            System.out.println("  " + acc);
        }

        System.out.println("\nExpected all 4 accounts with first name 'user'");
        boolean pass = result.size() == 4 && result.get(0).accountNumber < result.get(1).accountNumber;
        System.out.println("Status: " + (pass ? "✓ PASS" : "✗ FAIL"));
    }

    /**
     * Test Case 9: Case Insensitive Search
     * Verify that search is case insensitive for string fields
     */
    public static void testCase9() {
        int N = 4;
        Object[][] A = {
            {70001L, "Rohan", "Sharma", 7111111111L, 7001},
            {70002L, "rohan", "patel", 7222222222L, 7002},
            {70003L, "ROHAN", "verma", 7333333333L, 7003},
            {70004L, "john", "doe", 7444444444L, 7004}
        };
        String D = "first_name";
        String V = "ROHAN";

        System.out.println("Search Criteria: " + D + " = " + V + " (case insensitive)");
        System.out.println("Input: " + N + " accounts");

        List<Account> result = solve(N, A, D, V);

        System.out.println("\nMatching Accounts (case insensitive):");
        for (Account acc : result) {
            System.out.println("  " + acc);
        }

        System.out.println("\nExpected 3 accounts with first name containing 'rohan' (any case)");
        boolean pass = result.size() == 3;
        System.out.println("Status: " + (pass ? "✓ PASS" : "✗ FAIL"));
    }

    /**
     * Test Case 10: Large Account Numbers
     * Verify handling of large long values
     */
    public static void testCase10() {
        int N = 3;
        Object[][] A = {
            {9876543210123L, "bigacct1", "user", 9999999999L, 9001},
            {1234567890123L, "bigacct2", "user", 8888888888L, 9002},
            {5555555555555L, "bigacct3", "user", 7777777777L, 9003}
        };
        String D = "first_name";
        String V = "bigacct2";

        System.out.println("Search Criteria: " + D + " = " + V);
        System.out.println("Input: " + N + " accounts with large account numbers");

        List<Account> result = solve(N, A, D, V);

        System.out.println("\nMatching Accounts:");
        for (Account acc : result) {
            System.out.println("  " + acc);
        }

        System.out.println("\nExpected 1 account with large account number");
        boolean pass = result.size() == 1 && result.get(0).accountNumber == 1234567890123L;
        System.out.println("Status: " + (pass ? "✓ PASS" : "✗ FAIL"));
    }
}

