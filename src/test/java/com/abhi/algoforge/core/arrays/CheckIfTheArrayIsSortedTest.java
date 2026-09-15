package com.abhi.algoforge.core.arrays;

import com.abhi.algoforge.core.testsupport.ApproachTestSupport;
import com.abhi.algoforge.core.testsupport.ApproachTestSupport.Case;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Every {@link CheckIfTheArrayIsSorted.Approach} enum constant is run against
 * the same table of {@link #CASES} via {@link ApproachTestSupport}.
 */
class CheckIfTheArrayIsSortedTest {

    // NOTE: no descending-array case here - the two approaches disagree on
    // those, see divideAndConquer_doesNotRecognizeDescendingOrder below.
    private static final List<Case<Boolean>> CASES = List.of(
            new Case<>(new int[]{1, 2, 3, 4, 5, 6}, 6, true),   // strictly ascending
            new Case<>(new int[]{1, 1, 1, 1, 1, 1}, 6, true),   // all equal
            new Case<>(new int[]{1, 2, 3, 7, 4, 5}, 6, false),  // unsorted
            new Case<>(new int[]{3, 1, 2}, 3, false)            // unsorted, small
    );

    @TestFactory
    Stream<DynamicTest> everyApproachAgainstEveryCase() {
        CheckIfTheArrayIsSorted solver = new CheckIfTheArrayIsSorted();
        return ApproachTestSupport.forEachApproach(
                CheckIfTheArrayIsSorted.Approach.values(), CASES,
                (approach, arr, n) -> solver.isSorted(arr, n, approach));
    }

    @Test
    void fewerThanTwoElementsIsAlwaysSorted() {
        CheckIfTheArrayIsSorted solver = new CheckIfTheArrayIsSorted();
        assertTrue(solver.isSorted(new int[]{}, 0));
        assertTrue(solver.isSorted(new int[]{5}, 1));
    }

    @Test
    void defaultOverloadMatchesNaiveSolution() {
        CheckIfTheArrayIsSorted solver = new CheckIfTheArrayIsSorted();
        int[] arr = {1, 2, 3, 7, 4, 5};

        boolean viaDefault = solver.isSorted(arr, arr.length);
        boolean viaExplicitNaive = solver.isSorted(arr, arr.length, CheckIfTheArrayIsSorted.Approach.NAIVE_SOLUTION);

        assertEquals(viaExplicitNaive, viaDefault);
    }

    // Known bug: DIVIDE_AND_CONQUER only compares each range's two endpoints
    // (arr[left] <= arr[right]) - it never checks the boundary between the
    // left and right halves (arr[middle] vs arr[middle + 1]). That lets a
    // "sorted-looking-at-the-edges" but internally unsorted array slip
    // through as a false positive. Documented here rather than "fixed".
    @Test
    void divideAndConquer_missesUnsortedDipAcrossTheMidpoint() {
        // Overall endpoints (1 <= 4) look fine, and each half's endpoints look
        // fine too (1<=3, 2<=4) - but arr[1]=3 > arr[2]=2, so it isn't sorted.
        int[] arr = {1, 3, 2, 4};
        boolean actual = new CheckIfTheArrayIsSorted()
                .isSorted(arr, arr.length, CheckIfTheArrayIsSorted.Approach.DIVIDE_AND_CONQUER);
        assertTrue(actual, "documents the current (incorrect) behaviour - NAIVE_SOLUTION correctly returns false for this array");
    }

    // Known bug: DIVIDE_AND_CONQUER's `arr[left] <= arr[right]` check only
    // recognizes ascending order. NAIVE_SOLUTION treats a descending array as
    // "sorted" too (checkIncreasing || checkDecreasing), so the two disagree
    // on every strictly-descending array. Documented here rather than "fixed".
    @Test
    void divideAndConquer_doesNotRecognizeDescendingOrder() {
        int[] arr = {6, 5, 4, 3, 2, 1};

        boolean viaNaive = new CheckIfTheArrayIsSorted()
                .isSorted(arr, arr.length, CheckIfTheArrayIsSorted.Approach.NAIVE_SOLUTION);
        boolean viaDivideAndConquer = new CheckIfTheArrayIsSorted()
                .isSorted(arr, arr.length, CheckIfTheArrayIsSorted.Approach.DIVIDE_AND_CONQUER);

        assertTrue(viaNaive, "NAIVE_SOLUTION correctly treats descending as sorted");
        assertTrue(!viaDivideAndConquer, "documents the current (incorrect) behaviour of DIVIDE_AND_CONQUER");
    }
}
