package com.abhi.algoforge.core.arrays;

public class MaximumLengthEvenOddArray {
    public enum Approach {
        LINEAR_SOLUTION {
            @Override
            public int solve(int[] arr) {
                int N = arr.length;
                int maxLength = 1, currentMax = 1;
                for (int i = 1; i < N; i++) {
                    if (isOdd(arr[i] + arr[i - 1])) {
                        currentMax++;
                    } else {
                        currentMax = 1;
                    }
                    maxLength = Math.max(maxLength, currentMax);
                }
                return maxLength;
            }

            private boolean isOdd(int num) {
                return num % 2 == 1;
            }
        }, NAIVE_SOLUTION {
            @Override
            public int solve(int[] arr) {
                int res = 1;
                int n = arr.length;
                for (int i = 0; i < n; i++) {
                    int curr = 1;

                    for (int j = i + 1; j < n; j++) {
                        if ((arr[j] % 2 == 0 && arr[j - 1] % 2 != 0) || (arr[j] % 2 != 0 && arr[j - 1] % 2 == 0))
                            curr++;
                        else break;
                    }

                    res = Math.max(res, curr);
                }

                return res;
            }
        };

        public abstract int solve(int[] arr);
    }

    public int maximumLengthOfEvenOddArray(int[] arr) {
        return maximumLengthOfEvenOddArray(arr, Approach.NAIVE_SOLUTION);
    }

    public int maximumLengthOfEvenOddArray(int[] arr, Approach approach) {
        return approach.solve(arr);
    }

    public static void main(String[] args) {
        System.out.println(new MaximumLengthEvenOddArray().maximumLengthOfEvenOddArray(new int[]{10, 12, 14, 7, 8}));
        System.out.println(new MaximumLengthEvenOddArray().maximumLengthOfEvenOddArray(new int[]{7, 10, 13, 14}));
        System.out.println(new MaximumLengthEvenOddArray().maximumLengthOfEvenOddArray(new int[]{10, 12, 8, 4}));
        System.out.println(new MaximumLengthEvenOddArray().maximumLengthOfEvenOddArray(new int[]{5, 10, 20, 6, 3, 8}));
    }
}
