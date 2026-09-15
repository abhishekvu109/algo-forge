package com.abhi.algoforge.core.arrays;

public class CheckIfTheArrayIsSorted {

    public enum Approach {
        NAIVE_SOLUTION {
            @Override
            public boolean solve(int[] arr, int N) {
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
        },
        DIVIDE_AND_CONQUER {
            @Override
            public boolean solve(int[] arr, int N) {
                return isSortedRange(arr, 0, N - 1);
            }

            private boolean isSortedRange(int[] arr, int leftIndex, int rightIndex) {
                if (rightIndex <= leftIndex) {
                    return true;
                }
                int middleIndex = (leftIndex + rightIndex) / 2;
                return arr[leftIndex] <= arr[rightIndex] &&
                        isSortedRange(arr, leftIndex, middleIndex) &&
                        isSortedRange(arr, middleIndex + 1, rightIndex);
            }
        };

        public abstract boolean solve(int[] arr, int N);
    }

    public boolean isSorted(int[] arr, int N) {
        return isSorted(arr, N, Approach.NAIVE_SOLUTION);
    }

    public boolean isSorted(int[] arr, int N, Approach approach) {
        if (N < 2) {
            return true;
        }
        return approach.solve(arr, N);
    }

    public static void main(String[] args) {
        System.out.println(new CheckIfTheArrayIsSorted().isSorted(new int[]{1, 2, 3, 7, 4, 5}, 6, Approach.NAIVE_SOLUTION));
        System.out.println(new CheckIfTheArrayIsSorted().isSorted(new int[]{1, 2, 3, 4, 5, 6}, 6, Approach.DIVIDE_AND_CONQUER));
        System.out.println(new CheckIfTheArrayIsSorted().isSorted(new int[]{1, 1, 1, 1, 1, 1}, 6, Approach.DIVIDE_AND_CONQUER));
        System.out.println(new CheckIfTheArrayIsSorted().isSorted(new int[]{6, 5, 4, 3, 2, 1}, 6, Approach.NAIVE_SOLUTION));
    }
}
