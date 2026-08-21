class Solution {

    int preIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        return solve(
                preorder,
                inorder,
                0,
                inorder.length - 1
        );
    }

    public TreeNode solve(
            int[] preorder,
            int[] inorder,
            int start,
            int end) {

        // Base Case
        if (start > end) {
            return null;
        }

        // First preorder element is the root
        TreeNode root =
                new TreeNode(preorder[preIndex]);

        preIndex++;

        // Find root in inorder
        int index = start;

        for (int i = start; i <= end; i++) {

            if (inorder[i] == root.val) {
                index = i;
                break;
            }
        }

        // Build left subtree
        root.left =
                solve(
                        preorder,
                        inorder,
                        start,
                        index - 1
                );

        // Build right subtree
        root.right =
                solve(
                        preorder,
                        inorder,
                        index + 1,
                        end
                );

        return root;
    }
}