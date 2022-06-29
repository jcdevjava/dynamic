package xyz.jcpalma.dynamic.construct;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Construct {

    public static boolean canConstructBF(String target, String[] words) {
        if (target.isEmpty()) return true;

        for (String word : words) {
            if (target.startsWith(word)) {
                String suffix = target.substring(word.length());
                if (canConstructBF(suffix, words)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean canConstruct(String target, String[] words) {
        final Map<String, Boolean> memo = new HashMap<>();
        return canConstruct(target, words, memo);
    }

    private static boolean canConstruct(String target, String[] words, Map<String, Boolean> memo) {
        if (memo.containsKey(target)) return memo.get(target);
        if (target.isEmpty()) return true;

        for (String word : words) {
            if (target.startsWith(word)) {
                String suffix = target.substring(word.length());
                if (canConstruct(suffix, words, memo)) {
                    memo.put(target, true);
                    return true;
                }
            }
        }
        memo.put(target, false);
        return false;
    }

    public static int countConstructBF(String target, String[] words) {
        if (target.isEmpty()) return 1;

        int count = 0;

        for (String word : words) {
            if (target.startsWith(word)) {
                String suffix = target.substring(word.length());
                count += countConstructBF(suffix, words);
            }
        }
        return count;
    }

    public static int countConstruct(String target, String[] words) {
        final Map<String, Integer> memo = new HashMap<>();
        return countConstruct(target, words, memo);
    }

    private static int countConstruct(String target, String[] words, Map<String, Integer> memo) {
        if (memo.containsKey(target)) return memo.get(target);
        if (target.isEmpty()) return 1;

        int count = 0;

        for (String word : words) {
            if (target.startsWith(word)) {
                String suffix = target.substring(word.length());
                count += countConstruct(suffix, words, memo);
            }
        }
        memo.put(target, count);
        return count;
    }

    public static String[][] allConstructBF(String target, String[] words) {
        if (target.isEmpty()) return new String[][]{{}};

        String[][] allWays = {};
        for (String word : words) {
            if (target.startsWith(word)) {
                String suffix = target.substring(word.length());
                for (var suffixWays : allConstructBF(suffix, words)) {
                    allWays = append(allWays, append(suffixWays, word));
                }
            }
        }
        return allWays;
    }

    public static String[][] allConstruct(String target, String[] words) {
        final Map<String, String[][]> memo = new HashMap<>();
        return allConstruct(target, words, memo);
    }

    private static String[][] allConstruct(String target, String[] words, Map<String, String[][]> memo) {
        if (memo.containsKey(target)) return memo.get(target);
        if (target.isEmpty()) return new String[][]{{}};

        String[][] allWays = {};
        for (String word : words) {
            if (target.startsWith(word)) {
                String suffix = target.substring(word.length());
                for (var suffixWays : allConstruct(suffix, words, memo)) {
                    allWays = append(allWays, append(suffixWays, word));
                }
            }
        }
        memo.put(target, allWays);
        return allWays;
    }

    /**
     * Appends the specified word at the beginning of the array.
     *
     * @param array the array where the words will be appended.
     * @param word  the word to append.
     * @return the new array with the word appended.
     */
    private static String[] append(String[] array, String word) {
        return Stream
            .concat(Stream.of(word), Stream.of(array))
            .toArray(String[]::new);
    }

    /**
     * Appends the specified array of words to the end of the 2D array.
     *
     * @param array the 2D array where the array will be appended.
     * @param words array to append
     * @return the new 2D array with the array appended.
     */
    private static String[][] append(String[][] array, String[] words) {
        return Stream
            .concat(Stream.of(array), Stream.<String[]>of(words))
            .toArray(String[][]::new);
    }

}
