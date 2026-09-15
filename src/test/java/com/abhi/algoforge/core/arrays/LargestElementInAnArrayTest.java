package com.abhi.algoforge.core.arrays;

import com.abhi.algoforge.core.testsupport.ApproachTestSupport;
import com.abhi.algoforge.core.testsupport.ApproachTestSupport.Case;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Every {@link LargestElementInAnArray.Approach} enum constant is run against
 * the same table of {@link #CASES} via {@link ApproachTestSupport} - add a new
 * approach here and a new case there and both get cross-tested automatically.
 */
class LargestElementInAnArrayTest {

    private static final List<Case<Integer>> CASES = List.of(
            new Case<>(new int[]{1, 2, 3, 7, 4, 5}, 6, 7),
            new Case<>(new int[]{5}, 1, 5),
            new Case<>(new int[]{-3, -1, -7, -2}, 4, -1),
            new Case<>(new int[]{4, 4, 4, 4}, 4, 4),
            new Case<>(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE}, 3, Integer.MAX_VALUE)
    );

    @TestFactory
    Stream<DynamicTest> everyApproachAgainstEveryCase() {
        LargestElementInAnArray solver = new LargestElementInAnArray();
        return ApproachTestSupport.forEachApproach(
                LargestElementInAnArray.Approach.values(), CASES,
                (approach, arr, n) -> solver.largestElementInAnArray(arr, n, approach));
    }

    @Test
    void defaultOverloadMatchesNaiveSolution() {
        LargestElementInAnArray solver = new LargestElementInAnArray();
        int[] arr = {1, 2, 3, 7, 4, 5};

        int viaDefault = solver.largestElementInAnArray(arr.clone(), arr.length);
        int viaExplicitNaive = solver.largestElementInAnArray(
                arr.clone(), arr.length, LargestElementInAnArray.Approach.NAIVE_SOLUTION);

        assertEquals(viaExplicitNaive, viaDefault);
    }
}
