package com.mirak.leetcode.individual.medium;

public class NumberOfCornerRectangles {

  public int countCornerRectangles(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;

    int[][] dp = new int[n][m];

    int count = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == 0) {
          continue;
        }
        for (int k = j + 1; k < m; k++) {
          if (grid[i][k] == 0) {
            continue;
          }
          count += dp[j][k];
          dp[j][k]++;
        }
      }
    }
    return count;
  }
}
