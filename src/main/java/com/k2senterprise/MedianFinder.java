package com.k2senterprise;

import java.util.Arrays;

public class MedianFinder {

    public static void main(String[] args) {
        int[] arr = {12, 3, 5, 7, 4, 19, 26};
        int out = getMedian(arr);
        System.out.println("Median is: " + out);
        arr = new int[] {12, 3, 5, 7, 4, 26};
        out = getMedian(arr);
        System.out.println("Median is: " + out);
    }

    public static int getMedian(int[] arr) {
        Arrays.sort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
        int n = arr.length;
        if (n % 2 != 0) {
            return arr[n / 2];
        } else {
            return (arr[(n / 2)-1] + arr[(n / 2)]) / 2;
        }
    }
}
