package com.abhi.algoforge.core.arrays;

public class RemoveDuplicateFromTheSortedArray {
    public enum Approach {
        LINEAR_APPROACH {
            @Override
            public void solve(int[] arr) {
                int duplicateNumber = arr[0];
                for (int i = 1; i < arr.length; i++) {
                    if (arr[i] == duplicateNumber) {
                        duplicateNumber = arr[i];
                        arr[i] = Integer.MIN_VALUE;
                        continue;
                    }
                    duplicateNumber = arr[i];
                }
            }
        },
        BRUTE_FORCE {
            @Override
            public void solve(int[] arr) {
                int N = arr.length;
                int[] temp = new int[N];
                int j = 0;
                temp[j] = arr[0];
                for (int i = 1; i < N; i++) {
                    if (temp[j] != arr[i]) {
                        temp[++j] = arr[i];
                    }
                }
                arr = new int[j];
                for (int i = 0; i <= j; i++) {
                    arr[i] = temp[i];
                }
            }
        };


        public abstract void solve(int[] arr);
    }

    public void removeDuplicateFromTheSortedArray(int[] arr, Approach approach) {
        approach.solve(arr);
    }

    public void removeDuplicateFromTheSortedArray(int[] arr) {
        removeDuplicateFromTheSortedArray(arr, Approach.BRUTE_FORCE);
    }
}
