package com.abhi.algoforge.core.problems.arrays;

import java.util.ArrayList;
import java.util.Arrays;

/*
Given an array arr[] of positive integers which may have duplicates. The task is to find the maximum and second maximum from the array,
and both of them should be different from each other, and If no second maximum exists, then the second maximum will be -1.

Examples :

Input: arr[] = [2, 1, 2]
Output: [2, 1]
Explanation: In the given array elements, 2 is the maximum and 1 is the second maximum.
Input: arr[] = [3, 3, 3]
Output: [3, -1]
Explanation: In the given array, 3 is the maximum, and since no distinct smaller element exists, the second maximum is -1.
Constraints:
1 ≤ arr.size() ≤ 105
1 ≤ arr[i] ≤ 106
* */
public class LargestAndSecondLargest {
    private static final LargestAndSecondLargest INSTANCE = new LargestAndSecondLargest();

    private LargestAndSecondLargest() {
    }

    public enum ImplementationType {
        BRUTE_FORCE_USING_SORTING, OPTIMIZED
    }

    public static ArrayList<Integer> largestAndSecondLargestObjectInAnArray(int[] arr, ImplementationType type) {
        if (type == null) {
            throw new IllegalArgumentException("Invalid type.");
        }
        if (arr.length == 0) {
            ArrayList<Integer> output = new ArrayList<>();
            output.add(-1);
            return output;
        }
        switch (type) {
            case BRUTE_FORCE_USING_SORTING -> {
                return INSTANCE.bruteForce(arr);
            }
            case OPTIMIZED -> {
                return INSTANCE.optimized(arr);
            }
            default -> {
                throw new IllegalArgumentException("Invalid type.");
            }
        }
    }

    private ArrayList<Integer> bruteForce(int[] arr) {
        int size = arr.length;
        Arrays.sort(arr);
        ArrayList<Integer> output = new ArrayList<>();
        output.add(arr[size - 1]);
        for (int i = size - 2; i >= 0; i--) {
            if (arr[i] != arr[i + 1]) {
                output.add(arr[i]);
            }
            if (output.size() == 2) {
                break;
            }
        }
        if (output.size() < 2) {
            output.add(-1);
        }
        return output;
    }

    private ArrayList<Integer> optimized(int[] arr) {
        ArrayList<Integer> output = new ArrayList<>();
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        for (int j : arr) {
            max = Math.max(j, max);
        }
        output.add(max);
        int secondMax = Integer.MIN_VALUE;
        for (int j : arr) {
            if (j > secondMax && j != max) {
                secondMax = j;
            }
        }
        secondMax = (secondMax == Integer.MIN_VALUE) ? -1 : secondMax;
        output.add(secondMax);
        return output;
    }

}
