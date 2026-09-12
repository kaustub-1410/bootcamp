class Solution {

    static class State {
        long weight;
        int[] idx;

        State(long weight, int[] idx) {
            this.weight = weight;
            this.idx = idx;
        }
    }

    private State better(State a, State b) {
        if (a == null) return b;
        if (b == null) return a;

        if (a.weight != b.weight) {
            return a.weight > b.weight ? a : b;
        }

        int n = a.idx.length;
        int m = b.idx.length;
        int len = Math.min(n, m);

        for (int i = 0; i < len; i++) {
            if (a.idx[i] != b.idx[i]) {
                return a.idx[i] < b.idx[i] ? a : b;
            }
        }

        return n <= m ? a : b;
    }

    private int[] insertSorted(int[] arr, int val) {
        int[] res = new int[arr.length + 1];
        int i = 0, j = 0;

        while (i < arr.length && arr[i] < val) {
            res[j++] = arr[i++];
        }

        res[j++] = val;

        while (i < arr.length) {
            res[j++] = arr[i++];
        }

        return res;
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        long[][] arr = new long[n][4];
        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0); // start
            arr[i][1] = intervals.get(i).get(1); // end
            arr[i][2] = intervals.get(i).get(2); // weight
            arr[i][3] = i;                       // original index
        }

        java.util.Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) return Long.compare(a[1], b[1]);
            return Long.compare(a[0], b[0]);
        });

        long[] ends = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            ends[i] = arr[i - 1][1];
        }

        int[] p = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            long start = arr[i - 1][0];

            int lo = 1, hi = n, ans = 0;

            while (lo <= hi) {
                int mid = (lo + hi) >>> 1;

                // strictly non-overlapping:
                // previous end < current start
                if (ends[mid] < start) {
                    ans = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            p[i] = ans;
        }

        State[][] dp = new State[5][n + 1];

        for (int i = 0; i <= n; i++) {
            dp[0][i] = new State(0L, new int[0]);
        }

        for (int k = 1; k <= 4; k++) {
            dp[k][0] = null;

            for (int i = 1; i <= n; i++) {
                State skip = dp[k][i - 1];

                State take = null;
                State prev = dp[k - 1][p[i]];

                if (prev != null) {
                    long newWeight = prev.weight + arr[i - 1][2];
                    int originalIndex = (int) arr[i - 1][3];

                    take = new State(
                            newWeight,
                            insertSorted(prev.idx, originalIndex)
                    );
                }

                dp[k][i] = better(skip, take);
            }
        }

        State answer = new State(0L, new int[0]);

        for (int k = 0; k <= 4; k++) {
            answer = better(answer, dp[k][n]);
        }

        return answer.idx;
    }
}