package com.abhi.algoforge.core.problems.arrays;

import com.abhi.algoforge.core.structures.linear.stack.LinkedStack;
import com.abhi.algoforge.core.structures.linear.stack.Stack;

public class ReverseInGroups {
    private static final ReverseInGroups INSTANCE = new ReverseInGroups();

    private ReverseInGroups() {
    }

    public enum ImplementationType {
        BRUTE_FORCE
    }

    public static void execute(int[] arr, int k, ImplementationType type) {
        if (type == null) {
            throw new IllegalArgumentException();
        }
        if (type == ImplementationType.BRUTE_FORCE) {
            INSTANCE.stackApproach(arr, k);
        }
    }

    private void stackApproach(int[] arr, int k) {
        Stack<Integer> stack = new LinkedStack<>();
        int i = 0, j = 0, N = arr.length;
        while (i < N) {
            while (stack.size() < k && i < N) {
                stack.push(arr[i]);
                i++;
            }
            while (!stack.isEmpty() && j < N) {
                arr[j] = stack.pop();
                j++;
            }
        }
    }

}
