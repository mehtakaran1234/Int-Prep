package com.k2senterprise;

import java.util.Arrays;

public class FlatMapUsage {
    public static void main(String[] args) {
        int a[][] = {{1, 2, 3}, {4, 5, 6, 11, 12}, {7, 8, 9, 10}, {13, 14, 15}, {16},};

        int totalLength = 0;
        for (int i = 0; i < a.length; i++) {
            totalLength += a[i].length;
        }
        int[] outputArr = new int[totalLength];
        System.out.println(outputArr.length);

        int k = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                outputArr[k] = a[i][j];
                k++;
            }

        }
        System.out.println(Arrays.toString(outputArr));


        int[] arr = Arrays.stream(a).flatMapToInt(Arrays::stream).toArray();
        System.out.println("Flattened array: " + Arrays.toString(arr));
    }
}
