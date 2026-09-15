package com.abhi.algoforge.core.arrays;

public class LeftRotateAnArrayByD {
    public enum Approach {
        IN_PLACE_OPTIMAL {
            @Override
            public void solve(int[] arr, int D) {
                int N = arr.length;
                int[] temp = new int[D];
                for (int i = 0; i < D; i++) {
                    temp[i] = arr[i];
                }

            }
        }, USING_AUX_SPACE {
            @Override
            public void solve(int[] arr, int D) {
                int N = arr.length;
                int[] temp = new int[N];
                int index = N - D;
                for (int i = 0; i < D; i++) {
                    temp[index++] = arr[i];
                }
                index = 0;
                for (int i = D; i < N; i++) {
                    temp[index++] = arr[i];
                }
                for (int i = 0; i < N; i++) {
                    arr[i] = temp[i];
                }
            }
        };

        public abstract void solve(int[] arr, int D);
    }

    public void leftRotateByDPlaces(int[] arr, int D) {
        leftRotateByDPlaces(arr, D, Approach.IN_PLACE_OPTIMAL);
    }

    public void leftRotateByDPlaces(int[] arr, int D, Approach approach) {
        approach.solve(arr, D);
    }

}
