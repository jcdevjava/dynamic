package xyz.jcpalma.dynamic.sum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SumTest {

    static Stream<Arguments> testCasesForCanSum() {
        return Stream.of(
            Arguments.of("canSum", 7, new int[]{2, 3}, true),
            Arguments.of("canSum", 7, new int[]{5, 3, 4, 7}, true),
            Arguments.of("canSum", 7, new int[]{2, 4}, false),
            Arguments.of("canSum", 8, new int[]{2, 3, 5}, true),
            Arguments.of("canSum", 300, new int[]{7, 14}, false)
        );
    }

    static Stream<Arguments> testCasesForHowSum() {
        return Stream.of(
            Arguments.of("howSum", 7, new int[]{2, 3}, new int[]{3, 2, 2}),
            Arguments.of("howSum", 7, new int[]{5, 3, 4, 7}, new int[]{4, 3}),
            Arguments.of("howSum", 7, new int[]{2, 4}, null),
            Arguments.of("howSum", 8, new int[]{2, 3, 5}, new int[]{2, 2, 2, 2}),
            Arguments.of("howSum", 300, new int[]{7, 14}, null)
        );
    }

    static Stream<Arguments> testCasesForBestSum() {
        return Stream.of(
            Arguments.of("bestSum", 7, new int[]{5, 3, 4, 7}, new int[]{7}),
            Arguments.of("bestSum", 8, new int[]{2, 3, 5}, new int[]{5, 3}),
            Arguments.of("bestSum", 8, new int[]{1, 4, 5}, new int[]{4, 4}),
            Arguments.of("bestSum", 110, new int[]{3, 5, 25}, new int[]{25, 25, 25, 25, 5, 5})
        );
    }

    @Order(1)
    @ParameterizedTest(name = "{index}: {0}BF({1}, {2}) -> {3}")
    @MethodSource("testCasesForCanSum")
    @DisplayName("CanSum with brute force")
    void testCanSumBruteForce(String ignoredName, int targetSum, int[] numbers, boolean expected) {
        final boolean result = Sum.canSumBF(targetSum, numbers);
        assertEquals(
            expected,
            result,
            String.format("CanSumBF(%d, %s) should be %b, but was %b", targetSum, Arrays.toString(numbers), expected, result)
        );
    }

    @Order(2)
    @ParameterizedTest(name = "{index}: {0}({1}, {2}) -> {3}")
    @MethodSource("testCasesForCanSum")
    @DisplayName("CanSum with memoization")
    void testCanSum(String ignoredName, int targetSum, int[] numbers, boolean expected) {
        final boolean result = Sum.canSum(targetSum, numbers);
        assertEquals(
            expected,
            result,
            String.format("CanSum(%d, %s) should be %b, but was %b", targetSum, Arrays.toString(numbers), expected, result)
        );
    }

    @Order(3)
    @ParameterizedTest(name = "{index}: {0}({1}, {2}) -> {3}")
    @MethodSource("testCasesForHowSum")
    @DisplayName("HowSum with brute force")
    void testHowSumBruteForce(String ignoredName, int targetSum, int[] numbers, int[] expected) {
        final int[] result = Sum.howSumBF(targetSum, numbers);
        assertArrayEquals(
            expected,
            result,
            String.format("HowSumBF(%d, %s) should be %s, but was %s", targetSum, Arrays.toString(numbers), Arrays.toString(expected), Arrays.toString(result))
        );
    }

    @Order(4)
    @ParameterizedTest(name = "{index}: {0}({1}, {2}) -> {3}")
    @MethodSource("testCasesForHowSum")
    @DisplayName("HowSum with memoization")
    void testHowSum(String ignoredName, int targetSum, int[] numbers, int[] expected) {
        final int[] result = Sum.howSum(targetSum, numbers);
        assertArrayEquals(
            expected,
            result,
            String.format("HowSum(%d, %s) should be %s, but was %s", targetSum, Arrays.toString(numbers), Arrays.toString(expected), Arrays.toString(result))
        );
    }

    @Order(5)
    @ParameterizedTest(name = "{index}: {0}({1}, {2}) -> {3}")
    @MethodSource("testCasesForBestSum")
    @DisplayName("BestSum with brute force")
    void testBestSumBruteForce(String ignoredName, int targetSum, int[] numbers, int[] expected) {
        final int[] result = Sum.bestSumBF(targetSum, numbers);
        assertArrayEquals(
            expected,
            result,
            String.format("BestSumBF(%d, %s) should be %s, but was %s", targetSum, Arrays.toString(numbers), Arrays.toString(expected), Arrays.toString(result))
        );
    }

    @Order(6)
    @ParameterizedTest(name = "{index}: {0}({1}, {2}) -> {3}")
    @MethodSource("testCasesForBestSum")
    @DisplayName("BestSum with memoization")
    void testBestSum(String ignoredName, int targetSum, int[] numbers, int[] expected) {
        final int[] result = Sum.bestSum(targetSum, numbers);
        assertArrayEquals(
            expected,
            result,
            String.format("BestSum(%d, %s) should be %s, but was %s", targetSum, Arrays.toString(numbers), Arrays.toString(expected), Arrays.toString(result))
        );
    }

}