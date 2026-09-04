import java.util.*;

class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;

        DSU dsu = new DSU(n);

        // Connect indices that are allowed to swap
        for (int[] swap : allowedSwaps) {
            dsu.union(swap[0], swap[1]);
        }

        // Map each component to frequency of source values
        Map<Integer, Map<Integer, Integer>> groups = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);

            groups.putIfAbsent(root, new HashMap<>());

            Map<Integer, Integer> freq = groups.get(root);
            freq.put(source[i], freq.getOrDefault(source[i], 0) + 1);
        }

        int answer = 0;

        // Try to match target values inside the same component
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            Map<Integer, Integer> freq = groups.get(root);

            int value = target[i];

            if (freq.getOrDefault(value, 0) > 0) {
                freq.put(value, freq.get(value) - 1);
            } else {
                answer++;
            }
        }

        return answer;
    }

    // Disjoint Set Union
    class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) {
                return;
            }

            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++;
            }
        }
    }
}