package com.k2senterprise;


public class PollutionProblemToptal {

    public static void main(String[] args) {
        /*double arr[] = {5, 19, 8, 1};
        System.out.println("Output: " + solution(arr));

        double[] arr1 = new double[]{10, 10};
        System.out.println("Output: " + solution(arr1));

        double[] arr2 = new double[]{3, 0, 5};
        System.out.println("Output: " + solution(arr2));

        double[] arr3 = new double[]{3, 5, 6, 1, 18};
        System.out.println("Output: " + solution(arr3));
*/
        double[] arr4 = new double[]{1,1,1};
        System.out.println("Output: " + solution(arr4));


    }

    public static int solution(double[] arr) {
        int operation = 0;
        double originalSum = 0;
        double max = Double.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            originalSum += arr[i];
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println("originalSum: " + originalSum + " Max: " + max);

        boolean flag = true;
        while (flag) {
            double countSum = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == max) {
                    arr[i] = (max / 2);
                    operation++;
                    max = arr[i];
                }
                if (max < arr[i]) {
                    max = arr[i];
                }
                countSum += arr[i];

            }
            if (countSum <= (originalSum / 2)) {
                break;
            }
        }

        return operation;
    }
}
