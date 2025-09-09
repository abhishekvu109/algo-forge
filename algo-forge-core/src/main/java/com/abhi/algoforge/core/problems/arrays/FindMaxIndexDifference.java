package com.abhi.algoforge.core.problems.arrays;

import java.util.Arrays;

/**
 * Returns the maximum difference j - i such that arr[i] < arr[j] and i < j.
 *
 * <p>Examples:</p>
 * <pre>
 * Input: arr[] = [1, 10]
 * Output: 1
 * Explanation: arr[0] < arr[1], so (j - i) is 1 - 0 = 1.
 *
 * Input: arr[] = [5, 4, 3]
 * Output: 0
 * Explanation: There is no pair that satisfies the given condition.
 *
 * Input: arr[] = [34, 8, 10, 3, 2, 80, 30, 33, 1]
 * Output: 6
 * Explanation: arr[1] < arr[7], satisfying the required condition (arr[i] < arr[j]),
 * thus giving the maximum difference of j - i which is 6 (7 - 1).
 * </pre>
 * <p>
 * param arr an array of positive integers
 *
 * @return the maximum value of j - i such that arr[i] < arr[j] and i < j
 * @throws IllegalArgumentException if the array is null or its size is out of the allowed range
 * @implNote Constraints:
 * <ul>
 *   <li>1 ≤ arr.size ≤ 10^5</li>
 *   <li>0 ≤ arr[i] ≤ 10^9</li>
 * </ul>
 */

public class FindMaxIndexDifference {
    private final static FindMaxIndexDifference INSTANCE = new FindMaxIndexDifference();

    private FindMaxIndexDifference() {
    }

    public enum ImplementationType {
        BRUTE_FORCE,
        PRE_COMPUTED_MIN,
        SORTING_APPROACH
    }

    public static int maxIndexDifference(int[] arr, ImplementationType type) {
        if (arr.length == 0) {
            return 0;
        }
        if (type == null) {
            throw new IllegalArgumentException("Cannot be null.");
        }
        switch (type) {
            case BRUTE_FORCE -> {
                return INSTANCE.bruteForce(arr);
            }
            case PRE_COMPUTED_MIN -> {
                return INSTANCE.preComputedMin(arr);
            }
            case SORTING_APPROACH -> {
                return INSTANCE.sortingApproach(arr);
            }
            default -> {
                throw new IllegalArgumentException("Illegal implementation type.");
            }
        }
    }

    private int preComputedMin(int[] arr) {
        int size = arr.length;
        int[] lMin = new int[size];
        int[] rMax = new int[size];
        lMin[0] = arr[0];
        for (int i = 1; i < size; i++) {
            lMin[i] = Math.min(arr[i], lMin[i - 1]);
        }
        rMax[size - 1] = arr[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            rMax[i] = Math.max(arr[i], rMax[i + 1]);
        }
        int i = 0, j = 0, maxIndexDiff = 0;
        while (i < size && j < size) {
            if (rMax[i] >= lMin[j]) {
                maxIndexDiff = Math.max(i - j, maxIndexDiff);
                i++;
            } else {
                j++;
            }
        }
        return maxIndexDiff;
    }

    private int bruteForce(int[] arr) {
        int maxDiff = 0;
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (arr[j] > arr[i]) {
                    maxDiff = Math.max(maxDiff, j - i);
                }
            }
        }
        return maxDiff;
    }

    private int sortingApproach(int[] arr) {
        int[][] v = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            v[i][0] = arr[i];
            v[i][1] = i;
        }
        Arrays.sort(v, (a, b) -> Integer.compare(a[0], b[0]));

        int i = v[0][1];
        int ans = 0;
        for (int j = 1; j < v.length; j++) {
            ans = Math.max(ans, v[j][1] - i);
            i = Math.min(i, v[j][1]);
        }

        return ans;
    }


}
