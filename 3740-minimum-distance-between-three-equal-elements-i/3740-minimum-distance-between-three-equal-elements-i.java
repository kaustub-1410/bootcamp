class Solution {
    public int minimumDistance(int[] nums) {
        // For each value, store its last two indices
        int[][] last = new int[nums.length + 1][2];

        // Initialize with -1
        for (int i = 0; i <= nums.length; i++) {
            last[i][0] = -1;
            last[i][1] = -1;
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];

            // If we already have two occurrences, form a triple
            if (last[x][0] != -1 && last[x][1] != -1) {
                int first = last[x][0];
                int second = last[x][1];

                int distance = 2 * (i - first);
                ans = Math.min(ans, distance);
            }

            // Shift the last two occurrences
            last[x][0] = last[x][1];
            last[x][1] = i;
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}