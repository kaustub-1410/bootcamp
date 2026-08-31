class Solution {
    private int rows, cols;
    private int originalColor;
    private int newColor;

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        rows = image.length;
        cols = image[0].length;
        originalColor = image[sr][sc];
        newColor = color;

        if (originalColor == newColor) {
            return image;
        }

        dfs(image, sr, sc);
        return image;
    }

    private void dfs(int[][] image, int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols) {
            return;
        }

        if (image[r][c] != originalColor) {
            return;
        }

        image[r][c] = newColor;

        dfs(image, r + 1, c);
        dfs(image, r - 1, c);
        dfs(image, r, c + 1);
        dfs(image, r, c - 1);
    }
}