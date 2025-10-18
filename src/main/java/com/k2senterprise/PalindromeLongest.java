package com.k2senterprise;


// Java program to find the longest
// palindromic substring.

import java.util.*;

class PalindromeLongest {

    // Function to check if a substring
    // s[low..high] is a palindrome
    static boolean checkPal(String s, int low, int high) {
        while (low < high) {


            if (s.charAt(low) != s.charAt(high))
                return false;
            low++;
            high--;
        }
        return true;
    }

    // Function to find the longest palindrome substring
    static String longestPalindrome(String s) {

        // Get length of input string
        int n = s.length();

        // All substrings of length 1 are palindromes
        int maxLen = 1, start = 0;

        // Nested loop to mark start and end index
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {

                // Check if the current substring is
                // a palindrome
                if (checkPal(s, i, j) && (j - i + 1) > maxLen) {
                    start = i;
                    maxLen = j - i + 1;
                }
            }
        }

        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        String s = "+forge_ekssk$%ee_g_for";
        System.out.println(longestPalindrome(s));
    }
}
