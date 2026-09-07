class Solution {

    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = null;

    public void recoverTree(TreeNode root) {

        TreeNode current = root;

        while (current != null) {

            // No left child
            if (current.left == null) {

                check(current);

                prev = current;
                current = current.right;

            } else {

                // Find inorder predecessor
                TreeNode predecessor = current.left;

                while (predecessor.right != null &&
                       predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                // Create temporary link
                if (predecessor.right == null) {

                    predecessor.right = current;
                    current = current.left;

                } else {

                    // Remove temporary link
                    predecessor.right = null;

                    check(current);

                    prev = current;
                    current = current.right;
                }
            }
        }

        // Swap the incorrect values
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void check(TreeNode current) {

        if (prev != null && prev.val > current.val) {

            if (first == null) {
                first = prev;
            }

            second = current;
        }
    }
}