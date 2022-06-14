package xyz.jcpalma.dynamic.fib;

import java.util.Map;

public class Fibonacci {

    public static long fib(int n) {
        Map<Integer, Long> memo = new java.util.HashMap<>();
        return fib(n, memo);
    }

    private static long fib(int n, Map<Integer, Long> memo) {
        if (memo.containsKey(n)) return memo.get(n);
        if (n <= 2) return 1;

        long result = fib(n - 1, memo) + fib(n - 2, memo);
        memo.put(n, result);
        return result;
    }

    public static long fibBruteForce(int n) {
        if (n <= 2) return 1;
        return fibBruteForce(n - 1) + fibBruteForce(n - 2);
    }

}
