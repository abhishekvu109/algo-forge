package com.abhi.algoforge.core.problems.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindMaxIndexDifferenceTest {

    FindMaxIndexDifference.ImplementationType type = FindMaxIndexDifference.ImplementationType.SORTING_APPROACH;

    @Test
    void testMaxIndexDifferenceBruteForce() {
        // Example 1
        int[] arr1 = {1, 10};
        int result1 = FindMaxIndexDifference.maxIndexDifference(arr1, type);
        assertEquals(1, result1, "Should return 1 for array {1, 10}");

        // Example 2
        int[] arr2 = {5, 4, 3};
        int result2 = FindMaxIndexDifference.maxIndexDifference(arr2, type);
        assertEquals(0, result2, "Should return 0 when no valid pair exists");

        // Example 3
        int[] arr3 = {34, 8, 10, 3, 2, 80, 30, 33, 1};
        int result3 = FindMaxIndexDifference.maxIndexDifference(arr3, type);
        assertEquals(6, result3, "Should return 6 for array {34, 8, 10, 3, 2, 80, 30, 33, 1}");

        // Example 3
        int[] arr4 = {28, 19, 21, 14, 24, 22, 16, 15, 22, 16, 22, 19, 27, 8, 27};
        int result4 = FindMaxIndexDifference.maxIndexDifference(arr4, type);
        assertEquals(13, result4, "Should return 13 for array {28 ,19 ,21 ,14 ,24 ,22 ,16, 15 ,22 ,16, 22 ,19 ,27, 8, 27}");
    }

    @Test
    void testMaxIndexDifferenceWithSingleElement() {
        int[] arr = {5};
        int result = FindMaxIndexDifference.maxIndexDifference(arr, type);
        assertEquals(0, result, "Should return Integer.MIN_VALUE when only one element is present");
    }

    @Test
    void testMaxIndexDifferenceWithEmptyArray() {
        int[] arr = {};
        int result = FindMaxIndexDifference.maxIndexDifference(arr, type);
        assertEquals(0, result);
    }

    @Test
    void testMaxIndexDifferenceWithNoValidPairs() {
        int[] arr = {10, 9, 8, 7, 6};
        int result = FindMaxIndexDifference.maxIndexDifference(arr, type);
        assertEquals(0, result, "Should return 0 when no valid pairs exist");
    }

    @Test
    void testIllegalImplementationType() {
        int[] arr = {1, 2, 3};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            FindMaxIndexDifference.maxIndexDifference(arr, null);
        });
        assertEquals("Cannot be null.", exception.getMessage());
    }

    @Test
    void testNullArray() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            FindMaxIndexDifference.maxIndexDifference(null, type);
        });
        assertNotNull(exception.getMessage());
    }
}