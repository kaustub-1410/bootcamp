class Solution {
    public String shortestPalindrome(String s) {
        String rev = new StringBuilder(s).reverse().toString();

        String pattern = s + "#" + rev;

        int[] lps = new int[pattern.length()];

        for (int i = 1; i < pattern.length(); i++) {
            int j = lps[i - 1];

            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = lps[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }

            lps[i] = j;
        }

        int longestPalPrefix = lps[pattern.length() - 1];

        String suffix = s.substring(longestPalPrefix);

        return new StringBuilder(suffix).reverse().toString() + s;
    }
}