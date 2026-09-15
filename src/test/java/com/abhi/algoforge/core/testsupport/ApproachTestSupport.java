package com.abhi.algoforge.core.testsupport;

import org.junit.jupiter.api.DynamicTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

/**
 * Generates one JUnit 5 {@link DynamicTest} per (approach x case) combination,
 * so a solution's test class only has to declare its cases once and every
 * approach - an {@code enum}'s {@code values()}, or an {@code Integer[]} of
 * legacy Approach constants - gets exercised against all of them. Add a new
 * approach and every existing case is tested against it automatically; add a
 * new case and every existing approach is tested against it automatically.
 * <p>
 * The input array is cloned before each invocation, so an approach that
 * sorts or otherwise mutates the array in place can't leak state into
 * another approach's run over the same case.
 */
public final class ApproachTestSupport {

    private ApproachTestSupport() {
    }

    /** One test case: an input array, its logical length N, and the expected result. */
    public record Case<T>(int[] arr, int n, T expected) {
        @Override
        public String toString() {
            String expectedText = expected instanceof int[] expectedArr
                    ? Arrays.toString(expectedArr)
                    : String.valueOf(expected);
            return "arr=" + Arrays.toString(arr) + ", n=" + n + " -> " + expectedText;
        }
    }

    /** Calls a solution that returns a result: {@code (approach, clonedArray, n) -> result}. */
    @FunctionalInterface
    public interface ApproachInvocation<A, T> {
        T apply(A approach, int[] arr, int n);
    }

    /** Calls a solution that mutates the array in place instead of returning a result. */
    @FunctionalInterface
    public interface ApproachMutation<A> {
        void accept(A approach, int[] arr, int n);
    }

    /**
     * For solutions that return a value (e.g. {@code int}, {@code boolean}).
     *
     * @param approaches every approach to exercise, e.g. {@code MyEnum.values()}
     *                    or {@code new Integer[]{Approach.NAIVE_SOLUTION, ...}}
     * @param cases       the shared table of cases, run against every approach
     * @param invoke      invokes the solution under test
     */
    public static <A, T> Stream<DynamicTest> forEachApproach(
            A[] approaches, List<Case<T>> cases, ApproachInvocation<A, T> invoke) {
        return Stream.of(approaches)
                .flatMap(approach -> cases.stream()
                        .map(c -> dynamicTest(
                                approach + " | " + c,
                                () -> assertEquals(c.expected(), invoke.apply(approach, c.arr().clone(), c.n())))));
    }

    /**
     * For solutions that mutate the input array in place instead of returning a
     * value (e.g. reversing/rotating an array). {@code Case.expected()} is the
     * array's expected final contents.
     *
     * @param approaches every approach to exercise
     * @param cases       the shared table of cases, run against every approach
     * @param mutate      invokes the solution under test
     */
    public static <A> Stream<DynamicTest> forEachApproachMutatingArray(
            A[] approaches, List<Case<int[]>> cases, ApproachMutation<A> mutate) {
        return Stream.of(approaches)
                .flatMap(approach -> cases.stream()
                        .map(c -> dynamicTest(approach + " | " + c, () -> {
                            int[] actual = c.arr().clone();
                            mutate.accept(approach, actual, c.n());
                            assertArrayEquals(c.expected(), actual);
                        })));
    }
}
