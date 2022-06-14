package xyz.jcpalma.dynamic.sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Sum {

    public static boolean canSumBF(int targetSum, int[] numbers) {
        if (targetSum <= 0) return targetSum == 0;

        for (int n : numbers) {
            if (canSumBF(targetSum - n, numbers)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canSum(int targetSum, int[] numbers) {
        final Map<Integer, Boolean> memo = new HashMap<>();
        return canSum(targetSum, numbers, memo);
    }

    private static boolean canSum(int targetSum, int[] numbers, Map<Integer, Boolean> memo) {
        if (memo.containsKey(targetSum)) return memo.get(targetSum);
        if (targetSum <= 0) return targetSum == 0;

        for (int n : numbers) {
            if (canSum(targetSum - n, numbers, memo)) {
                memo.put(targetSum, true);
                return true;
            }
        }
        memo.put(targetSum, false);
        return false;
    }

    public static int[] howSumBF(int targetSum, int[] numbers) {
        if (targetSum <= 0) return targetSum == 0 ? new int[]{} : null;

        for (int n : numbers) {
            final int[] result = howSumBF(targetSum - n, numbers);
            if (result != null) return append(result, n);
        }
        return null;
    }

    public static int[] howSum(int targetSum, int[] numbers) {
        final Map<Integer, int[]> memo = new HashMap<>();
        return howSum(targetSum, numbers, memo);
    }

    private static int[] howSum(int targetSum, int[] numbers, Map<Integer, int[]> memo) {
        if (memo.containsKey(targetSum)) return memo.get(targetSum);
        if (targetSum <= 0) return targetSum == 0 ? new int[]{} : null;

        for (int n : numbers) {
            final int[] result = howSum(targetSum - n, numbers, memo);
            if (result != null) {
                final int[] way = append(result, n);
                memo.put(targetSum, way);
                return way;
            }

        }
        memo.put(targetSum, null);
        return null;
    }

    public static int[] bestSumBF(int targetSum, int[] numbers) {
        if (targetSum <= 0) return targetSum == 0 ? new int[]{} : null;

        int[] best = null;
        for (int n : numbers) {
            int[] result = bestSumBF(targetSum - n, numbers);
            if (result != null) {
                final int[] way = append(result, n);
                if (best == null || way.length < best.length) {
                    best = way;
                }
            }
        }

        return best;
    }

    public static int[] bestSum(int targetSum, int[] numbers) {
        final Map<Integer, int[]> memo = new HashMap<>();
        return bestSum(targetSum, numbers, memo);
    }

    private static int[] bestSum(int targetSum, int[] numbers, Map<Integer, int[]> memo) {
        if (memo.containsKey(targetSum)) return memo.get(targetSum);
        if (targetSum <= 0) return targetSum == 0 ? new int[]{} : null;

        int[] best = null;
        for (int n : numbers) {
            int[] result = bestSum(targetSum - n, numbers, memo);
            if (result != null) {
                final int[] way = append(result, n);
                if (best == null || way.length < best.length) {
                    best = way;
                }
            }
        }
        memo.put(targetSum, best);
        return best;
    }

    private static int[] append(int[] a, int value) {
        final int[] newArray = Arrays.copyOf(a, a.length + 1);
        newArray[newArray.length - 1] = value;
        return newArray;
    }
}
