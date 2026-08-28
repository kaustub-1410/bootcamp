class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();

        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        int odd = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) {
                odd++;
                mid = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        int[] left = new int[26];
        for (int i = 0; i < 26; i++) {
            left[i] = cnt[i] / 2;
        }

        int m = n / 2;
        char[] half = new char[m];

        if (!dfs(0, false, half, left, target, n, mid)) {
            return "";
        }

        StringBuilder ans = new StringBuilder();

        for (char c : half) ans.append(c);
        if ((n & 1) == 1) ans.append(mid);
        for (int i = m - 1; i >= 0; i--) ans.append(half[i]);

        String res = ans.toString();
        return res.compareTo(target) > 0 ? res : "";
    }

    private boolean dfs(
        int pos,
        boolean greater,
        char[] half,
        int[] left,
        String target,
        int n,
        char mid
    ) {
        int m = half.length;

        if (pos == m) {
            StringBuilder sb = new StringBuilder();

            for (char c : half) sb.append(c);
            if ((n & 1) == 1) sb.append(mid);
            for (int i = m - 1; i >= 0; i--) sb.append(half[i]);

            return sb.toString().compareTo(target) > 0;
        }

        for (int c = 0; c < 26; c++) {
            if (left[c] == 0) continue;

            char ch = (char) ('a' + c);

            left[c]--;
            half[pos] = ch;

            boolean nextGreater = greater;

            if (!greater) {
                if (ch > target.charAt(pos)) {
                    nextGreater = true;
                } else if (ch < target.charAt(pos)) {
                    left[c]++;
                    continue;
                }
            }

            if (dfs(pos + 1, nextGreater, half, left, target, n, mid)) {
                return true;
            }

            left[c]++;
        }

        return false;
    }
}