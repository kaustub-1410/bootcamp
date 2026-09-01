import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0;
        int litterCount = 0;

        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        int targetMask = (1 << litterCount) - 1;

        // best[r][c][mask] = max remaining energy seen
        int[][][] best = new int[m][n][1 << litterCount];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(best[i][j], -1);
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc, 0, energy});

        best[sr][sc][0] = energy;

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        int moves = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] cur = q.poll();

                int r = cur[0];
                int c = cur[1];
                int mask = cur[2];
                int e = cur[3];

                if (mask == targetMask) {
                    return moves;
                }

                if (e == 0) {
                    continue;
                }

                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k];
                    int nc = c + dc[k];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    char cell = classroom[nr].charAt(nc);

                    if (cell == 'X') {
                        continue;
                    }

                    int newEnergy = e - 1;

                    if (cell == 'R') {
                        newEnergy = energy;
                    }

                    int newMask = mask;

                    if (cell == 'L') {
                        newMask |= (1 << litterId[nr][nc]);
                    }

                    // If we've already been here with equal or more energy,
                    // this state is dominated.
                    if (best[nr][nc][newMask] >= newEnergy) {
                        continue;
                    }

                    best[nr][nc][newMask] = newEnergy;
                    q.offer(new int[]{nr, nc, newMask, newEnergy});
                }
            }

            moves++;
        }

        return -1;
    }
}