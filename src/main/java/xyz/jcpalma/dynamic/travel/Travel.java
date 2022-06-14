package xyz.jcpalma.dynamic.travel;

import java.util.HashMap;
import java.util.Map;

public class Travel {

    record Memo(int m, int n) {
        public static Memo of(int m, int n) {
            return new Memo(m, n);
        }
    }

    public static long gridTravel(int m, int n) {
        Map<Memo, Long> memo = new HashMap<>();
        return gridTravel(m, n, memo);
    }

    private static long gridTravel(int m, int n, Map<Memo, Long> memo) {
        Memo key = Memo.of(m, n);
        if (memo.containsKey(key)) return memo.get(key);

        if (m == 1 && n == 1) return 1;
        if (m == 0 || n == 0) return 0;

        long result = gridTravel(m - 1, n, memo) + gridTravel(m, n - 1, memo);
        memo.put(key, result);
        return result;
    }

    public static long gridTravelBF(int m, int n) {
        if (m == 1 && n == 1) return 1;
        if (m == 0 || n == 0) return 0;
        return gridTravelBF(m - 1, n) + gridTravelBF(m, n - 1);
    }

}
