package com.k2senterprise;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidParenthesisTest {

    @Test
    void testValidParenthesis() {
        ValidParenthesis validParenthesis = new ValidParenthesis();

        // Test case 1: Valid nested parentheses
        assertTrue(validParenthesis.isValid("({[]})"), "Test case 1 failed");

        // Test case 2: Valid nested parentheses (repeated input)
        assertTrue(validParenthesis.isValid("({[]})"), "Test case 2 failed");

        // Test case 3: Simple valid parentheses
        assertTrue(validParenthesis.isValid("()"), "Test case 3 failed");

        // Test case 4: Multiple valid parentheses
        assertTrue(validParenthesis.isValid("()[]{}"), "Test case 4 failed");

        // Test case 5: Invalid parentheses
        assertFalse(validParenthesis.isValid("(]"), "Test case 5 failed");

        // Test case 6: Valid mixed parentheses
        assertTrue(validParenthesis.isValid("([])"), "Test case 6 failed");

        // Test case 7: Single invalid closing parenthesis
        assertFalse(validParenthesis.isValid("]"), "Test case 7 failed");
    }
}
