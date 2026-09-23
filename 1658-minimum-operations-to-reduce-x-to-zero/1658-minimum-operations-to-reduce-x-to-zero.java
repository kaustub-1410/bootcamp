class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;

        long totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        long target = totalSum - x;

        if (target < 0) return -1;
        if (target == 0) return n;

        int left = 0;
        long currSum = 0;
        int maxLen = -1;

        for (int right = 0; right < n; right++) {
            currSum += nums[right];

            while (currSum > target) {
                currSum -= nums[left++];
            }

            if (currSum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        return maxLen == -1 ? -1 : n - maxLen;
    }
}