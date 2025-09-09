package com.abhi.algoforge.core.problems.arrays;

public class RotateArray {
    private static final RotateArray INSTANCE = new RotateArray();

    private RotateArray() {
    }

    public enum ImplementationType {
        BRUTE_FORCE
    }

    public static void execute(ImplementationType type, int[] arr, int d) {
        if (type == null || d < 0) {
            throw new IllegalArgumentException();
        }
        switch (type) {
            case BRUTE_FORCE -> {
                INSTANCE.bruteForce(arr, d);
            }
            default -> {
                throw new IllegalArgumentException();
            }
        }
    }

    private void bruteForce(int[] arr, int d) {
        int N = arr.length;
        if (d == N || N == 0) {
            return;
        }
        if (d > N) {
            d = d % N;
        }
        int[] temp = new int[d];
        for (int i = 0; i < d; i++) {
            temp[i] = arr[i];
        }
        int j = 0;
        for (int i = d; i < N; i++) {
            arr[j++] = arr[i];
        }
        j = (N % 2 == 0) ? d : d + 1;
        for (int i = 0; i < d; i++) {
            arr[j++] = temp[i];
        }
    }

}
