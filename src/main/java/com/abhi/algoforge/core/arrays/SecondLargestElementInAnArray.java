package com.abhi.algoforge.core.arrays;

import java.util.Arrays;

public class SecondLargestElementInAnArray {
    public enum Approach {
        NAIVE_SOLUTION {
            @Override
            public int solve(int[] arr, int N) {
                int FIRST_LARGEST = new LargestElementInAnArray().largestElementInAnArray(arr, N);
                int SECOND_LARGEST = Integer.MIN_VALUE;
                for (int i = 0; i < N; i++) {
                    if (arr[i] != FIRST_LARGEST && arr[i] > SECOND_LARGEST) {
                        SECOND_LARGEST = arr[i];
                    }
                }
                return SECOND_LARGEST;
            }
        },
        USING_SORTING {
            @Override
            public int solve(int[] arr, int N) {
                Arrays.sort(arr);
                return arr[N - 2];
            }
        };

        public abstract int solve(int[] arr, int N);
    }

    public int secondLargestElementInAnArray(int[] arr, int N, Approach approach) {
        return approach.solve(arr, N);
    }

    public int secondLargestElementInAnArray(int[] arr, int N) {
        return secondLargestElementInAnArray(arr, N, Approach.NAIVE_SOLUTION);
    }
}
