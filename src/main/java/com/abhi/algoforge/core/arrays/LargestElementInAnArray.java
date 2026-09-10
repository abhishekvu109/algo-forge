package com.abhi.algoforge.core.arrays;

import java.util.Arrays;

public class LargestElementInAnArray {

    public static class Approach {
        public static final int NAIVE_SOLUTION = 0;
        public static final int USING_SORTING = 1;
    }

    public int largestElementInAnArray(int[] arr, int N) {
        return largestElementInAnArray(arr, N, Approach.NAIVE_SOLUTION);
    }

    public int largestElementInAnArray(int[] arr, int N, int approach) {
        return switch (approach) {
            case Approach.NAIVE_SOLUTION -> naiveSolution(arr, N);
            case Approach.USING_SORTING -> usingSorting(arr, N);
            default -> naiveSolution(arr, N);
        };
    }

    private int naiveSolution(int[] arr, int N) {
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            result = Math.max(result, arr[i]);
        }
        return result;
    }

    private int usingSorting(int[] arr, int N) {
        Arrays.sort(arr);
        return arr[N - 1];
    }

}
