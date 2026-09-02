class Solution {
    public boolean isMatch(String s, String p) {
        int i = 0; // pointer for s
        int j = 0; // pointer for p

        int star = -1;       // last position of '*'
        int match = 0;       // position in s matched by '*'

        while (i < s.length()) {

            // Characters match or pattern has '?'
            if (j < p.length() &&
                (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                i++;
                j++;
            }

            // Pattern has '*'
            else if (j < p.length() && p.charAt(j) == '*') {
                star = j;
                match = i;
                j++;
            }

            // Mismatch
            else if (star != -1) {
                // Let '*' match one more character
                j = star + 1;
                match++;
                i = match;
            }

            // No '*' available to handle mismatch
            else {
                return false;
            }
        }

        // Remaining pattern characters must all be '*'
        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }

        return j == p.length();
    }
}