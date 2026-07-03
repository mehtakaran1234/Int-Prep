package com.k2senterprise.hackerearth;

import java.io.*;
import java.util.*;


public class TestClass {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        int N = Integer.parseInt(br.readLine().trim());
        int[] A = new int[N];
        String[] arr_A = br.readLine().split(" ");
        for(int i_A = 0; i_A < N; i_A++) {
            A[i_A] = Integer.parseInt(arr_A[i_A]);
        }
        int out_ = Solve(N, A);
        wr.println(out_);
        wr.flush();

        wr.close();
        br.close();
    }
    static int Solve(int N, int[] A){
        // Find the number with the smallest absolute value (closest to zero)
        int result = A[0];
        int minAbsValue = Math.abs(A[0]);

        for(int i = 1; i < N; i++){
            int absValue = Math.abs(A[i]);
            if(absValue < minAbsValue || (absValue == minAbsValue && A[i] > result)){
                minAbsValue = absValue;
                result = A[i];
            }
        }
        return result;
    }
}
