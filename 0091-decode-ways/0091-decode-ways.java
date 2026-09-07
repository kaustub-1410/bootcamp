class Solution {
    public int numDecodings(String s) {

        int n = s.length();

        int[] dp = new int[n + 1];

        // Empty string has one way
        dp[0] = 1;

        // First character
        if (s.charAt(0) != '0') {
            dp[1] = 1;
        }

        for (int i = 2; i <= n; i++) {

            // Take one digit
            char one = s.charAt(i - 1);

            if (one != '0') {
                dp[i] += dp[i - 1];
            }

            // Take two digits
            int two = Integer.parseInt(s.substring(i - 2, i));

            if (two >= 10 && two <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}