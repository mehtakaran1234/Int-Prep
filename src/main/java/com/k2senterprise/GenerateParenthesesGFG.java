package com.k2senterprise;

// java program to generate all the combinations of balanced
// parenthesis.
import java.util.ArrayList;
class GenerateParenthesesGFG {

    // Function to generate valid parentheses sequences
    static void validParentheses(int n, int open, String curr,
                                 ArrayList<String> res) {

        // If the current sequence has reached the length of 2 * n,
        // add it to the result
        if (curr.length() == 2 * n) {
            res.add(curr);
            return;
        }

        // Add opening parenthesis if we haven't used all n opening
        // parentheses
        if (open < n)
            validParentheses(n, open + 1, curr + "(", res);

        // Add closing parenthesis if the number of
        // closing parentheses is less than the number of opening ones
        if (curr.length() - open < open)
            validParentheses(n, open, curr + ")", res);
    }

    // Function to return all valid parentheses sequences
    static ArrayList<String> generateParentheses(int n) {
        ArrayList<String> res = new ArrayList<>();

        // Start recursion with 0 open parentheses and an empty string
        validParentheses(n/2, 0, "", res);
        return res;
    }

    public static void main(String[] args) {
        int n = 7;
        ArrayList<String> res = generateParentheses(n);

        for (String seq : res) {
            System.out.println(seq);
        }
    }
}
