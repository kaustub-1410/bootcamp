class Solution {
    public int kthSmallest(TreeNode root, int k) {

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {

            // Go to the leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // Get the smallest remaining node
            current = stack.pop();

            k--;

            // If k becomes 0, this is the kth smallest
            if (k == 0) {
                return current.val;
            }

            // Move to the right subtree
            current = current.right;
        }

        return -1;
    }
}