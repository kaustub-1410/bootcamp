import java.util.*;

class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;

                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[]{i, j});
                    grid[i][j] = '0';

                    while (!q.isEmpty()) {
                        int[] cur = q.poll();

                        for (int[] d : dirs) {
                            int nr = cur[0] + d[0];
                            int nc = cur[1] + d[1];

                            if (nr >= 0 && nc >= 0 && nr < m && nc < n
                                    && grid[nr][nc] == '1') {
                                grid[nr][nc] = '0';
                                q.offer(new int[]{nr, nc});
                            }
                        }
                    }
                }
            }
        }

        return count;
    }
}