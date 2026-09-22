class Solution {

    class Node {
        int prod;
        long[] cnt;

        Node(int k) {
            cnt = new long[k];
        }
    }

    int n, k;
    Node[] seg;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.n = nums.length;
        this.k = k;

        seg = new Node[4 * n];
        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, idx, value % k);

            Node res = query(1, 0, n - 1, start, n - 1);
            ans[i] = (int) res.cnt[x];
        }

        return ans;
    }

    private void build(int node, int l, int r, int[] nums) {
        seg[node] = new Node(k);

        if (l == r) {
            int v = nums[l] % k;
            seg[node].prod = v;
            seg[node].cnt[v] = 1;
            return;
        }

        int mid = (l + r) >> 1;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        seg[node] = merge(seg[node * 2], seg[node * 2 + 1]);
    }

    private void update(int node, int l, int r, int idx, int value) {
        if (l == r) {
            seg[node] = new Node(k);
            seg[node].prod = value;
            seg[node].cnt[value] = 1;
            return;
        }

        int mid = (l + r) >> 1;

        if (idx <= mid) {
            update(node * 2, l, mid, idx, value);
        } else {
            update(node * 2 + 1, mid + 1, r, idx, value);
        }

        seg[node] = merge(seg[node * 2], seg[node * 2 + 1]);
    }

    private Node query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) return seg[node];

        int mid = (l + r) >> 1;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    private Node merge(Node a, Node b) {
        Node res = new Node(k);

        res.prod = (a.prod * b.prod) % k;

        for (int i = 0; i < k; i++) {
            res.cnt[i] += a.cnt[i];
        }

        for (int rb = 0; rb < k; rb++) {
            int nr = (a.prod * rb) % k;
            res.cnt[nr] += b.cnt[rb];
        }

        return res;
    }
}