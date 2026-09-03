class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;
        boolean hasOdd = false;

        for (int x : nums1) {
            min = Math.min(min, x);
            if ((x & 1) == 1) {
                hasOdd = true;
            }
        }

        if (!hasOdd) return true; // all even

        return (min & 1) == 1;    // minimum element is odd
    }
}