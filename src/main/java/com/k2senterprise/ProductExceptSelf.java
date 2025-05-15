package com.k2senterprise;

public class ProductExceptSelf {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5,6};
        // output 24, 12, 8, 6
        // output 120, 60, 40, 30, 24

        for(int i = 0; i < arr.length; i++){
            int product = 1;
            for(int j = 0; j < arr.length; j++){
                if(i != j){
                    product *= arr[j];
                }
            }
            System.out.print(product + " ");

        }
    }
}
