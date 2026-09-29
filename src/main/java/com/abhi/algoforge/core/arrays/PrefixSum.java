package com.abhi.algoforge.core.arrays;

public class PrefixSum {

    public enum Approach {
        LINEAR_SOLUTION {
            @Override
            public int solve(int[] arr, int left, int right) {
                int result = 0;
                for (int i = left; i <= right; i++) {
                    result += arr[i];
                }
                return result;
            }
        },
        OPTIMAL_SOLUTION {
            @Override
            public int solve(int[] arr, int left, int right) {
                int N = arr.length;
                int[] PREFIX_SUM = new int[N];
                PREFIX_SUM[0] = arr[0];
                for (int i = 1; i < N; i++) {
                    PREFIX_SUM[i] = PREFIX_SUM[i - 1] + arr[i];
                }
                return left == 0 ? PREFIX_SUM[right] : PREFIX_SUM[right] - PREFIX_SUM[left - 1];
            }
        };

        public abstract int solve(int[] arr, int left, int right);
    }

    public int getSum(int[] arr, int left, int right, Approach approach) {
        return approach.solve(arr, left, right);
    }

    public int getSum(int[] arr, int left, int right) {
        return getSum(arr, left, right, Approach.OPTIMAL_SOLUTION);
    }

    public static void main(String[] args) {
        System.out.println(new PrefixSum().getSum(new int[]{2, 8, 3, 9, 6, 5, 4}, 0, 2));
        System.out.println(new PrefixSum().getSum(new int[]{2, 8, 3, 9, 6, 5, 4}, 1, 3));
        System.out.println(new PrefixSum().getSum(new int[]{2, 8, 3, 9, 6, 5, 4}, 2, 6));
    }
}
