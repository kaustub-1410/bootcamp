class Solution {
    public String longestNiceSubstring(String s) {
        int n = s.length();

        String ans = "";

        for (int i = 0; i < n; i++) {

            int[] lower = new int[26];
            int[] upper = new int[26];

            for (int j = i; j < n; j++) {

                char ch = s.charAt(j);

                if (Character.isLowerCase(ch)) {
                    lower[ch - 'a']++;
                } else {
                    upper[ch - 'A']++;
                }

                if (isNice(lower, upper)) {
                    String curr = s.substring(i, j + 1);

                    if (curr.length() > ans.length()) {
                        ans = curr;
                    }
                }
            }
        }

        return ans;
    }

    private boolean isNice(int[] lower, int[] upper) {

        for (int i = 0; i < 26; i++) {

            if ((lower[i] > 0 && upper[i] == 0) ||
                (upper[i] > 0 && lower[i] == 0)) {
                return false;
            }
        }

        return true;
    }
}