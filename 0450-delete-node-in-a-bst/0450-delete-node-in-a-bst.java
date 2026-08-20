class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        }
        else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        }
        else {

            if (root.left == null) {
                return root.right;
            }

            if (root.right == null) {
                return root.left;
            }

            TreeNode temp = root.right;

            while (temp.left != null) {
                temp = temp.left;
            }
            root.val = temp.val;

            root.right = deleteNode(root.right, temp.val);
        }

        return root;
    }
}