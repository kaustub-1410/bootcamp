import java.util.*;

class Solution {
    public int missingInteger(int[] nums) {
        int sum = nums[0];

        // Find longest sequential prefix and its sum
        int i = 1;
        while (i < nums.length && nums[i] == nums[i - 1] + 1) {
            sum += nums[i];
            i++;
        }

        // Store all numbers in a set
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        // Find smallest missing integer >= sum
        int x = sum;
        while (set.contains(x)) {
            x++;
        }

        return x;
    }
}