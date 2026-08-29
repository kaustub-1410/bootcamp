import java.util.*;

class Solution {

    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        int[] cnt = new int[26];
        for (char ch : s.toCharArray()) {
            cnt[ch - 'a']++;
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int need = target.charAt(i) - 'a';
            boolean found = false;

            for (int c = need; c < 26; c++) {
                if (cnt[c] == 0) continue;

                cnt[c]--;

                if (c > need) {
                    ans.append((char) ('a' + c));

                    for (int k = 0; k < 26; k++) {
                        while (cnt[k] > 0) {
                            ans.append((char) ('a' + k));
                            cnt[k]--;
                        }
                    }
                    return ans.toString();
                }

                if (canMakeGreater(i + 1, cnt, target)) {
                    ans.append((char) ('a' + c));
                    found = true;
                    break;
                }

                cnt[c]++;
            }

            if (!found) return "";
        }

        return "";
    }

    private boolean canMakeGreater(int pos, int[] cnt, String target) {
        int[] temp = cnt.clone();

        for (int i = pos; i < target.length(); i++) {
            int t = target.charAt(i) - 'a';

            for (int c = 25; c >= 0; c--) {
                if (temp[c] == 0) continue;

                temp[c]--;

                if (c > t) return true;
                if (c < t) return false;

                break;
            }
        }

        return false; // equal, not strictly greater
    }
}