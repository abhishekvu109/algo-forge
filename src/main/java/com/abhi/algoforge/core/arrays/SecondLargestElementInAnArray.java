package com.abhi.algoforge.core.arrays;

import java.util.Arrays;

public class SecondLargestElementInAnArray {
    public static class Approach {
        public static final int NAIVE_SOLUTION = 0;
        public static final int USING_SORTING = 1;
    }

    public int secondLargestElementInAnArray(int[] arr, int N) {
        return secondLargestElementInAnArray(arr, N, Approach.NAIVE_SOLUTION);
    }

    public int secondLargestElementInAnArray(int[] arr, int N, int approach) {
        if (N < 2) {
            return Integer.MIN_VALUE;
        }
        return switch (approach) {
            case Approach.NAIVE_SOLUTION -> naiveSolution(arr, N);
            case Approach.USING_SORTING -> usingSorting(arr, N);
            default -> naiveSolution(arr, N);
        };
    }

    private int naiveSolution(int[] arr, int N) {
        int FIRST_LARGEST = new LargestElementInAnArray().largestElementInAnArray(arr, N);
        int SECOND_LARGEST = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (arr[i] != FIRST_LARGEST && arr[i] > SECOND_LARGEST) {
                SECOND_LARGEST = arr[i];
            }
        }
        return SECOND_LARGEST;
    }

    private int usingSorting(int[] arr, int N) {
        Arrays.sort(arr);
        return arr[N - 2];
    }


    public static void main(String[] args) {
        System.out.println(new SecondLargestElementInAnArray().secondLargestElementInAnArray(new int[]{1, 2, 3, 7, 4, 5}, 6));
        System.out.println(new SecondLargestElementInAnArray().secondLargestElementInAnArray(new int[]{1, 2, 3, 7, 4, 5}, 6, Approach.USING_SORTING));
    }

}
