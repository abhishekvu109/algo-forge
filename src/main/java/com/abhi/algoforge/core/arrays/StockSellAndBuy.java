package com.abhi.algoforge.core.arrays;

public class StockSellAndBuy {

    public enum Approach {
        NAIVE_SOLUTION {
            @Override
            public int solve(int[] arr) {
                int N = arr.length;
                return solve(arr, 0, N - 1, 0);
            }

            private int solve(int[] arr, int leftIndex, int rightIndex, int profit) {
                if (leftIndex > rightIndex) {
                    return profit;
                }
                int currentProfit = arr[rightIndex] > arr[leftIndex] ? arr[rightIndex] - arr[leftIndex] : 0;
                return Math.max(solve(arr, leftIndex + 1, rightIndex, profit + currentProfit),
                        solve(arr, leftIndex, rightIndex - 1, profit + currentProfit));
            }
        };

        public abstract int solve(int[] arr);
    }

    public int stockSellAndBuy(int[] arr, Approach approach) {
        return approach.solve(arr);
    }

    public int stockSellAndBuy(int[] arr) {
        return stockSellAndBuy(arr, Approach.NAIVE_SOLUTION);
    }

    public static void main(String[] args) {
        System.out.println(new StockSellAndBuy().stockSellAndBuy(new int[]{1, 5, 3, 8, 12}));
    }
}
