package com.abhi.algoforge.core.arrays;

import com.abhi.algoforge.core.util.Pair;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MinimumNumberOfFlipsRequired {
    public enum Approach {
        LINEAR_SOLUTION {
            @Override
            public List<Pair<Integer, Integer>> solve(int[] arr) {
                int N = arr.length;
                int countOfZeroGroup = arr[0] == 0 ? 1 : 0, countOfOnesGroup = arr[0] == 1 ? 1 : 0;
                for (int i = 1; i < N; i++) {
                    if (arr[i] == 0) {
                        if (arr[i - 1] != 0) {
                            countOfZeroGroup++;
                        }
                    } else {
                        if (arr[i - 1] != 1) {
                            countOfOnesGroup++;
                        }
                    }
                }
                List<Integer> aux = new LinkedList<>();
                if (countOfZeroGroup <= countOfOnesGroup) {
                    for (int i = 0; i < N; i++) {
                        if (arr[i] == 0) {
                            aux.addLast(i);
                        }
                    }
                } else {
                    for (int i = 0; i < N; i++) {
                        if (arr[i] == 1) {
                            aux.addLast(i);
                        }
                    }

                }
                if (aux.isEmpty()) {
                    return Collections.emptyList();
                }
                List<Pair<Integer, Integer>> result = new LinkedList<>();
                int startIndex = aux.getFirst();
                for (int i = 1; i < aux.size(); i++) {
                    if (aux.get(i - 1) + 1 != aux.get(i)) {
                        result.addLast(new Pair<>(startIndex, aux.get(i - 1)));
                        startIndex = aux.get(i);
                    }
                }
                result.addLast(new Pair<>(startIndex, aux.getLast()));
                return result;
            }
        };

        public abstract List<Pair<Integer, Integer>> solve(int[] arr);
    }

    public List<Pair<Integer, Integer>> minimumNumberOfFlipsRequired(int[] arr, Approach approach) {
        return approach.solve(arr);
    }

    public List<Pair<Integer, Integer>> minimumNumberOfFlipsRequired(int[] arr) {
        return minimumNumberOfFlipsRequired(arr, Approach.LINEAR_SOLUTION);
    }

    public static void main(String[] args) {
        System.out.println(new MinimumNumberOfFlipsRequired().minimumNumberOfFlipsRequired(new int[]{1, 1, 0, 0, 0, 1}));
        System.out.println(new MinimumNumberOfFlipsRequired().minimumNumberOfFlipsRequired(new int[]{1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1}));
        System.out.println(new MinimumNumberOfFlipsRequired().minimumNumberOfFlipsRequired(new int[]{1, 1, 1}));
        System.out.println(new MinimumNumberOfFlipsRequired().minimumNumberOfFlipsRequired(new int[]{0, 1}));
    }
}
