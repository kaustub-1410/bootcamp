class Solution {
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum, new ArrayList<>());
        return result;
    }

    private void dfs(TreeNode node, int remaining, List<Integer> path) {
        if (node == null) {
            return;
        }

        path.add(node.val);
        remaining -= node.val;

        // Leaf node
        if (node.left == null && node.right == null) {
            if (remaining == 0) {
                result.add(new ArrayList<>(path));
            }
        } else {
            dfs(node.left, remaining, path);
            dfs(node.right, remaining, path);
        }

        // Backtrack
        path.remove(path.size() - 1);
    }
}