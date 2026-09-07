class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int n = s.length();

        long[] dp = new long[n + 1];
        dp[0] = 1; // empty subsequence

        int[] last = new int[26];
        Arrays.fill(last, -1);

        for (int i = 1; i <= n; i++) {
            char ch = s.charAt(i - 1);

            dp[i] = (2 * dp[i - 1]) % MOD;

            int prev = last[ch - 'a'];

            if (prev != -1) {
                dp[i] = (dp[i] - dp[prev] + MOD) % MOD;
            }

            last[ch - 'a'] = i - 1;
        }

        return (int)((dp[n] - 1 + MOD) % MOD);
    }
}