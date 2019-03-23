package com.mirak.leetcode.individual.hard;

public class SwimInRisingWater {

  private int[] rowInc = {1, -1, 0, 0};
  private int[] colInc = {0, 0, 1, -1};

  public int swimInWater(int[][] grid) {
    int n = grid.length;
    if (n == 0) {
      return 0;
    }
    int m = grid[0].length;
    int low = 0;
    int high = n * m;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (canPass(grid, n, m, mid)) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }

  private boolean canPass(int[][] grid, int n, int m, int max) {
    boolean[][] vis = new boolean[n][m];
    dfs(0, 0, grid, vis, n, m, max);
    return vis[n - 1][m - 1];
  }

  private void dfs(int i, int j, int[][] grid, boolean[][] vis, int n, int m, int max) {
    if (i < 0 || j < 0 || i == n || j == m || vis[i][j] || grid[i][j] > max) {
      return;
    }
    vis[i][j] = true;
    for (int k = 0; k < rowInc.length; k++) {
      dfs(i + rowInc[k], j + colInc[k], grid, vis, n, m, max);
    }
  }

}
