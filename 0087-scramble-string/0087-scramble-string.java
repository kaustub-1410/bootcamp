import java.util.*;

class Solution {

    private Map<String, Boolean> memo = new HashMap<>();

    public boolean isScramble(String s1, String s2) {

        if (s1.length() != s2.length()) {
            return false;
        }

        return solve(s1, s2);
    }

    private boolean solve(String s1, String s2) {

        // Already calculated
        String key = s1 + "#" + s2;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Same string
        if (s1.equals(s2)) {
            memo.put(key, true);
            return true;
        }

        // Check character frequencies
        int[] count = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }

        for (int c : count) {
            if (c != 0) {
                memo.put(key, false);
                return false;
            }
        }

        int n = s1.length();

        // Try every possible split
        for (int i = 1; i < n; i++) {

            // Case 1: No swap
            //
            // s1 = left | right
            // s2 = left | right
            //
            if (solve(
                    s1.substring(0, i),
                    s2.substring(0, i)
                )
                &&
                solve(
                    s1.substring(i),
                    s2.substring(i)
                )) {

                memo.put(key, true);
                return true;
            }

            // Case 2: Swap
            //
            // s1 = left | right
            // s2 = right | left
            //
            if (solve(
                    s1.substring(0, i),
                    s2.substring(n - i)
                )
                &&
                solve(
                    s1.substring(i),
                    s2.substring(0, n - i)
                )) {

                memo.put(key, true);
                return true;
            }
        }

        memo.put(key, false);
        return false;
    }
}