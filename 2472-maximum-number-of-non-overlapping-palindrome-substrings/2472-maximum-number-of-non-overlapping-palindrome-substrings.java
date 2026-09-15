class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();

        boolean[][] pal = new boolean[n][n];

        // Build palindrome table
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 2 || pal[i + 1][j - 1]) {
                        pal[i][j] = true;
                    }
                }
            }
        }

        int[] dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            // Skip current character
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);

            // End a palindrome at i
            for (int j = 0; j <= i; j++) {
                if (i - j + 1 >= k && pal[j][i]) {
                    dp[i + 1] = Math.max(dp[i + 1], dp[j] + 1);
                }
            }
        }

        return dp[n];
    }
}