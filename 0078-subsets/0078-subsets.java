class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), ans);
        return ans;
    }

    private void backtrack(int index, int[] nums,
                           List<Integer> curr,
                           List<List<Integer>> ans) {

        if (index == nums.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        // Include current element
        curr.add(nums[index]);
        backtrack(index + 1, nums, curr, ans);

        // Exclude current element
        curr.remove(curr.size() - 1);
        backtrack(index + 1, nums, curr, ans);
    }
}