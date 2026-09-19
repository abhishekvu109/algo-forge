package com.abhi.algoforge.core.arrays;

public class MaximumSumSubArray {
    public enum Approach {
        LINEAR_SOLUTION {
            @Override
            public int solve(int[] arr) {
                int N = arr.length;
                int maxSum = arr[0],result=arr[0];
                for (int i = 1; i < N; i++) {
                    maxSum = Math.max(maxSum + arr[i], arr[i]);
                    result=Math.max(maxSum,result);
                }
                return result;
            }
        },
        NAIVE_SOLUTION {
            @Override
            public int solve(int[] arr) {
                int N = arr.length;
                int maxSum = Integer.MIN_VALUE;
                for (int i = 0; i < N; i++) {
                    int currentSum = 0;
                    for (int j = i; j < N; j++) {
                        currentSum += arr[j];
                        maxSum = Math.max(currentSum, maxSum);
                    }
                }
                return maxSum;
            }
        },
        RECURSIVE_SOLUTION {
            @Override
            public int solve(int[] arr) {
                int N = arr.length;
                return solve(arr, 0, N - 1);
            }

            private int solve(int[] arr, int left, int right) {
                if (left > right) {
                    return Integer.MIN_VALUE;
                }
                int currentSum = 0;
                for (int i = left; i <= right; i++) {
                    currentSum += arr[i];
                }
                int leftSum = solve(arr, left + 1, right);
                int rightSum = solve(arr, left, right - 1);
                return Math.max(Math.max(currentSum, leftSum), rightSum);
            }
        };

        public abstract int solve(int[] arr);
    }

    public int maximumSumSubArray(int[] arr) {
        return maximumSumSubArray(arr, Approach.LINEAR_SOLUTION);
    }

    public int maximumSumSubArray(int[] arr, Approach approach) {
        return approach.solve(arr);
    }

    public static void main(String[] args) {
        System.out.println(new MaximumSumSubArray().maximumSumSubArray(new int[]{2, 3, -8, 7, -1, 2, 3}));
        System.out.println(new MaximumSumSubArray().maximumSumSubArray(new int[]{5, 8, 3}));
        System.out.println(new MaximumSumSubArray().maximumSumSubArray(new int[]{-6, -1, -8}));
    }
}
