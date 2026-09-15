package com.abhi.algoforge.core.arrays;

import java.util.Deque;
import java.util.LinkedList;

public class MoveZerosToTheEnd {
    public enum Approach {
        LINEAR_APPROACH {
            @Override
            public void solve(int[] arr) {
                int N = arr.length;
                int[] temp = new int[N];
                int j = 0;
                for (int i = 0; i < N; i++) {
                    if (arr[i] == 0) {
                        temp[j] = 0;
                        j++;
                    }
                }
                for (int i = 0; i < N; i++) {
                    if (arr[i] != 0) {
                        temp[j] = arr[i];
                        j++;
                    }
                }
                for (int i = 0; i < N; i++) {
                    arr[i] = temp[i];
                }
            }
        },
        TWO_POINTER_APPROACH {
            @Override
            public void solve(int[] arr) {
                int N = arr.length;
                int left = 0, right = N - 1;
                while (left < right) {
                    if (arr[left] == 0) {
                        left++;
                        continue;
                    }
                    if (arr[right] != 0) {
                        right--;
                        continue;
                    }
                    if (arr[left] != 0) {
                        while (left < right && arr[right] != 0) {
                            right--;
                        }
                        int temp = arr[right];
                        arr[right] = arr[left];
                        arr[left] = temp;
                    }
                }
            }
        }, USING_DEQUE {
            @Override
            public void solve(int[] arr) {
                int N = arr.length;
                Deque<Integer> aux = new LinkedList<>();
                for (int i = 0; i < N; i++) {
                    if (arr[i] == 0) {
                        aux.addFirst(arr[i]);
                    } else {
                        aux.addLast(arr[i]);
                    }
                }
                for (int i = 0; i < N; i++) {
                    arr[i] = aux.removeFirst();
                }

            }
        };

        public abstract void solve(int[] arr);
    }

    public void moveZerosToTheEnd(int[] arr, Approach approach) {
        approach.solve(arr);
    }

    public void moveZerosToTheEnd(int[] arr) {
        moveZerosToTheEnd(arr, Approach.LINEAR_APPROACH);
    }
}
