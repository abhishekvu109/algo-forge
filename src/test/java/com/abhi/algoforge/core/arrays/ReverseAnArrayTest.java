package com.abhi.algoforge.core.arrays;

import com.abhi.algoforge.core.testsupport.ApproachTestSupport;
import com.abhi.algoforge.core.testsupport.ApproachTestSupport.Case;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Stream;

/**
 * Every {@link ReverseAnArray.Approach} enum constant is run against the same
 * table of {@link #CASES} via {@link ApproachTestSupport}. Only one approach
 * (TWO_POINTER) exists today - add a new one (e.g. extra-array, recursive)
 * and it's cross-tested against every case here automatically.
 */
class ReverseAnArrayTest {

    private static final List<Case<int[]>> CASES = List.of(
            new Case<>(new int[]{1, 2, 3, 7, 4, 5}, 6, new int[]{5, 4, 7, 3, 2, 1}),
            new Case<>(new int[]{1, 2}, 2, new int[]{2, 1}),
            new Case<>(new int[]{5}, 1, new int[]{5}),
            new Case<>(new int[]{}, 0, new int[]{}),
            new Case<>(new int[]{4, 4, 4}, 3, new int[]{4, 4, 4})
    );

    @TestFactory
    Stream<DynamicTest> everyApproachAgainstEveryCase() {
        ReverseAnArray solver = new ReverseAnArray();
        return ApproachTestSupport.forEachApproachMutatingArray(
                ReverseAnArray.Approach.values(), CASES,
                (approach, arr, n) -> solver.reverseAnArray(arr, n, approach));
    }
}
