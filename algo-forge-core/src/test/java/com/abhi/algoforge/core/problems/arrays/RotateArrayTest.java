package com.abhi.algoforge.core.problems.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RotateArrayTest {

    @Test
    void testRotateNormal() {
        int[] arr = {1, 2, 3, 4, 5};
        int[] expected = {3, 4, 5, 1, 2};
        RotateArray.execute(RotateArray.ImplementationType.BRUTE_FORCE, arr, 2);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testRotateByZero() {
        int[] arr = {1, 2, 3};
        int[] expected = {1, 2, 3};
        RotateArray.execute(RotateArray.ImplementationType.BRUTE_FORCE, arr, 0);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testRotateByArrayLength() {
        int[] arr = {1, 2, 3};
        int[] expected = {1, 2, 3};
        RotateArray.execute(RotateArray.ImplementationType.BRUTE_FORCE, arr, arr.length);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testRotateEmptyArray() {
        int[] arr = {};
        int[] expected = {};
        RotateArray.execute(RotateArray.ImplementationType.BRUTE_FORCE, arr, 2);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testRotateSingleElement() {
        int[] arr = {42};
        int[] expected = {42};
        RotateArray.execute(RotateArray.ImplementationType.BRUTE_FORCE, arr, 1);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testRotateMoreThanLength() {
        int[] arr = {1, 2, 3, 4};
        int[] expected = {3, 4, 1, 2};
        // If d > N, we usually do d = d % N, so let's test with d = 6 (6 % 4 = 2)
        RotateArray.execute(RotateArray.ImplementationType.BRUTE_FORCE, arr, 6);
        assertArrayEquals(expected, arr);
    }

    @Test
    void testRotateNegativeD() {
        int[] arr = {1, 2, 3, 4};
        assertThrows(IllegalArgumentException.class, () -> {
            RotateArray.execute(RotateArray.ImplementationType.BRUTE_FORCE, arr, -1);
        });
    }

    @Test
    void testNullTypeThrowsException() {
        int[] arr = {1, 2, 3};
        assertThrows(IllegalArgumentException.class, () -> {
            RotateArray.execute(null, arr, 2);
        });
    }
}