package com.abhi.algoforge.core.arrays;

import java.util.Deque;
import java.util.LinkedList;

public class MaximumCircularSubarraySum {
    public enum Approach {
        NAIVE_SOLUTION {
            @Override
            public int solve(int[] arr) {
                int N = arr.length;
                int maxSum = Integer.MIN_VALUE;
                for (int i = 0; i < N; i++) {
                    int currentSum = 0, currentPos = i;
                    for (int j = 0; j < N; j++) {
                        currentSum += arr[currentPos % N];
                        currentPos++;
                        maxSum = Math.max(maxSum, currentSum);
                    }
                }
                return maxSum;
            }
        },
        LINEAR_SOLUTION_KADANE_ALGORITHM {
            @Override
            public int solve(int[] arr) {
                int n = arr.length;
                int max_normal = normalMaxSum(arr);
                if (max_normal < 0)
                    return max_normal;
                int arr_sum = 0;
                for (int i = 0; i < n; i++) {
                    arr_sum += arr[i];
                    arr[i] = -arr[i];
                }
                int max_circular = arr_sum + normalMaxSum(arr);
                return Math.max(max_circular, max_normal);
            }

            private int normalMaxSum(int arr[]) {
                int n = arr.length;
                int res = arr[0];
                int maxEnding = arr[0];
                for (int i = 1; i < n; i++) {
                    maxEnding = Math.max(maxEnding + arr[i], arr[i]);
                    res = Math.max(maxEnding, res);
                }
                return res;
            }
        };

        public abstract int solve(int[] arr);
    }

    public int maximumCircularSubarraySum(int[] arr) {
        return maximumCircularSubarraySum(arr, Approach.LINEAR_SOLUTION_KADANE_ALGORITHM);
    }

    ;

    public int maximumCircularSubarraySum(int[] arr, Approach approach) {
        return approach.solve(arr);
    }

    public static void main(String[] args) {
        System.out.println(new MaximumCircularSubarraySum().maximumCircularSubarraySum(new int[]{5, -2, 3, 4}));
        System.out.println(new MaximumCircularSubarraySum().maximumCircularSubarraySum(new int[]{2, 3, -4}));
        System.out.println(new MaximumCircularSubarraySum().maximumCircularSubarraySum(new int[]{8, -4, 3, -5, 4}));
        System.out.println(new MaximumCircularSubarraySum().maximumCircularSubarraySum(new int[]{-3, 4, 6, -2}));
        System.out.println(new MaximumCircularSubarraySum().maximumCircularSubarraySum(new int[]{-8, 7, 6}));
        System.out.println(new MaximumCircularSubarraySum().maximumCircularSubarraySum(new int[]{3, -4, 5, 6, -8, 7}));
        /*
        * 12
            5
            12
            10
            13
            17
        * */
    }
}
