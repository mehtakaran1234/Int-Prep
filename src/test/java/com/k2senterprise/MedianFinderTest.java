package com.k2senterprise;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MedianFinderTest {

    @Test
    void testOddLengthArray() {
        int[] arr = {12, 3, 5, 7, 4, 19, 26};
        assertEquals(7, MedianFinder.getMedian(arr));
    }

    @Test
    void testEvenLengthArray() {
        int[] arr = {12, 3, 5, 7, 4, 26};
        assertEquals(6, MedianFinder.getMedian(arr));
    }

    @Test
    void testSingleElementArray() {
        int[] arr = {42};
        assertEquals(42, MedianFinder.getMedian(arr));
    }

    @Test
    void testTwoElementArray() {
        int[] arr = {10, 20};
        assertEquals(15, MedianFinder.getMedian(arr));
    }

    @Test
    void testAlreadySortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        assertEquals(3, MedianFinder.getMedian(arr));
    }

    @Test
    void testArrayWithNegativeNumbers() {
        int[] arr = {-5, -1, -3, -2, -4};
        assertEquals(-3, MedianFinder.getMedian(arr));
    }

    @Test
    void testArrayWithDuplicates() {
        int[] arr = {2, 2, 2, 2, 2};
        assertEquals(2, MedianFinder.getMedian(arr));
    }
}
