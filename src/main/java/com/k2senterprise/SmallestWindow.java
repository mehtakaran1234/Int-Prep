package com.k2senterprise;

import java.util.Arrays;

public class SmallestWindow {

    public static String smallestWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        int[] charCount = new int[256];
        for (char c : t.toCharArray()) {
            charCount[c]++;
        }
        System.out.println(Arrays.toString(charCount));

        int left = 0, right = 0, minLength = Integer.MAX_VALUE, start = 0, count = t.length();

        while (right < s.length()) {
            if (charCount[s.charAt(right)] > 0) {
                count--;
            }
            charCount[s.charAt(right)]--;
            right++;

            while (count == 0) {
                if (right - left < minLength) {
                    minLength = right - left;
                    start = left;
                }
                charCount[s.charAt(left)]++;
                if (charCount[s.charAt(left)] > 0) {
                    count++;
                }
                left++;
            }
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(start, start + minLength);
    }

    public static void smallestWindowSelf(String s, String t) {
        if(s == null || t == null || s.length() < t.length()) {
            System.out.println("");
            return;
        }
        String split[] = s.split(" ");

    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(smallestWindow(s, t));
        s = "My name is Fran";
        t = "rim";
        System.out.println(smallestWindow(s, t));
        s = "I am the greatest";
        t = "Imt";
        System.out.println(smallestWindow(s, t));
    }
}
