class Solution {

    private int dist(int a, int b) {
        if (a == 26 || b == 26) return 0;

        int r1 = a / 6, c1 = a % 6;
        int r2 = b / 6, c2 = b % 6;

        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    public int minimumDistance(String word) {
        int INF = 1_000_000;

        int[][] dp = new int[27][27];

        for (int i = 0; i < 27; i++) {
            for (int j = 0; j < 27; j++) {
                dp[i][j] = INF;
            }
        }

        dp[26][26] = 0; // both fingers unused

        for (char ch : word.toCharArray()) {
            int cur = ch - 'A';

            int[][] ndp = new int[27][27];

            for (int i = 0; i < 27; i++) {
                for (int j = 0; j < 27; j++) {
                    ndp[i][j] = INF;
                }
            }

            for (int f1 = 0; f1 < 27; f1++) {
                for (int f2 = 0; f2 < 27; f2++) {

                    if (dp[f1][f2] == INF) continue;

                    // use finger 1
                    ndp[cur][f2] = Math.min(
                        ndp[cur][f2],
                        dp[f1][f2] + dist(f1, cur)
                    );

                    // use finger 2
                    ndp[f1][cur] = Math.min(
                        ndp[f1][cur],
                        dp[f1][f2] + dist(f2, cur)
                    );
                }
            }

            dp = ndp;
        }

        int ans = INF;

        for (int i = 0; i < 27; i++) {
            for (int j = 0; j < 27; j++) {
                ans = Math.min(ans, dp[i][j]);
            }
        }

        return ans;
    }
}