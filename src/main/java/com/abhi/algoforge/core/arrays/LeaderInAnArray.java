package com.abhi.algoforge.core.arrays;

import java.net.Inet4Address;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeaderInAnArray {

    public enum Approach {

        BRUTE_FORCE_SOLUTION {
            @Override
            public int solve(int[] arr) {
                int leader = arr[0], leaderCount = 0, N = arr.length;
                for (int i = 0; i < N; i++) {
                    int currentCount = 0;
                    for (int j = i; j < N; j++) {
                        if (arr[i] == arr[j]) {
                            currentCount++;
                        }
                    }
                    if (currentCount > leaderCount) {
                        leader = arr[i];
                    }
                }
                return leader;
            }
        },

        USING_HASHING {
            @Override
            public int solve(int[] arr) {
                int N = arr.length;
                Map<Integer, Integer> map = new HashMap<>();
                for (int i = 0; i < N; i++) {
                    if (map.containsKey(arr[i])) {
                        int value = map.get(arr[i]);
                        value++;
                        map.put(arr[i], value);
                    } else {
                        map.put(arr[i], 1);
                    }
                }
                int leader = Integer.MIN_VALUE, count = Integer.MIN_VALUE;
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    if (entry.getValue() > count) {
                        leader = entry.getKey();
                        count = entry.getValue();
                    }
                }
                return leader;
            }
        }, USING_SORTING {
            @Override
            public int solve(int[] arr) {
                Arrays.sort(arr);
                int leader = arr[0], count = 1, N = arr.length;
                int currentLeader = arr[0], currentCount = 1;
                for (int i = 1; i < N; i++) {
                    if (arr[i] == arr[i - 1]) {
                        currentLeader = arr[i];
                        currentCount++;
                    } else {
                        if (currentCount > count) {
                            leader = currentLeader;
                            count = currentCount;
                        }
                        currentLeader = arr[i];
                        currentCount = 1;
                    }
                }
                return leader;
            }
        };

        public abstract int solve(int[] arr);
    }

    public int leaderInAnArray(int[] arr, Approach approach) {
        return approach.solve(arr);
    }

    public int leaderInAnArray(int[] arr) {
        return leaderInAnArray(arr, Approach.USING_HASHING);
    }
}
