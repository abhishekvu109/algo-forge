package com.abhi.algoforge.core.problems.arrays;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LargestAndSecondLargestTest {

    private static final LargestAndSecondLargest.ImplementationType type = LargestAndSecondLargest.ImplementationType.BRUTE_FORCE_USING_SORTING;

    @Test
    void testExample1() {
        int[] arr = {2, 1, 2};
        ArrayList<Integer> result = LargestAndSecondLargest.largestAndSecondLargestObjectInAnArray(arr, type);
        assertEquals(2, result.get(0));
        assertEquals(1, result.get(1));
    }

    @Test
    void testExample2() {
        int[] arr = {3, 3, 3};
        ArrayList<Integer> result = LargestAndSecondLargest.largestAndSecondLargestObjectInAnArray(arr, type);
        assertEquals(3, result.get(0));
        assertEquals(-1, result.get(1));
    }

    @Test
    void testSingleElementArray() {
        int[] arr = {5};
        ArrayList<Integer> result = LargestAndSecondLargest.largestAndSecondLargestObjectInAnArray(arr, type);
        assertEquals(5, result.get(0));
        assertEquals(-1, result.get(1));
    }

    @Test
    void testSecondMaximumExists() {
        int[] arr = {10, 20, 20, 5, 5, 15};
        ArrayList<Integer> result = LargestAndSecondLargest.largestAndSecondLargestObjectInAnArray(arr, type);
        assertEquals(20, result.get(0));
        assertEquals(15, result.get(1));
    }

    @Test
    void testAllElementsSame() {
        int[] arr = {7, 7, 7, 7};
        ArrayList<Integer> result = LargestAndSecondLargest.largestAndSecondLargestObjectInAnArray(arr, type);
        assertEquals(7, result.get(0));
        assertEquals(-1, result.get(1));
    }

    @Test
    void testAlreadySortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        ArrayList<Integer> result = LargestAndSecondLargest.largestAndSecondLargestObjectInAnArray(arr, type);
        assertEquals(5, result.get(0));
        assertEquals(4, result.get(1));
    }

    @Test
    void testReverseSortedArray() {
        int[] arr = {9, 7, 5, 3};
        ArrayList<Integer> result = LargestAndSecondLargest.largestAndSecondLargestObjectInAnArray(arr, type);
        assertEquals(9, result.get(0));
        assertEquals(7, result.get(1));
    }

    @Test
    void testInvalidImplementationType() {
        int[] arr = {1, 2, 3};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            LargestAndSecondLargest.largestAndSecondLargestObjectInAnArray(arr, null);
        });
        assertEquals("Invalid type.", exception.getMessage());
    }


}