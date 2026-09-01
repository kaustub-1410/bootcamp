import java.util.*;

class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<Long> obstacleSet = new HashSet<>();

        for (int[] obs : obstacles) {
            long key = (((long) obs[0]) << 32) ^ (obs[1] & 0xffffffffL);
            obstacleSet.add(key);
        }

        int[][] dirs = {
            {0, 1},   // North
            {1, 0},   // East
            {0, -1},  // South
            {-1, 0}   // West
        };

        int dir = 0;
        int x = 0, y = 0;
        int maxDist = 0;

        for (int cmd : commands) {
            if (cmd == -1) {
                dir = (dir + 1) % 4;
            } else if (cmd == -2) {
                dir = (dir + 3) % 4;
            } else {
                for (int step = 0; step < cmd; step++) {
                    int nx = x + dirs[dir][0];
                    int ny = y + dirs[dir][1];

                    long key = (((long) nx) << 32) ^ (ny & 0xffffffffL);

                    if (obstacleSet.contains(key)) {
                        break;
                    }

                    x = nx;
                    y = ny;

                    maxDist = Math.max(maxDist, x * x + y * y);
                }
            }
        }

        return maxDist;
    }
}