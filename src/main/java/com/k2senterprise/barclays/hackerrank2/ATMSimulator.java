package com.k2senterprise.barclays.hackerrank2;

import java.util.*;

/**
 * ATM Simulator
 *
 * Input format (per operation): 6 integers
 * - If first == -1 : deposit operation. Next 5 integers are counts to add for denominations [20,50,100,200,500]
 * - If first > 0  : withdrawal operation. First integer is the amount to withdraw. Next 5 integers are ignored.
 *
 * The program reads an integer M = number of operations, then M lines each with 6 integers.
 * For each withdrawal operation it prints either 5 integers (counts of notes given for [20,50,100,200,500])
 * or -1 if the greedy withdrawal fails. Deposits produce no output.
 *
 * The greedy rule: try to use the largest denomination first (500), then 200, 100, 50, 20.
 * If greedy fails to make exact change, the withdrawal fails and the ATM state is unchanged.
 */
public class ATMSimulator {

    // Denominations in ascending order
    private static final int[] DENOMS = {20, 50, 100, 200, 500};

    private long[] stock = new long[5];

    public ATMSimulator() {
        Arrays.fill(stock, 0);
    }

    // Deposit counts: array length 5 corresponding to DENOMS order
    public void deposit(long[] counts) {
        for (int i = 0; i < 5; i++) {
            if (counts[i] < 0) continue; // ignore negative counts
            stock[i] += counts[i];
        }
    }

    // Try to withdraw amount using greedy approach. Returns array of 5 counts (DENOMS order) or null if fail.
    public long[] withdraw(int amount) {
        long[] give = new long[5];
        int remain = amount;
        // iterate from largest denom index 4 down to 0
        for (int i = 4; i >= 0; i--) {
            int denom = DENOMS[i];
            long maxNeeded = remain / denom;
            long take = Math.min(maxNeeded, stock[i]);
            give[i] = take;
            remain -= (int)(take * denom);
        }
        if (remain != 0) {
            // cannot fulfill exact amount with greedy approach
            return null;
        }
        // commit the withdrawal
        for (int i = 0; i < 5; i++) stock[i] -= give[i];
        return give;
    }

    // Process a batch of operations represented as int[][] ops where each row has 6 ints
    // Returns a list of outputs corresponding to each operation in order; for deposits returns null, for withdrawals returns long[]{...} or null to indicate -1
    public List<long[]> processOperations(int[][] ops) {
        List<long[]> outputs = new ArrayList<>();
        for (int[] row : ops) {
            if (row.length < 1) {
                outputs.add(null);
                continue;
            }
            if (row[0] == -1) {
                // deposit: next 5 values are counts
                long[] counts = new long[5];
                for (int i = 0; i < 5; i++) {
                    counts[i] = (i + 1 < row.length) ? row[i + 1] : 0;
                }
                deposit(counts);
                outputs.add(null); // indicate no output for deposit
            } else {
                int amount = row[0];
                long[] res = withdraw(amount);
                outputs.add(res);
            }
        }
        return outputs;
    }

    // Utility to print outputs in required format: For each operation, if deposit -> nothing, if withdrawal -> print 5 numbers or -1
    private static void printOutputs(List<long[]> outputs) {
        for (long[] out : outputs) {
            if (out == null) continue; // deposit or ignored
            if (out.length == 0) {
                System.out.println("-1");
                continue;
            }
            boolean failed = false;
            for (long v : out) {
                if (v < 0) failed = true;
            }
            if (failed || out == null) {
                System.out.println("-1");
            } else {
                // print in DENOMS order: 20 50 100 200 500
                // as integers
                System.out.println(out[0] + " " + out[1] + " " + out[2] + " " + out[3] + " " + out[4]);
            }
        }
    }

    // Parses input from stdin. Expected format:
    // M
    // op1_a op1_b op1_c op1_d op1_e op1_f
    // ... M lines
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean hasInput = sc.hasNextInt();
        if (!hasInput) {
            // No stdin provided - run sample tests
            runSampleTests();
            return;
        }
        int M = sc.nextInt();
        int[][] ops = new int[M][6];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 6; j++) {
                if (sc.hasNextInt()) ops[i][j] = sc.nextInt();
                else ops[i][j] = 0;
            }
        }
        ATMSimulator atm = new ATMSimulator();
        List<long[]> outputs = atm.processOperations(ops);
        // print outputs for withdrawals only
        for (int i = 0; i < outputs.size(); i++) {
            long[] out = outputs.get(i);
            if (out == null) continue; // deposit
            if (out == null) {
                System.out.println("-1");
            } else {
                if (out == null) System.out.println("-1");
                else System.out.println(out[0] + " " + out[1] + " " + out[2] + " " + out[3] + " " + out[4]);
            }
        }
    }

    // Sample tests to validate behavior
    private static void runSampleTests() {
        System.out.println("Running sample tests for ATMSimulator...\n");

        // Sample 1: deposit some bills then withdraw
        int[][] ops1 = new int[][]{
            {-1, 0, 0, 1, 2, 1}, // deposit: 0x20,0x50,1x100,2x200,1x500 => stock: [0,0,1,2,1]
            {600, 0, 0, 0, 0, 0}, // withdraw 600 -> greedy takes 1x500 then needs 100 -> stock has 1x100 -> success -> output: [0,0,1,0,1]
            {600, 0, 0, 0, 0, 0}, // withdraw 600 again -> now stock missing 100 -> fails -> output: -1
        };
        ATMSimulator atm1 = new ATMSimulator();
        List<long[]> out1 = atm1.processOperations(ops1);
        System.out.println("Sample 1 Outputs:");
        printOutputs(out1); // expect one line for first withdrawal and -1 for second

        // Sample 2: show greedy failing although possible combination exists
        int[][] ops2 = new int[][]{
            {-1, 0, 0, 0, 3, 1}, // deposit: 0x20,0x50,0x100,3x200,1x500
            {600, 0, 0, 0, 0, 0}  // withdraw 600 -> greedy picks 1x500 then cannot make 100 from 200s -> should fail (-1)
        };
        ATMSimulator atm2 = new ATMSimulator();
        List<long[]> out2 = atm2.processOperations(ops2);
        System.out.println("\nSample 2 Outputs:");
        printOutputs(out2); // expect -1

        // Sample 3: multiple withdrawals and deposits
        int[][] ops3 = new int[][]{
            {-1, 10, 10, 10, 10, 10}, // deposit many
            {370, 0, 0, 0, 0, 0}, // withdraw 370 -> greedy: 0x500,1x200,1x100,1x50,1x20 => [1x20,1x50,1x100,1x200,0x500]
            {30, 0, 0, 0, 0, 0}, // withdraw 30 -> cannot (20+10 no 10 denom) -> -1
            {-1, 0, 1, 0, 0, 0}, // deposit 1x50
            {30, 0, 0, 0, 0, 0} // withdraw 30 still -1 (no 10)
        };
        ATMSimulator atm3 = new ATMSimulator();
        List<long[]> out3 = atm3.processOperations(ops3);
        System.out.println("\nSample 3 Outputs:");
        printOutputs(out3);

        System.out.println("\nSample tests completed.");
    }
}

