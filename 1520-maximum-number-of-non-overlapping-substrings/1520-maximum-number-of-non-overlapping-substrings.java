class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        for (int c = 0; c < 26; c++) {
            if (first[c] == n) continue;

            int l = first[c];
            int r = last[c];

            boolean valid = true;

            for (int i = l; i <= r; i++) {
                int ch = s.charAt(i) - 'a';

                if (first[ch] < l) {
                    valid = false;
                    break;
                }

                r = Math.max(r, last[ch]);
            }

            if (valid) {
                intervals.add(new int[]{l, r});
            }
        }

        intervals.sort((a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        List<int[]> chosen = new ArrayList<>();

        for (int[] cur : intervals) {
            if (chosen.isEmpty() || cur[0] > chosen.get(chosen.size() - 1)[1]) {
                chosen.add(cur);
            } else {
                int[] prev = chosen.get(chosen.size() - 1);

                if (cur[1] < prev[1]) {
                    chosen.set(chosen.size() - 1, cur);
                }
            }
        }

        List<String> ans = new ArrayList<>();

        for (int[] in : chosen) {
            ans.add(s.substring(in[0], in[1] + 1));
        }

        return ans;
    }
}