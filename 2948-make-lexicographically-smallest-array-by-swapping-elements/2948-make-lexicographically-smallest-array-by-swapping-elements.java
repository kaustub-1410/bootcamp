import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i]; // value
            arr[i][1] = i;       // original index
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int[] result = new int[n];
        int start = 0;

        while (start < n) {
            int end = start;

            // Find one connected group
            while (end + 1 < n && arr[end + 1][0] - arr[end][0] <= limit) {
                end++;
            }

            List<Integer> indices = new ArrayList<>();
            List<Integer> values = new ArrayList<>();

            for (int i = start; i <= end; i++) {
                indices.add(arr[i][1]);
                values.add(arr[i][0]);
            }

            Collections.sort(indices);

            // Values are already sorted because arr is sorted by value
            for (int i = 0; i < indices.size(); i++) {
                result[indices.get(i)] = values.get(i);
            }

            start = end + 1;
        }

        return result;
    }
}