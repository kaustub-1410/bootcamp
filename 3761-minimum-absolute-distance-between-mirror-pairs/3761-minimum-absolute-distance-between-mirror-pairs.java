import java.util.*;

class Solution {
    public int minMirrorPairDistance(int[] nums) {
        Map<Integer, Integer> last = new HashMap<>();
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {
            // If reverse of an earlier number equals nums[i]
            if (last.containsKey(nums[i])) {
                ans = Math.min(ans, i - last.get(nums[i]));
            }

            // Store reverse(nums[i]) for future elements
            int rev = reverse(nums[i]);
            last.put(rev, i);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int reverse(int x) {
        int rev = 0;

        while (x > 0) {
            rev = rev * 10 + (x % 10);
            x /= 10;
        }

        return rev;
    }
}