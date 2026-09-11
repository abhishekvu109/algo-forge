package com.abhi.algoforge.core.arrays;

public class CheckIfTheArrayIsSorted {
    public static class Approach {
        public static final int NAIVE_SOLUTION = 0;
        public static final int DIVIDE_AND_CONQUER = 1;
    }

    public boolean isSorted(int[] arr, int N) {
        return isSorted(arr, N, Approach.NAIVE_SOLUTION);
    }

    public boolean isSorted(int[] arr, int N, int approach) {
        if (N < 2) {
            return true;
        }
        return switch (approach) {
            case Approach.NAIVE_SOLUTION -> naiveSolution(arr, N);
            case Approach.DIVIDE_AND_CONQUER -> divideAndConquer(arr, 0, N - 1);
            default -> naiveSolution(arr, N);
        };
    }

    private boolean naiveSolution(int[] arr, int N) {
        boolean checkIncreasing = true, checkDecreasing = true;

        for (int i = 1; i < N; i++) {
            if (arr[i] < arr[i - 1]) {
                checkIncreasing = false;
                break;
            }
        }
        for (int i = 1; i < N; i++) {
            if (arr[i] > arr[i - 1]) {
                checkDecreasing = false;
                break;
            }
        }
        return checkIncreasing || checkDecreasing;
    }

    private boolean divideAndConquer(int[] arr, int leftIndex, int rightIndex) {
        if (rightIndex <= leftIndex) {
            return true;
        }
        int middleIndex = (leftIndex + rightIndex) / 2;
        return arr[leftIndex] <= arr[rightIndex] &&
                divideAndConquer(arr, leftIndex, middleIndex) &&
                divideAndConquer(arr, middleIndex + 1, rightIndex);

    }

    public static void main(String[] args) {
        System.out.println(new CheckIfTheArrayIsSorted().isSorted(new int[]{1, 2, 3, 7, 4, 5}, 6,Approach.NAIVE_SOLUTION));
        System.out.println(new CheckIfTheArrayIsSorted().isSorted(new int[]{1, 2, 3, 4, 5, 6}, 6,Approach.DIVIDE_AND_CONQUER));
        System.out.println(new CheckIfTheArrayIsSorted().isSorted(new int[]{1, 1, 1, 1, 1, 1}, 6,Approach.DIVIDE_AND_CONQUER));
        System.out.println(new CheckIfTheArrayIsSorted().isSorted(new int[]{6, 5, 4, 3, 2, 1}, 6,Approach.NAIVE_SOLUTION));
    }
}
