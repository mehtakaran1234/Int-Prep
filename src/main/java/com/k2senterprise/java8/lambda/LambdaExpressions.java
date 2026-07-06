package com.k2senterprise.java8.lambda;

interface If1{
    boolean fun(int n);
}
interface Func {
    // n is some natural number whose
    // factorial is to be computed
    long fact(long n);
}

public class LambdaExpressions {
    public static void main(String[] args) {
        If1 if1 = (x) -> (x%2 ==0 );

        System.out.println(if1.fun(7));

        Func f = (n) ->
        {
            // Block body

            // Initially initializing with 1
            long res = 1;

            // iterating from 1 to the current number
            // to find factorial by multiplication
            for (int i = 1; i <= n; i++)
                res = i * res;
            return res;
        };

        // Calling lambda function

        // Print and display n the console
        System.out.println("Factorial of 5 : " + f.fact(50));
    }


}
