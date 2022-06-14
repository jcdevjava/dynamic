package xyz.jcpalma.dynamic.fib;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Timeout(10)
class FibonacciTest {

    @Order(1)
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("fibonacciTestCases")
    @DisplayName("Fibonacci with memoization")
    void testFibonacci(String ignoredName, int n, long expected) {
        long result = Fibonacci.fib(n);
        assertEquals(
            expected,
            result,
            String.format("Fibonacci(%d) should be %,d, but was %,d", n, expected, result)
        );
    }

    @Order(2)
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("fibonacciTestCases")
    @DisplayName("Fibonacci with brute force")
    void testFibonacciBruteForce(String ignoredName, int n, long expected) {
        final long result = Fibonacci.fibBruteForce(n);
        assertEquals(
            expected,
            result,
            String.format("Fibonacci(%d) should be %,d, but was %,d", n, expected, result)
        );
    }

    public static Stream<Arguments> fibonacciTestCases() {

        return Stream.of(
            Arguments.of("Fibonacci(1)=1", 1, 1),
            Arguments.of("Fibonacci(2)=1", 2, 1),
            Arguments.of("Fibonacci(7)=13", 7, 13),
            Arguments.of("Fibonacci(30)=??", 30, 832040),
            Arguments.of("Fibonacci(60)=1,548,008,755,920", 60, 1548008755920L)
        );
    }

}