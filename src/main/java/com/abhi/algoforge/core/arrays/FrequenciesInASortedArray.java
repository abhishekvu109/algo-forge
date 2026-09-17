package com.abhi.algoforge.core.arrays;

import com.abhi.algoforge.core.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class FrequenciesInASortedArray {
    public enum Approach {
        LINEAR_APPROACH {
            @Override
            public List<Pair<Integer, Integer>> solve(int[] arr) {
                int N = arr.length;
                List<Pair<Integer, Integer>> result = new ArrayList<>();
                int count = 1;
                for (int i = 1; i < N; i++) {
                    if (arr[i] == arr[i - 1]) {
                        count++;
                    } else {
                        result.add(new Pair<>(arr[i - 1], count));
                        count = 1;
                    }
                }
                return result;
            }
        };

        public abstract List<Pair<Integer, Integer>> solve(int[] arr);
    }

    public List<Pair<Integer, Integer>> frequenciesInASortedArray(int[] arr, Approach approach) {
        return approach.solve(arr);
    }

    public List<Pair<Integer, Integer>> frequenciesInASortedArray(int[] arr) {
        return frequenciesInASortedArray(arr, Approach.LINEAR_APPROACH);
    }
}
