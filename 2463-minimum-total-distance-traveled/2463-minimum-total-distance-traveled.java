class Solution {

    private long[][] memo;
    private List<Integer> robots;
    private int[][] factories;

    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (a, b) -> Integer.compare(a[0], b[0]));

        robots = robot;
        factories = factory;

        memo = new long[robot.size() + 1][factory.length + 1];

        for (long[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dfs(0, 0);
    }

    private long dfs(int i, int j) {
        if (i == robots.size()) return 0;

        if (j == factories.length) {
            return Long.MAX_VALUE / 4;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        long ans = dfs(i, j + 1); // skip factory

        long cost = 0;
        int pos = factories[j][0];
        int limit = factories[j][1];

        for (int k = 1; k <= limit && i + k - 1 < robots.size(); k++) {

            cost += Math.abs((long) robots.get(i + k - 1) - pos);

            ans = Math.min(
                ans,
                cost + dfs(i + k, j + 1)
            );
        }

        return memo[i][j] = ans;
    }
}