package com.k2senterprise;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class ValidParenthesis {
    public List<Character> openBraces = Arrays.asList('{', '(', '[');
    public List<Character> closeBraces = Arrays.asList('}', ')', ']');


    public Stack<Character> stack = new Stack<Character>();

    public boolean isValid(String s) {

        for (int i = 0; i < s.length(); i++) {
            if (openBraces.contains(s.charAt(i)))
                stack.push(s.charAt(i));
            else if (closeBraces.contains(s.charAt(i))) {
                int index = closeBraces.indexOf(s.charAt(i));
                if (!stack.isEmpty() && stack.peek() == openBraces.get(index))
                    stack.pop();
                else
                    return false;
            }
        }
        if (stack.isEmpty())
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        ValidParenthesis validParenthesis = new ValidParenthesis();
        String s = "({[]})";
        boolean result = validParenthesis.isValid(s);
        System.out.println("Result: " + result);

        s = "({[]})";
        result = validParenthesis.isValid(s);
        System.out.println("Result: " + result);

        s = "()";
        result = validParenthesis.isValid(s);
        System.out.println("Result: " + result);

        s = "()[]{}";
        result = validParenthesis.isValid(s);
        System.out.println("Result: " + result);


        s = "(]";
        result = validParenthesis.isValid(s);
        System.out.println("Result: " + result);

        s = "([])";
        result = validParenthesis.isValid(s);
        System.out.println("Result: " + result);

        s = "]";
        result = validParenthesis.isValid(s);
        System.out.println("Result: " + result);

    }
}