package com.abhi.algoforge.core.problems.arrays;

public class SortedAndRotated {
    private static final SortedAndRotated INSTANCE = new SortedAndRotated();

    private SortedAndRotated() {
    }

    public enum ImplementationType {
        BRUTE_FORCE
    }

    public static boolean sortedAndRotated(int[] arr, ImplementationType type) {
        if (type == null) {
            throw new IllegalArgumentException();
        }
        if (type == ImplementationType.BRUTE_FORCE) {
            return INSTANCE.isSortedRotated(arr);
        }
        throw new IllegalArgumentException();
    }

    private boolean isSortedRotated(int[] arr) {
        int incPivot=0,descPivot=0;
        int N=arr.length;
        if(N==0||N==1){
            return false;
        }

        for(int i=1;i<N;i++){
            if(arr[i]<arr[i-1]){
                incPivot++;
            }
            if(arr[i]>arr[i-1]){
                descPivot++;
            }
        }
        return incPivot==1 || descPivot==1;
    }
}
