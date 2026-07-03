package com.k2senterprise.barclays.hackerrank;

import java.util.*;

public class StrangeSumSolution {

    /**
     * Solve function to handle Strange Sum queries
     *
     * @param N - size of the array
     * @param Q - number of queries
     * @param A - the array of integers
     * @param queries - 2D array of queries where each query is [type, param1, param2]
     * @return array of integers containing answers for type 2 queries
     */
    public static int[] solve(int N, int Q, int[] A, int[][] queries) {
        List<Integer> results = new ArrayList<>();

        // Process each query
        for (int i = 0; i < Q; i++) {
            int queryType = queries[i][0];

            if (queryType == 1) {
                // Type 1: Set A[idx] = val
                int idx = queries[i][1];
                int val = queries[i][2];
                // Convert to 0-indexed
                A[idx - 1] = val;
            } else if (queryType == 2) {
                // Type 2: Find strange sum from index l to r (1-indexed)
                int l = queries[i][1];
                int r = queries[i][2];
                // Convert to 0-indexed
                int strangeSum = calculateStrangeSum(A, l - 1, r - 1);
                results.add(strangeSum);
            }
        }

        // Convert results list to array
        int[] answer = new int[results.size()];
        for (int i = 0; i < results.size(); i++) {
            answer[i] = results.get(i);
        }

        return answer;
    }

    /**
     * Calculate Strange Sum for a sub-array from index start to end (0-indexed)
     * Strange Sum S = 1*a[start] + 2*a[start+1] + ... + (end-start+1)*a[end]
     */
    private static int calculateStrangeSum(int[] A, int start, int end) {
        long sum = 0;
        int multiplier = 1;

        for (int i = start; i <= end; i++) {
            sum += (long) multiplier * A[i];
            multiplier++;
        }

        return (int) sum;
    }

    // ============= TEST CASES =============

    public static void main(String[] args) {
        System.out.println("========== STRANGE SUM SOLUTION - TEST CASES ==========\n");

        System.out.println("===== TEST CASE 1: Problem Statement Example =====");
        testCase1();

        System.out.println("\n===== TEST CASE 2: Multiple Type 2 Queries =====");
        testCase2();

        System.out.println("\n===== TEST CASE 3: Mixed Type 1 and Type 2 Queries =====");
        testCase3();

        System.out.println("\n===== TEST CASE 4: Single Element Queries =====");
        testCase4();

        System.out.println("\n===== TEST CASE 5: Large Values =====");
        testCase5();

        System.out.println("\n===== TEST CASE 6: Edge Case - Entire Array =====");
        testCase6();

        System.out.println("\n===== TEST CASE 7: Multiple Updates Same Index =====");
        testCase7();

        System.out.println("\n===== TEST CASE 8: Zero Values =====");
        testCase8();

        System.out.println("\n===== TEST CASE 9: Negative Values =====");
        testCase9();

        System.out.println("\n===== TEST CASE 10: Complex Sequence =====");
        testCase10();

        System.out.println("\n========== ALL TESTS COMPLETED ==========");
    }

    /**
     * Test Case 1: Example from problem statement
     * N=5, Q=1, A=[5, 4, 3, 2, 1]
     * Query: Type 2, l=2, r=4
     * Expected: 1*4 + 2*3 + 3*2 = 4 + 6 + 6 = 16
     */
    public static void testCase1() {
        int N = 5;
        int Q = 1;
        int[] A = {5, 4, 3, 2, 1};
        int[][] queries = {
            {2, 2, 4}
        };

        System.out.println("Input: N=" + N + ", Q=" + Q);
        System.out.println("Array A: " + Arrays.toString(A));
        System.out.println("Queries (Type, L, R): " + Arrays.deepToString(queries));
        System.out.println("Calculation: 1*4 + 2*3 + 3*2 = 4 + 6 + 6 = 16");

        int[] result = solve(N, Q, A.clone(), queries);

        System.out.println("Output: " + Arrays.toString(result));
        System.out.println("Expected: [16]");
        System.out.println("Status: " + (result.length > 0 && result[0] == 16 ? "✓ PASS" : "✗ FAIL"));
    }

    /**
     * Test Case 2: Multiple type 2 queries
     * N=4, Q=2, A=[1, 2, 3, 4]
     * Query 1: Type 2, l=1, r=4 => 1*1 + 2*2 + 3*3 + 4*4 = 1 + 4 + 9 + 16 = 30
     * Query 2: Type 2, l=1, r=2 => 1*1 + 2*2 = 1 + 4 = 5
     */
    public static void testCase2() {
        int N = 4;
        int Q = 2;
        int[] A = {1, 2, 3, 4};
        int[][] queries = {
            {2, 1, 4},
            {2, 1, 2}
        };

        System.out.println("Input: N=" + N + ", Q=" + Q);
        System.out.println("Array A: " + Arrays.toString(A));
        System.out.println("Queries (Type, L, R): " + Arrays.deepToString(queries));
        System.out.println("Calculation Q1: 1*1 + 2*2 + 3*3 + 4*4 = 30");
        System.out.println("Calculation Q2: 1*1 + 2*2 = 5");

        int[] result = solve(N, Q, A.clone(), queries);

        System.out.println("Output: " + Arrays.toString(result));
        System.out.println("Expected: [30, 5]");
        System.out.println("Status: " + (Arrays.equals(result, new int[]{30, 5}) ? "✓ PASS" : "✗ FAIL"));
    }

    /**
     * Test Case 3: Mix of type 1 and type 2 queries
     * N=3, Q=3, A=[1, 2, 3]
     * Query 1: Type 2, l=1, r=3 => 1*1 + 2*2 + 3*3 = 14
     * Query 2: Type 1, idx=2, val=5 => A becomes [1, 5, 3]
     * Query 3: Type 2, l=1, r=3 => 1*1 + 2*5 + 3*3 = 20
     */
    public static void testCase3() {
        int N = 3;
        int Q = 3;
        int[] A = {1, 2, 3};
        int[][] queries = {
            {2, 1, 3},
            {1, 2, 5},
            {2, 1, 3}
        };

        System.out.println("Input: N=" + N + ", Q=" + Q);
        System.out.println("Array A: " + Arrays.toString(A));
        System.out.println("Queries (Type, Param1, Param2): " + Arrays.deepToString(queries));
        System.out.println("Calculation Q1: 1*1 + 2*2 + 3*3 = 14");
        System.out.println("Calculation Q2: Update A[2]=5 => A becomes [1, 5, 3]");
        System.out.println("Calculation Q3: 1*1 + 2*5 + 3*3 = 20");

        int[] result = solve(N, Q, A.clone(), queries);

        System.out.println("Output: " + Arrays.toString(result));
        System.out.println("Expected: [14, 20]");
        System.out.println("Status: " + (Arrays.equals(result, new int[]{14, 20}) ? "✓ PASS" : "✗ FAIL"));
    }

    /**
     * Test Case 4: Single element queries
     * N=5, Q=4, A=[10, 20, 30, 40, 50]
     * Query 1: Type 2, l=3, r=3 => 1*30 = 30
     * Query 2: Type 1, idx=3, val=100
     * Query 3: Type 2, l=3, r=3 => 1*100 = 100
     * Query 4: Type 2, l=2, r=5 => 1*20 + 2*100 + 3*40 + 4*50 = 540
     */
    public static void testCase4() {
        int N = 5;
        int Q = 4;
        int[] A = {10, 20, 30, 40, 50};
        int[][] queries = {
            {2, 3, 3},
            {1, 3, 100},
            {2, 3, 3},
            {2, 2, 5}
        };

        System.out.println("Input: N=" + N + ", Q=" + Q);
        System.out.println("Array A: " + Arrays.toString(A));
        System.out.println("Queries (Type, Param1, Param2): " + Arrays.deepToString(queries));
        System.out.println("Calculation Q1: 1*30 = 30 (single element)");
        System.out.println("Calculation Q2: Update A[3]=100");
        System.out.println("Calculation Q3: 1*100 = 100");
        System.out.println("Calculation Q4: 1*20 + 2*100 + 3*40 + 4*50 = 540");

        int[] result = solve(N, Q, A.clone(), queries);

        System.out.println("Output: " + Arrays.toString(result));
        System.out.println("Expected: [30, 100, 540]");
        System.out.println("Status: " + (Arrays.equals(result, new int[]{30, 100, 540}) ? "✓ PASS" : "✗ FAIL"));
    }

    /**
     * Test Case 5: Large values
     * N=6, Q=2, A=[1000000, 2000000, 3000000, 4000000, 5000000, 6000000]
     * Query 1: Type 2, l=1, r=3 => 1*1M + 2*2M + 3*3M = 14M
     * Query 2: Type 2, l=4, r=6 => 1*4M + 2*5M + 3*6M = 32M
     */
    public static void testCase5() {
        int N = 6;
        int Q = 2;
        int[] A = {1000000, 2000000, 3000000, 4000000, 5000000, 6000000};
        int[][] queries = {
            {2, 1, 3},
            {2, 4, 6}
        };

        System.out.println("Input: N=" + N + ", Q=" + Q);
        System.out.println("Array A: " + Arrays.toString(A));
        System.out.println("Queries (Type, L, R): " + Arrays.deepToString(queries));
        System.out.println("Calculation Q1: 1*1M + 2*2M + 3*3M = 14,000,000");
        System.out.println("Calculation Q2: 1*4M + 2*5M + 3*6M = 32,000,000");

        int[] result = solve(N, Q, A.clone(), queries);

        System.out.println("Output: " + Arrays.toString(result));
        System.out.println("Expected: [14000000, 32000000]");
        System.out.println("Status: " + (Arrays.equals(result, new int[]{14000000, 32000000}) ? "✓ PASS" : "✗ FAIL"));
    }

    /**
     * Test Case 6: Edge case - entire array
     * N=5, Q=2, A=[2, 4, 6, 8, 10]
     * Query 1: Type 2, l=1, r=5 => 1*2 + 2*4 + 3*6 + 4*8 + 5*10 = 110
     * Query 2: Type 2, l=1, r=5 => Same calculation = 110
     */
    public static void testCase6() {
        int N = 5;
        int Q = 2;
        int[] A = {2, 4, 6, 8, 10};
        int[][] queries = {
            {2, 1, 5},
            {2, 1, 5}
        };

        System.out.println("Input: N=" + N + ", Q=" + Q);
        System.out.println("Array A: " + Arrays.toString(A));
        System.out.println("Queries (Type, L, R): " + Arrays.deepToString(queries));
        System.out.println("Calculation: 1*2 + 2*4 + 3*6 + 4*8 + 5*10 = 2+8+18+32+50 = 110");

        int[] result = solve(N, Q, A.clone(), queries);

        System.out.println("Output: " + Arrays.toString(result));
        System.out.println("Expected: [110, 110]");
        System.out.println("Status: " + (Arrays.equals(result, new int[]{110, 110}) ? "✓ PASS" : "✗ FAIL"));
    }

    /**
     * Test Case 7: Multiple updates to same index
     * N=3, Q=5, A=[1, 1, 1]
     * Query 1: Type 2, l=1, r=3 => 1*1 + 2*1 + 3*1 = 6
     * Query 2: Type 1, idx=2, val=10
     * Query 3: Type 2, l=1, r=3 => 1*1 + 2*10 + 3*1 = 24
     * Query 4: Type 1, idx=2, val=5
     * Query 5: Type 2, l=1, r=3 => 1*1 + 2*5 + 3*1 = 14
     */
    public static void testCase7() {
        int N = 3;
        int Q = 5;
        int[] A = {1, 1, 1};
        int[][] queries = {
            {2, 1, 3},
            {1, 2, 10},
            {2, 1, 3},
            {1, 2, 5},
            {2, 1, 3}
        };

        System.out.println("Input: N=" + N + ", Q=" + Q);
        System.out.println("Array A: " + Arrays.toString(A));
        System.out.println("Queries (Type, Param1, Param2): " + Arrays.deepToString(queries));
        System.out.println("Calculation Q1: 1*1 + 2*1 + 3*1 = 6");
        System.out.println("Calculation Q3: After update, 1*1 + 2*10 + 3*1 = 24");
        System.out.println("Calculation Q5: After second update, 1*1 + 2*5 + 3*1 = 14");

        int[] result = solve(N, Q, A.clone(), queries);

        System.out.println("Output: " + Arrays.toString(result));
        System.out.println("Expected: [6, 24, 14]");
        System.out.println("Status: " + (Arrays.equals(result, new int[]{6, 24, 14}) ? "✓ PASS" : "✗ FAIL"));
    }

    /**
     * Test Case 8: Zero values
     * N=4, Q=3, A=[0, 0, 0, 0]
     * Query 1: Type 2, l=1, r=4 => 0
     * Query 2: Type 1, idx=3, val=7
     * Query 3: Type 2, l=1, r=4 => 1*0 + 2*0 + 3*7 + 4*0 = 21
     */
    public static void testCase8() {
        int N = 4;
        int Q = 3;
        int[] A = {0, 0, 0, 0};
        int[][] queries = {
            {2, 1, 4},
            {1, 3, 7},
            {2, 1, 4}
        };

        System.out.println("Input: N=" + N + ", Q=" + Q);
        System.out.println("Array A: " + Arrays.toString(A));
        System.out.println("Queries (Type, Param1, Param2): " + Arrays.deepToString(queries));
        System.out.println("Calculation Q1: All zeros = 0");
        System.out.println("Calculation Q3: After update, 1*0 + 2*0 + 3*7 + 4*0 = 21");

        int[] result = solve(N, Q, A.clone(), queries);

        System.out.println("Output: " + Arrays.toString(result));
        System.out.println("Expected: [0, 21]");
        System.out.println("Status: " + (Arrays.equals(result, new int[]{0, 21}) ? "✓ PASS" : "✗ FAIL"));
    }

    /**
     * Test Case 9: Negative values
     * N=4, Q=2, A=[-5, -3, -2, -1]
     * Query 1: Type 2, l=1, r=4 => 1*(-5) + 2*(-3) + 3*(-2) + 4*(-1) = -5 -6 -6 -4 = -21
     * Query 2: Type 2, l=2, r=3 => 1*(-3) + 2*(-2) = -3 -4 = -7
     */
    public static void testCase9() {
        int N = 4;
        int Q = 2;
        int[] A = {-5, -3, -2, -1};
        int[][] queries = {
            {2, 1, 4},
            {2, 2, 3}
        };

        System.out.println("Input: N=" + N + ", Q=" + Q);
        System.out.println("Array A: " + Arrays.toString(A));
        System.out.println("Queries (Type, L, R): " + Arrays.deepToString(queries));
        System.out.println("Calculation Q1: 1*(-5) + 2*(-3) + 3*(-2) + 4*(-1) = -21");
        System.out.println("Calculation Q2: 1*(-3) + 2*(-2) = -7");

        int[] result = solve(N, Q, A.clone(), queries);

        System.out.println("Output: " + Arrays.toString(result));
        System.out.println("Expected: [-21, -7]");
        System.out.println("Status: " + (Arrays.equals(result, new int[]{-21, -7}) ? "✓ PASS" : "✗ FAIL"));
    }

    /**
     * Test Case 10: Complex sequence with mixed operations
     * N=7, Q=6, A=[5, 10, 15, 20, 25, 30, 35]
     * Query 1: Type 2, l=2, r=5 => 1*10 + 2*15 + 3*20 + 4*25 = 200
     * Query 2: Type 1, idx=4, val=50
     * Query 3: Type 2, l=2, r=5 => 1*10 + 2*15 + 3*50 + 4*25 = 260
     * Query 4: Type 2, l=1, r=7 => 1*5 + 2*10 + 3*15 + 4*50 + 5*25 + 6*30 + 7*35 = 1115
     * Query 5: Type 1, idx=1, val=100
     * Query 6: Type 2, l=1, r=3 => 1*100 + 2*10 + 3*15 = 155
     */
    public static void testCase10() {
        int N = 7;
        int Q = 6;
        int[] A = {5, 10, 15, 20, 25, 30, 35};
        int[][] queries = {
            {2, 2, 5},
            {1, 4, 50},
            {2, 2, 5},
            {2, 1, 7},
            {1, 1, 100},
            {2, 1, 3}
        };

        System.out.println("Input: N=" + N + ", Q=" + Q);
        System.out.println("Array A: " + Arrays.toString(A));
        System.out.println("Queries (Type, Param1, Param2): " + Arrays.deepToString(queries));
        System.out.println("Calculation Q1: 1*10 + 2*15 + 3*20 + 4*25 = 200");
        System.out.println("Calculation Q3: After A[4]=50, 1*10 + 2*15 + 3*50 + 4*25 = 260");
        System.out.println("Calculation Q4: Full array, 1*5 + 2*10 + 3*15 + 4*50 + 5*25 + 6*30 + 7*35 = 1115");
        System.out.println("Calculation Q6: After A[1]=100, 1*100 + 2*10 + 3*15 = 155");

        int[] result = solve(N, Q, A.clone(), queries);

        System.out.println("Output: " + Arrays.toString(result));
        System.out.println("Expected: [200, 260, 1115, 155]");
        System.out.println("Status: " + (Arrays.equals(result, new int[]{200, 260, 1115, 155}) ? "✓ PASS" : "✗ FAIL"));
    }
}

