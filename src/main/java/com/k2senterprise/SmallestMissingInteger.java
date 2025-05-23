package com.k2senterprise;

import java.util.LinkedHashMap;
import java.util.Map;

public class SmallestMissingInteger {

    public static int solution(int[] A) {

        Map<Integer, Integer> map = new LinkedHashMap<>();
        int missing = 1;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                map.put(A[i], 1);
            }
        }
        if (map.size() > 0) {
            for (Integer i = 1; i <= map.size(); i++) {
                missing = i + 1;
                if (!map.containsKey(i)) {
                    return i;
                }
            }
        }
        return missing;
    }

    public static void main(String[] args) {
        /* For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
        Given A = [1, 2, 3], the function should return 4.
        Given A = [−1, −3], the function should return 1.*/
        int[] A = {1, 3, 6, 4, 1, 2};
        System.out.println("The smallest missing positive integer is: " + solution(A));

        A = new int[]{1, 2, 3};
        System.out.println("The smallest missing positive integer is: " + solution(A));

        A = new int[]{-1, -3};
        System.out.println("The smallest missing positive integer is: " + solution(A));
    }
}
