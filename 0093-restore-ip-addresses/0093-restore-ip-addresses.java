import java.util.*;

class Solution {

    public List<String> restoreIpAddresses(String s) {

        List<String> result = new ArrayList<>();

        backtrack(s, 0, 0, new StringBuilder(), result);

        return result;
    }

    private void backtrack(
            String s,
            int index,
            int parts,
            StringBuilder current,
            List<String> result) {

        // We have created 4 parts
        if (parts == 4) {

            // All digits must be used
            if (index == s.length()) {
                result.add(current.toString());
            }

            return;
        }

        // Try segment lengths 1, 2, 3
        for (int len = 1; len <= 3; len++) {

            // Don't go beyond string
            if (index + len > s.length()) {
                break;
            }

            String part = s.substring(index, index + len);

            // Leading zero
            if (part.length() > 1 && part.charAt(0) == '0') {
                break;
            }

            // Value must be <= 255
            int value = Integer.parseInt(part);

            if (value > 255) {
                break;
            }

            // Add dot before every part except first
            int oldLength = current.length();

            if (parts > 0) {
                current.append(".");
            }

            current.append(part);

            // Recursively create next part
            backtrack(
                s,
                index + len,
                parts + 1,
                current,
                result
            );

            // Backtrack
            current.setLength(oldLength);
        }
    }
}