package com.abhi.algoforge.core.arrays;

/*In a realm where numbers hold secrets, a captivating challenge awaits, which is to, Maximum Difference Problem with Order !!!

Our Task: Given an array arr[] of integers, find out the maximum difference between any two elements such that the larger element appears after the smaller number.



Examples :

Input : arr = {2, 3, 10, 6, 4, 8, 1}
Output : 8
Explanation : The maximum difference is between 10 and 2.

Input : arr = {7, 9, 5, 6, 3, 2}
Output : 2
Explanation : The maximum difference is between 9 and 7.*/


public class MaximumDifferenceWithOrder {
    public enum Approach {
        NAIVE_SOLUTION {
            @Override
            public int solve(int[] arr) {
                int N = arr.length;
                int MAX_DIFF = Integer.MIN_VALUE;
                for (int i = 0; i < N; i++) {
                    for (int j = i + 1; j < N; j++) {
                        MAX_DIFF = Math.max(MAX_DIFF, arr[j] - arr[i]);
                    }
                }
                return MAX_DIFF;
            }
        },
        USING_ARRAY {
            @Override
            public int solve(int[] arr) {
                int N = arr.length;
                int MAX_DIFF = Integer.MIN_VALUE;
                int[] lMin = new int[N];
                lMin[0] = arr[0];
                int[] rMax = new int[N];
                rMax[N - 1] = arr[N - 1];
                for (int i = 1; i < N; i++) {
                    int lastIndex = N - i - 1;
                    lMin[i] = Math.min(arr[i], lMin[i - 1]);
                    rMax[lastIndex] = Math.max(arr[lastIndex], rMax[lastIndex + 1]);
                }
                for (int i = 0; i < N; i++) {
                    MAX_DIFF = Math.max(MAX_DIFF, rMax[i] - lMin[i]);
                }
                return MAX_DIFF;
            }
        }, OPTIMAL_SOLUTION {
            @Override
            public int solve(int[] arr) {
                int N = arr.length;
                int result = arr[1] - arr[0], minValue = arr[0];
                for (int j = 1; j < N; j++) {
                    result = Math.max(result, arr[j] - minValue);
                    minValue = Math.min(minValue, arr[j]);
                }
                return minValue;
            }
        };

        public abstract int solve(int[] arr);
    }

    public int maximumDifferenceWithOrder(int[] arr, Approach approach) {
        return approach.solve(arr);
    }

    public int maximumDifferenceWithOrder(int[] arr) {
        return maximumDifferenceWithOrder(arr, Approach.OPTIMAL_SOLUTION);
    }
}
