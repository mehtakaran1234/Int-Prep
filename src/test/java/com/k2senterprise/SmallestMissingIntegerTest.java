package com.k2senterprise;

// Test cases for SmallestMissing.solution
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SmallestMissingIntegerTest {

    @Test
    void testExampleCases() {
        assertEquals(5, SmallestMissingInteger.solution(new int[]{1, 3, 6, 4, 1, 2}));
        assertEquals(4, SmallestMissingInteger.solution(new int[]{1, 2, 3}));
        assertEquals(1, SmallestMissingInteger.solution(new int[]{-1, -3}));
    }

    @Test
    void testEmptyArray() {
        assertEquals(1, SmallestMissingInteger.solution(new int[]{}));
    }

    @Test
    void testAllNegatives() {
        assertEquals(1, SmallestMissingInteger.solution(new int[]{-5, -2, -100}));
    }

    @Test
    void testAllPositivesConsecutive() {
        assertEquals(6, SmallestMissingInteger.solution(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    void testAllPositivesNonConsecutive() {
        assertEquals(2, SmallestMissingInteger.solution(new int[]{1, 3, 4, 5}));
    }

    @Test
    void testWithZero() {
        assertEquals(1, SmallestMissingInteger.solution(new int[]{0}));
        assertEquals(2, SmallestMissingInteger.solution(new int[]{1, 0}));
    }

    @Test
    void testWithDuplicates() {
        assertEquals(2, SmallestMissingInteger.solution(new int[]{1, 1, 1, 1}));
        assertEquals(3, SmallestMissingInteger.solution(new int[]{1, 2, 2, 1}));
    }

    @Test
    void testLargeNumbers() {
        assertEquals(1, SmallestMissingInteger.solution(new int[]{100, 200, 300}));
        assertEquals(2, SmallestMissingInteger.solution(new int[]{1, 100, 200}));
    }

    @Test
    void testSingleElement() {
        assertEquals(2, SmallestMissingInteger.solution(new int[]{1}));
        assertEquals(1, SmallestMissingInteger.solution(new int[]{2}));
        assertEquals(1, SmallestMissingInteger.solution(new int[]{-1}));
    }
}
