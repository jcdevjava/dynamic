package xyz.jcpalma.dynamic.travel;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TravelTest {

    @Order(1)
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("testCases")
    @DisplayName("Grid Travel with memoization")
    void testGridTravel(String ignoredName, int m, int n, long expected) {
        long result = Travel.gridTravel(m, n);
        assertEquals(
            expected,
            result,
            String.format("GridTravel(%d, %d) should be %,d, but was %,d", m, n, expected, result)
        );
    }

    @Order(2)
    @ParameterizedTest(name = "{index}: {0}")
    @MethodSource("testCases")
    @DisplayName("Grid Travel with brute force")
    void testGridTravelBruteForce(String ignoredName, int m, int n, long expected) {
        final long result = Travel.gridTravelBF(m, n);
        assertEquals(
            expected,
            result,
            String.format("GridTravel(%d, %d) should be %,d, but was %,d", m, n, expected, result)
        );
    }


    static Stream<Arguments> testCases() {
        return Stream.of(
            Arguments.of("gridTravel(m:1, n:1) -> 1", 1, 1, 1),
            Arguments.of("gridTravel(m:2, n:3) -> 3", 2, 3, 3),
            Arguments.of("gridTravel(m:3, n:2) -> 3", 3, 2, 3),
            Arguments.of("gridTravel(m:3, n:3) -> 6", 3, 3, 6),
            Arguments.of("gridTravel(m:18, n:18) -> 2,333,606,220", 18, 18, 2333606220L)
        );
    }

}