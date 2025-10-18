package com.k2senterprise;

public class CharctersCanFormPalindrome {
    public static void main(String[] args) {
        String str = "geeksogeeks";
        str = "geeksforgeeks";

        str = "hello";
        str= "aabbcc";
        str= "aabbcd";
        str = "annabelle";
        str = "ivicc";
        int evenCount = 0;
        int oddCount = 0;

        for(int i = 0; i < str.length(); i++){
            for(int j = 0; j<str.length();j++){
                if( i != j){
                    if(str.charAt(i) == str.charAt(j)){
                        evenCount++;
                        break;
                    }
                }
            }
            if(evenCount >= 1){
                evenCount = 0;
            }
            else {
                oddCount++;
            }
        }
        if(oddCount > 2){
            System.out.println("The string "+str+ " cannot be rearranged to form a palindrome");
        }
        else {
            System.out.println("The string "+str+ " can be rearranged to form a palindrome");
        }
    }
}
