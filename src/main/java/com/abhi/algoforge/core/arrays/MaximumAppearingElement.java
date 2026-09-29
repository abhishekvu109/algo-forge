package com.abhi.algoforge.core.arrays;

import com.abhi.algoforge.core.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MaximumAppearingElement {
    public enum Approach {

        OPTIMAL_SOLUTION {
            @Override
            public int solve(int[] left, int[] right) {
                int[] frequency = new int[101]; // the question is that the maximum upper range can be 100.
                int N = left.length;
                for (int i = 0; i < N; i++) {
                    frequency[left[i]]++;
                    frequency[right[i] + 1]--;
                }
                int result = 0;
                for (int i = 1; i < frequency.length; i++) {
                    frequency[i] = frequency[i - 1] + frequency[i];
                    if (frequency[i] > frequency[result]) {
                        result = i;
                    }
                }
                return result;
            }
        },
        LINEAR_APPROACH {
            @Override
            public int solve(int[] left, int[] right) {
                int N = left.length;
                List<Integer> aux = new LinkedList<>();
                for (int i = 0; i < N; i++) {
                    for (int j = left[i]; j <= right[i]; j++) {
                        aux.add(j);
                    }
                }
                Map<Integer, Integer> countMap = new HashMap<>();
                aux.forEach(item -> {
                    countMap.merge(item, 1, Integer::sum);
                });
                Pair<Integer, Integer> result = new Pair<>(null, null);
                for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                    if (result.key() == null) {
                        result = new Pair<>(entry.getKey(), entry.getValue());
                    } else {
                        if (entry.getValue() > result.value()) {
                            result = new Pair<>(entry.getKey(), entry.getValue());
                        }
                    }
                }
                return result.key();
            }
        };

        public abstract int solve(int[] left, int[] right);
    }

    public int maximumAppearingElement(int[] left, int[] right, Approach approach) {
        return approach.solve(left, right);
    }

    public int maximumAppearingElement(int[] left, int[] right) {
        return maximumAppearingElement(left, right, Approach.OPTIMAL_SOLUTION);
    }

    public static void main(String[] args) {
        System.out.println(new MaximumAppearingElement().maximumAppearingElement(new int[]{1, 2, 5, 15}, new int[]{5, 8, 7, 18}));
        System.out.println(new MaximumAppearingElement().maximumAppearingElement(new int[]{1, 2}, new int[]{5, 4}));
    }
}
