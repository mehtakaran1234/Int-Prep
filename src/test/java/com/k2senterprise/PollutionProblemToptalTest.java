package com.k2senterprise;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PollutionProblemToptalTest {

    @Test
    void testBasicCase() {
        double[] arr = {5, 19, 8, 1};
        assertEquals(3, PollutionProblemToptal.solution(arr), "Failed for input {5, 19, 8, 1}");
    }

    @Test
    void testEqualElements() {
        double[] arr = {10, 10};
        assertEquals(2, PollutionProblemToptal.solution(arr), "Failed for input {10, 10}");
    }

    @Test
    void testWithZero() {
        double[] arr = {3, 0, 5};
        assertEquals(2, PollutionProblemToptal.solution(arr), "Failed for input {3, 0, 5}");
    }

    @Test
    void testSingleElement() {
        double[] arr = {100};
        assertEquals(1, PollutionProblemToptal.solution(arr), "Failed for input {100}");
    }

    @Test
    void testAllZeros() {
        double[] arr = {0, 0, 0};
        assertEquals(0, PollutionProblemToptal.solution(arr), "Failed for input {0, 0, 0}");
    }

    @Test
    void testLargeNumbers() {
        double[] arr = {1e9, 1e9, 1e9};
        assertEquals(3, PollutionProblemToptal.solution(arr), "Failed for large numbers {1e9, 1e9, 1e9}");
    }

    @Test
    void testNegativeNumbers() {
        double[] arr = {-5, -19, -8, -1};
        assertEquals(0, PollutionProblemToptal.solution(arr), "Failed for negative numbers {-5, -19, -8, -1}");
    }

    @Test
    void testMixedPositiveAndNegative() {
        double[] arr = {5, -19, 8, -1};
        assertEquals(3, PollutionProblemToptal.solution(arr), "Failed for mixed numbers {5, -19, 8, -1}");
    }

    @Test
    void testEmptyArray() {
        double[] arr = {};
        assertEquals(0, PollutionProblemToptal.solution(arr), "Failed for empty array");
    }

    @Test
    void testFractionalValues() {
        double[] arr = {0.5, 1.5, 2.5};
        assertEquals(2, PollutionProblemToptal.solution(arr), "Failed for fractional values {0.5, 1.5, 2.5}");
    }

    @Test
    void testAlreadyReduced() {
        double[] arr = {1, 1, 1};
        assertEquals(0, PollutionProblemToptal.solution(arr), "Failed for already reduced array {1, 1, 1}");
    }
}
