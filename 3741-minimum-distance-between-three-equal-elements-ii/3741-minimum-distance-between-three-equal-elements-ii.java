class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        // last1[x] = most recent index of x
        // last2[x] = second most recent index of x
        int[] last1 = new int[n + 1];
        int[] last2 = new int[n + 1];

        // -1 means no occurrence yet
        java.util.Arrays.fill(last1, -1);
        java.util.Arrays.fill(last2, -1);

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            // We already have two previous occurrences of x
            if (last2[x] != -1) {
                int distance = 2 * (i - last2[x]);
                ans = Math.min(ans, distance);
            }

            // Update the last two positions
            last2[x] = last1[x];
            last1[x] = i;
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}