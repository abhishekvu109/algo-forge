package com.abhi.algoforge.core.arrays;

import com.abhi.algoforge.core.testsupport.ApproachTestSupport;
import com.abhi.algoforge.core.testsupport.ApproachTestSupport.Case;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Every {@link SecondLargestElementInAnArray.Approach} enum constant is run
 * against the same table of {@link #CASES} via {@link ApproachTestSupport}.
 */
class SecondLargestElementInAnArrayTest {

    // NOTE: these shared cases all use arrays where the maximum is unique.
    // When the maximum repeats, the two approaches disagree - see
    // duplicateMaximum_naiveSkipsAllCopiesOfIt / duplicateMaximum_sortingReturnsAnotherCopyOfTheMax below.
    private static final List<Case<Integer>> CASES = List.of(
            new Case<>(new int[]{1, 2, 3, 7, 4, 5}, 6, 5),
            new Case<>(new int[]{-3, -1, -7, -2}, 4, -2),
            new Case<>(new int[]{4, 4, 2, 7, 4}, 5, 4),
            new Case<>(new int[]{2, 1}, 2, 1)
    );

    @TestFactory
    Stream<DynamicTest> everyApproachAgainstEveryCase() {
        SecondLargestElementInAnArray solver = new SecondLargestElementInAnArray();
        return ApproachTestSupport.forEachApproach(
                SecondLargestElementInAnArray.Approach.values(), CASES,
                (approach, arr, n) -> solver.secondLargestElementInAnArray(arr, n, approach));
    }

    @Test
    void defaultOverloadMatchesNaiveSolution() {
        SecondLargestElementInAnArray solver = new SecondLargestElementInAnArray();
        int[] arr = {1, 2, 3, 7, 4, 5};

        int viaDefault = solver.secondLargestElementInAnArray(arr.clone(), arr.length);
        int viaExplicitNaive = solver.secondLargestElementInAnArray(
                arr.clone(), arr.length, SecondLargestElementInAnArray.Approach.NAIVE_SOLUTION);

        assertEquals(viaExplicitNaive, viaDefault);
    }

    // --- Known divergence: the two approaches define "second largest"
    // differently when the maximum value repeats. NAIVE_SOLUTION skips every
    // occurrence of the max and returns the largest *distinct* value below it;
    // USING_SORTING just returns arr[N-2] after sorting, which is another copy
    // of the max itself. Documented here rather than "fixed" - pick one
    // definition and reconcile the two approaches yourself.

    @Test
    void duplicateMaximum_naiveSkipsAllCopiesOfIt() {
        int[] arr = {10, 10, 9};
        int actual = new SecondLargestElementInAnArray().secondLargestElementInAnArray(
                arr, arr.length, SecondLargestElementInAnArray.Approach.NAIVE_SOLUTION);
        assertEquals(9, actual);
    }

    @Test
    void duplicateMaximum_sortingReturnsAnotherCopyOfTheMax() {
        int[] arr = {10, 10, 9};
        int actual = new SecondLargestElementInAnArray().secondLargestElementInAnArray(
                arr, arr.length, SecondLargestElementInAnArray.Approach.USING_SORTING);
        assertEquals(10, actual);
    }

    // --- Regression from the enum refactor: the old int-constant dispatcher
    // had `if (N < 2) return Integer.MIN_VALUE;` before the switch, guarding
    // both approaches. The current enum dispatcher (`approach.solve(arr, N)`)
    // has no such guard, so USING_SORTING now throws for N < 2 instead of
    // returning a sentinel. Documented here rather than "fixed" - re-add a
    // guard in the dispatcher yourself if you want both approaches protected.

    @Test
    void fewerThanTwoElements_naiveReturnsMinValue() {
        int[] arr = {42};
        int actual = new SecondLargestElementInAnArray().secondLargestElementInAnArray(
                arr, arr.length, SecondLargestElementInAnArray.Approach.NAIVE_SOLUTION);
        assertEquals(Integer.MIN_VALUE, actual);
    }

    @Test
    void fewerThanTwoElements_sortingThrowsInsteadOfReturningASentinel() {
        int[] arr = {42};
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                new SecondLargestElementInAnArray().secondLargestElementInAnArray(
                        arr, arr.length, SecondLargestElementInAnArray.Approach.USING_SORTING));
    }
}
