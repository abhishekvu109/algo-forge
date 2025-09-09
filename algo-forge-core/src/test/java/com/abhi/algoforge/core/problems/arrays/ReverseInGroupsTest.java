package com.abhi.algoforge.core.problems.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ReverseInGroupsTest {

    @Test
    public void testReverseInGroupsNormalCase() {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 3;
        int[] expected = {3, 2, 1, 5, 4};

        ReverseInGroups.execute(arr, k, ReverseInGroups.ImplementationType.BRUTE_FORCE);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testReverseInGroupsLessThanKRemainingElements() {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 3;
        int[] expected = {3, 2, 1, 5, 4};

        ReverseInGroups.execute(arr, k, ReverseInGroups.ImplementationType.BRUTE_FORCE);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testReverseInGroupsKEquals1() {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 1;
        int[] expected = {1, 2, 3, 4, 5};

        ReverseInGroups.execute(arr, k, ReverseInGroups.ImplementationType.BRUTE_FORCE);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testReverseInGroupsKEqualsArrayLength() {
        int[] arr = {1, 2, 3, 4};
        int k = 4;
        int[] expected = {4, 3, 2, 1};

        ReverseInGroups.execute(arr, k, ReverseInGroups.ImplementationType.BRUTE_FORCE);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testReverseInGroupsEmptyArray() {
        int[] arr = {};
        int k = 3;
        int[] expected = {};

        ReverseInGroups.execute(arr, k, ReverseInGroups.ImplementationType.BRUTE_FORCE);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testReverseInGroupsKGreaterThanArrayLength() {
        int[] arr = {1, 2, 3};
        int k = 5;
        int[] expected = {3, 2, 1};

        ReverseInGroups.execute(arr, k, ReverseInGroups.ImplementationType.BRUTE_FORCE);

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testReverseInGroupsNullImplementationType() {
        int[] arr = {1, 2, 3};
        int k = 3;

        assertThrows(IllegalArgumentException.class, () -> {
            ReverseInGroups.execute(arr, k, null);
        });
    }
}