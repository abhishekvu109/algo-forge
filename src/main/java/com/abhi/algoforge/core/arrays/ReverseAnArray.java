package com.abhi.algoforge.core.arrays;

public class ReverseAnArray {

    public enum Approach {
        TWO_POINTER {
            @Override
            public void solve(int[] arr, int N) {
                int left = 0, right = N - 1;
                while (left < right) {
                    int temp = arr[left];
                    arr[left] = arr[right];
                    arr[right] = temp;
                    left++;
                    right--;
                }
            }
        },
        NAIVE_SOLUTION {
            @Override
            public void solve(int[] arr, int N) {
                int[] temp = new int[N];
                int j = 0;
                for (int i = N; i > 0; i--) {
                    temp[j++] = arr[i - 1];
                }
                for (int i = 0; i < N; i++) {
                    arr[i] = temp[i];
                }
            }
        };

        public abstract void solve(int[] arr, int N);
    }

    public void reverseAnArray(int[] arr, int N) {
        reverseAnArray(arr, N, Approach.TWO_POINTER);
    }

    public void reverseAnArray(int[] arr, int N, Approach approach) {
        approach.solve(arr, N);
    }

}
