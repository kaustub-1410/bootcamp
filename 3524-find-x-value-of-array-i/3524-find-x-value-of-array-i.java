class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];

        long[] dp = new long[k];

        for (int num : nums) {
            int val = num % k;

            long[] ndp = new long[k];

            // Start a new subarray at current position
            ndp[val]++;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int nr = (r * val) % k;
                    ndp[nr] += dp[r];
                }
            }

            // Add counts of subarrays ending here
            for (int r = 0; r < k; r++) {
                result[r] += ndp[r];
            }

            dp = ndp;
        }

        return result;
    }
}