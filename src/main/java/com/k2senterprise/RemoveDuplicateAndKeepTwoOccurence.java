package com.k2senterprise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RemoveDuplicateAndKeepTwoOccurence {
    public static void main(String[] args) {
        int a[] = {1, 7, 7, 7, 2, 4, 4, 6, 1, 1, 3, 3, 4, 4, 5, 5, 5, 6, 1, 7, 7, 2, 8, 9, 10};
        //[1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 7, 7, 7, 7, 7, 8, 9, 10]
        Arrays.sort(a);
        System.out.println("Original Array: " + Arrays.toString(a));
        List<Integer> list = new ArrayList<>();
        int occurrence = 3;
        int count = 0;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] == a[i + 1]) {
                count++;
            } else {
                count = 0;
            }
            if (count < occurrence) {
                list.add(a[i]);
            }
        }
        System.out.println("Sorted Array: " + list);

    }
}
