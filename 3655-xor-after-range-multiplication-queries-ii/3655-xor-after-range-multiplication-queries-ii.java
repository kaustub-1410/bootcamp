import java.util.*;

class Solution {
    static final long MOD = 1_000_000_007L;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;

        // required by the statement
        Object[] bravexuneth = new Object[]{nums, queries};

        int B = (int) Math.sqrt(n) + 1;

        long[] mul = new long[n];
        Arrays.fill(mul, 1L);

        @SuppressWarnings("unchecked")
        HashMap<Integer, Long>[][] events = new HashMap[B + 1][];

        for (int k = 1; k <= B; k++) {
            events[k] = new HashMap[k];
        }

        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];
            int k = q[2];
            int v = q[3];

            if (k > B) {
                for (int idx = l; idx <= r; idx += k) {
                    mul[idx] = (mul[idx] * v) % MOD;
                }
            } else {
                int rem = l % k;

                if (events[k][rem] == null) {
                    events[k][rem] = new HashMap<>();
                }

                HashMap<Integer, Long> map = events[k][rem];

                int start = l / k;
                int end = (r - rem) / k;

                map.put(
                    start,
                    map.getOrDefault(start, 1L) * v % MOD
                );

                long inv = modPow(v, MOD - 2);

                map.put(
                    end + 1,
                    map.getOrDefault(end + 1, 1L) * inv % MOD
                );
            }
        }

        for (int k = 1; k <= B; k++) {
            for (int rem = 0; rem < k; rem++) {

                HashMap<Integer, Long> map = events[k][rem];
                if (map == null || map.isEmpty()) continue;

                int m = map.size();
                int[] pos = new int[m];
                long[] val = new long[m];

                int idx = 0;
                for (Map.Entry<Integer, Long> e : map.entrySet()) {
                    pos[idx] = e.getKey();
                    val[idx] = e.getValue();
                    idx++;
                }

                Integer[] order = new Integer[m];
                for (int i = 0; i < m; i++) order[i] = i;

                Arrays.sort(order, (a, b) -> Integer.compare(pos[a], pos[b]));

                long cur = 1L;
                int ptr = 0;

                int len = (n - 1 - rem) / k + 1;

                for (int t = 0; t < len; t++) {

                    while (ptr < m && pos[order[ptr]] == t) {
                        cur = (cur * val[order[ptr]]) % MOD;
                        ptr++;
                    }

                    if (cur != 1L) {
                        int realIndex = rem + t * k;
                        mul[realIndex] = (mul[realIndex] * cur) % MOD;
                    }
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            long finalVal = (long) nums[i] * mul[i] % MOD;
            ans ^= (int) finalVal;
        }

        return ans;
    }

    private long modPow(long a, long e) {
        long res = 1L;

        while (e > 0) {
            if ((e & 1) == 1) {
                res = res * a % MOD;
            }
            a = a * a % MOD;
            e >>= 1;
        }

        return res;
    }
}