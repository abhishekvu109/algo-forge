package com.abhi.algoforge.core.arrays;

public class MaximumConsecutive1sInABinaryArray {
    public enum Approach {
        LINEAR_APPROACH {
            @Override
            public int solve(int[] arr) {
                int N = arr.length;
                int count = 0, currentCount = 0;
                for (int i = 0; i < N; i++) {
                    if (arr[i] == 1) {
                        currentCount++;
                        if (i == N - 1) {
                            count = Math.max(count, currentCount);
                        }
                    } else {
                        count = Math.max(count, currentCount);
                        currentCount = 0;
                    }
                }
                return count;
            }
        };

        public abstract int solve(int[] arr);
    }

    public int maximumConsecutive1sInABinaryArray(int[] arr, Approach approach) {
        return approach.solve(arr);
    }

    public int maximumConsecutive1sInABinaryArray(int[] arr) {
        return maximumConsecutive1sInABinaryArray(arr, Approach.LINEAR_APPROACH);
    }

    public static void main(String[] args) {
        System.out.println(new MaximumConsecutive1sInABinaryArray().maximumConsecutive1sInABinaryArray(new int[]{0, 1, 1, 0, 1, 0}));
        System.out.println(new MaximumConsecutive1sInABinaryArray().maximumConsecutive1sInABinaryArray(new int[]{1, 1, 1, 1}));
        System.out.println(new MaximumConsecutive1sInABinaryArray().maximumConsecutive1sInABinaryArray(new int[]{0, 0, 0}));
        System.out.println(new MaximumConsecutive1sInABinaryArray().maximumConsecutive1sInABinaryArray(new int[]{1, 0, 1, 1, 1, 1, 0, 1, 1}));
    }
}
