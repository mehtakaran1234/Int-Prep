package com.k2senterprise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveDuplicateFromArray {

    static int removeDuplicateInSameArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr);
        int j = 0; // Index for the next unique element
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[j]) {
                j++;
                arr[j] = arr[i];
            }
        }
        return j;
    }

    static void removeDuplicateInNewArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        Arrays.sort(arr);
        int j = 0; // Index for the next unique element
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[j]) {
                j++;
                arr[j] = arr[i];
            }
        }
        // Resize the array to contain only unique elements
        int[] uniqueArr = Arrays.copyOf(arr, j + 1);
        System.out.println("Unique Array: " + Arrays.toString(uniqueArr));
    }

    static List<Integer> removeDuplicatesAndReturnList(int[] arr) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] != arr[i + 1]) {
                list.add(arr[i]);
            }
        }
        list.add(arr[arr.length - 1]); // Add the last element
        return list;
    }

    public static void main(String[] args) {
        int a[] = {1,7, 2, 3,3, 4,4, 5, 6,1, 7,7,2,8, 9, 10};
        List<Integer> list = removeDuplicatesAndReturnList(a);
        System.out.println("Sorted Array: " + list);

        removeDuplicateInNewArray(a);

        int n = removeDuplicateInSameArray(a);
        for(int i = 0; i <= n; i++) {
            System.out.print(a[i] + " ");
        }



    }
}
