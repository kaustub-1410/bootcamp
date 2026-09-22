class Solution {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, k, n, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int k, int remain,
                           List<Integer> path,
                           List<List<Integer>> result) {

        if (k == 0 && remain == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        if (k == 0 || remain < 0) {
            return;
        }

        for (int num = start; num <= 9; num++) {
            path.add(num);

            backtrack(num + 1, k - 1, remain - num, path, result);

            path.remove(path.size() - 1);
        }
    }
}