package com.abhi.algoforge.core.arrays;

public class EquilibriumPoint {
    public enum Approach {
        LINEAR_SOLUTION {
            @Override
            public boolean solve(int[] arr) {
                int N = arr.length;
                int[] PREFIX_SUM = new int[N];
                PREFIX_SUM[0] = arr[0];
                for (int i = 1; i < N; i++) {
                    PREFIX_SUM[i] = arr[i] + PREFIX_SUM[i - 1];
                }
                for (int i = 0; i < N; i++) {
                    int leftHalf = i == 0 ? 0 : PREFIX_SUM[i - 1];
                    int rightHalf = N - 1 == i ? 0 : PREFIX_SUM[N - 1] - PREFIX_SUM[i];
                    if (leftHalf == rightHalf) {
                        return true;
                    }
                }
                return false;
            }
        },
        NAIVES_SOLUTION {
            @Override
            public boolean solve(int[] arr) {
                int N = arr.length;
                for (int i = 0; i < N; i++) {
                    int leftHalf = 0;
                    int rightHalf = 0;
                    if (i > 0) {
                        for (int j = 0; j < i; j++) {
                            leftHalf += arr[j];
                        }
                    }
                    if (i < N - 1) {
                        for (int j = i + 1; j < N; j++) {
                            rightHalf += arr[j];
                        }
                    }
                    if (rightHalf == leftHalf) {
                        return true;
                    }

                }
                return false;
            }
        };

        public abstract boolean solve(int[] arr);
    }

    public boolean isEquilibriumPoint(int[] arr, Approach approach) {
        return approach.solve(arr);
    }

    public boolean isEquilibriumPoint(int[] arr) {
        return isEquilibriumPoint(arr, Approach.LINEAR_SOLUTION);
    }

    public static void main(String[] args) {
        System.out.println(new EquilibriumPoint().isEquilibriumPoint(new int[]{3, 4, 8, -9, 20, 6}));
        System.out.println(new EquilibriumPoint().isEquilibriumPoint(new int[]{4, 2, -2}));
        System.out.println(new EquilibriumPoint().isEquilibriumPoint(new int[]{4, 2, 2}));
    }
}
