package com.abhi.algoforge.core.arrays;

import java.util.Arrays;

public class LargestElementInAnArray {

    public enum Approach {
        NAIVE_SOLUTION {
            @Override
            public int solve(int[] arr, int N) {
                int result = Integer.MIN_VALUE;
                for (int i = 0; i < N; i++) {
                    result = Math.max(result, arr[i]);
                }
                return result;
            }
        },
        USING_SORTING {
            @Override
            public int solve(int[] arr, int N) {
                Arrays.sort(arr);
                return arr[N - 1];
            }
        };

        public abstract int solve(int[] arr, int N);
    }

    public int largestElementInAnArray(int[] arr, int N, Approach approach) {
        return approach.solve(arr, N);
    }

    public int largestElementInAnArray(int[] arr, int N) {
        return largestElementInAnArray(arr, N, Approach.NAIVE_SOLUTION);
    }

}
