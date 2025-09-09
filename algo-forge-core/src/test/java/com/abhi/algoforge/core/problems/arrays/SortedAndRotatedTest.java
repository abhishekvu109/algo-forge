package com.abhi.algoforge.core.problems.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SortedAndRotatedTest {

    private static final SortedAndRotated.ImplementationType type = SortedAndRotated.ImplementationType.BRUTE_FORCE;

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        assertFalse(SortedAndRotated.sortedAndRotated(arr, SortedAndRotated.ImplementationType.BRUTE_FORCE));
    }

    @Test
    public void testSingleElementArray() {
        int[] arr = {5};
        assertFalse(SortedAndRotated.sortedAndRotated(arr, SortedAndRotated.ImplementationType.BRUTE_FORCE));
    }

    @Test
    public void testAscendingRotatedArray() {
        int[] arr = {3, 4, 5, 1, 2};
        assertTrue(SortedAndRotated.sortedAndRotated(arr, SortedAndRotated.ImplementationType.BRUTE_FORCE));
    }

    @Test
    public void testAscendingSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        assertFalse(SortedAndRotated.sortedAndRotated(arr, SortedAndRotated.ImplementationType.BRUTE_FORCE));
    }

    @Test
    public void testDescendingRotatedArray() {
        int[] arr = {3, 2, 1, 5, 4};
        assertTrue(SortedAndRotated.sortedAndRotated(arr, SortedAndRotated.ImplementationType.BRUTE_FORCE));
    }

    @Test
    public void testDescendingSortedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        assertFalse(SortedAndRotated.sortedAndRotated(arr, SortedAndRotated.ImplementationType.BRUTE_FORCE));
    }

    @Test
    public void testNonRotatedUnsortedArray() {
        int[] arr = {1, 3, 2, 5, 4};
        assertFalse(SortedAndRotated.sortedAndRotated(arr, SortedAndRotated.ImplementationType.BRUTE_FORCE));
    }

    @Test
    public void testMultipleBreaksInAscending() {
        int[] arr = {1, 3, 2, 4, 0};
        assertFalse(SortedAndRotated.sortedAndRotated(arr, SortedAndRotated.ImplementationType.BRUTE_FORCE));
    }

    @Test
    public void testMultipleBreaksInDescending() {
        int[] arr = {5, 3, 4, 1, 2};
        assertFalse(SortedAndRotated.sortedAndRotated(arr, SortedAndRotated.ImplementationType.BRUTE_FORCE));
    }

    @Test
    public void testArrayWithDuplicates() {
        int[] arr = {2, 2, 2, 2};
        assertFalse(SortedAndRotated.sortedAndRotated(arr, SortedAndRotated.ImplementationType.BRUTE_FORCE));
    }

    @Test
    public void testNullType() {
        int[] arr = {1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> {
            SortedAndRotated.sortedAndRotated(arr, null);
        });
    }
}