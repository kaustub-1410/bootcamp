class Solution {
    public ListNode partition(ListNode head, int x) {

        // Dummy nodes for the two partitions
        ListNode lessDummy = new ListNode(0);
        ListNode greaterDummy = new ListNode(0);

        ListNode less = lessDummy;
        ListNode greater = greaterDummy;

        ListNode current = head;

        while (current != null) {

            if (current.val < x) {
                // Add to less-than list
                less.next = current;
                less = less.next;
            } else {
                // Add to greater-than-or-equal list
                greater.next = current;
                greater = greater.next;
            }

            current = current.next;
        }

        // Important: terminate the greater list
        greater.next = null;

        // Connect both lists
        less.next = greaterDummy.next;

        return lessDummy.next;
    }
}