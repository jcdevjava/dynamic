package xyz.jcpalma.dynamic.construct;

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
class ConstructTest {

    static Stream<Arguments> canConstructTestData() {
        return Stream.of(
            Arguments.of("canConstruct", "abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"}, true),
            Arguments.of("canConstruct", "skateboard", new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}, false),
            Arguments.of("canConstruct", "enterapotentpot", new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}, true),
            Arguments.of("canConstruct", "eeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee"}, false)
        );
    }

    static Stream<Arguments> countConstructTestData() {
        return Stream.of(
            Arguments.of("countConstruct", "purple", new String[]{"purp", "p", "ur", "le", "purpl"}, 2),
            Arguments.of("countConstruct", "abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"}, 1),
            Arguments.of("countConstruct", "skateboard", new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}, 0),
            Arguments.of("countConstruct", "enterapotentpot", new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}, 4),
            Arguments.of("countConstruct", "eeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee"}, 0)
        );
    }

    static Stream<Arguments> allConstructTestData() {
        return Stream.of(
            Arguments.of("allConstruct", "purple", new String[]{"purp", "p", "ur", "le", "purpl"}, new String[][]{{"purp", "le"}, {"p", "ur", "p", "le"}}),
            Arguments.of("allConstruct", "abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"}, new String[][]{{"abc", "def"}}),
            Arguments.of("allConstruct", "skateboard", new String[]{"bo", "rd", "ate", "t", "ska", "sk", "boar"}, new String[][]{}),
            Arguments.of("allConstruct", "eeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[]{"e", "ee", "eee", "eeee", "eeeee", "eeeeee"}, new String[][]{})
        );
    }

    @Order(1)
    @ParameterizedTest(name = "{index}: {0}BF({1}, {2}) -> {3}")
    @MethodSource("canConstructTestData")
    @DisplayName("CanConstruct with brute force")
    void testCanConstructBruteForce(String ignoredName, String target, String[] words, boolean expected) {
        final boolean result = Construct.canConstructBF(target, words);
        assertEquals(
            expected,
            result,
            String.format("CanConstructBF(%s, %s) should be %b, but was %b", target, Arrays.toString(words), expected, result)
        );
    }

    @Order(2)
    @ParameterizedTest(name = "{index}: {0}({1}, {2}) -> {3}")
    @MethodSource("canConstructTestData")
    @DisplayName("CanConstruct with memoization")
    void testCanConstructMemoization(String ignoredName, String target, String[] words, boolean expected) {
        final boolean result = Construct.canConstruct(target, words);
        assertEquals(
            expected,
            result,
            String.format("CanConstruct(%s, %s) should be %b, but was %b", target, Arrays.toString(words), expected, result)
        );
    }

    @Order(3)
    @ParameterizedTest(name = "{index}: {0}({1}, {2}) -> {3}")
    @MethodSource("countConstructTestData")
    @DisplayName("CountConstruct with brute force")
    void testCountConstructBruteForce(String ignoredName, String target, String[] words, int expected) {
        final int result = Construct.countConstructBF(target, words);
        assertEquals(
            expected,
            result,
            String.format("CountConstructBF(%s, %s) should be %d, but was %d", target, Arrays.toString(words), expected, result)
        );
    }

    @Order(4)
    @ParameterizedTest(name = "{index}: {0}({1}, {2}) -> {3}")
    @MethodSource("countConstructTestData")
    @DisplayName("CountConstruct with memoization")
    void testCountConstructMemoization(String ignoredName, String target, String[] words, int expected) {
        final int result = Construct.countConstruct(target, words);
        assertEquals(
            expected,
            result,
            String.format("CountConstruct(%s, %s) should be %d, but was %d", target, Arrays.toString(words), expected, result)
        );
    }

    @Order(5)
    @ParameterizedTest(name = "{index}: {0}({1}, {2}) -> {3}")
    @MethodSource("allConstructTestData")
    @DisplayName("AllConstruct with brute force")
    void testAllConstructBruteForce(String ignoredName, String target, String[] words, String[][] expected) {
        final String[][] result = Construct.allConstructBF(target, words);
        assertArrayEquals(
            expected,
            result,
            String.format("AllConstructBF(%s, %s) should be %s, but was %s", target, Arrays.toString(words), Arrays.deepToString(expected), Arrays.deepToString(result))
        );
    }

    @Order(6)
    @ParameterizedTest(name = "{index}: {0}({1}, {2}) -> {3}")
    @MethodSource("allConstructTestData")
    @DisplayName("AllConstruct with memoization")
    void testAllConstructMemoization(String ignoredName, String target, String[] words, String[][] expected) {
        final String[][] result = Construct.allConstruct(target, words);
        assertArrayEquals(
            expected,
            result,
            String.format("AllConstruct(%s, %s) should be %s, but was %s", target, Arrays.toString(words), Arrays.deepToString(expected), Arrays.deepToString(result))
        );
    }

}