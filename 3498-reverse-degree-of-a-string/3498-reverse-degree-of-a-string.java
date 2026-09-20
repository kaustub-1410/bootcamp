class Solution {
    public int reverseDegree(String s) {
        int sum = 0;

        for (int i = 0; i < s.length(); i++) {
            int reversePos = 'z' - s.charAt(i) + 1; // a=26, b=25, ..., z=1
            sum += reversePos * (i + 1);            // 1-indexed position
        }

        return sum;
    }
}