package com.k2senterprise;

import java.io.*;
import java.util.*;


public class HackerEarth {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine().trim());
        String[] arr_arr = br.readLine().split(" ");
        int[] arr = new int[n];
        for(int i_arr = 0; i_arr < arr_arr.length; i_arr++)
        {
            arr[i_arr] = Integer.parseInt(arr_arr[i_arr]);
        }

        int out_ = FindIt(arr);
        System.out.println(out_);

        wr.close();
        br.close();
    }
    static int FindIt(int[] arr){
        // Write your code here
        int result = 0;

        return result;

    }
}
