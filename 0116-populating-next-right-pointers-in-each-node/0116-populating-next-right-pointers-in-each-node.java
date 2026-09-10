class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node leftMost = root;

        while (leftMost.left != null) {
            Node curr = leftMost;

            while (curr != null) {
                // Connect left child -> right child
                curr.left.next = curr.right;

                // Connect right child -> next node's left child
                if (curr.next != null) {
                    curr.right.next = curr.next.left;
                }

                curr = curr.next;
            }

            leftMost = leftMost.left;
        }

        return root;
    }
}