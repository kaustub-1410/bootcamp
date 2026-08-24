class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        solve(candidates, target, 0, new ArrayList<>(), result);

        return result;
    }

    public void solve(int[] candidates, int target, int index,
                      List<Integer> current, List<List<Integer>> result) {

        // Found a valid combination
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Target exceeded
        if (target < 0) {
            return;
        }

        // Try every candidate starting from index
        for (int i = index; i < candidates.length; i++) {

            current.add(candidates[i]);

            // Use same i because a number can be chosen multiple times
            solve(candidates, target - candidates[i], i, current, result);

            // Backtrack
            current.remove(current.size() - 1);
        }
    }
}