package com.mirak.leetcode.individual.hard;

import java.util.*;

public class LongestIncreasingPathInAMatrix {

  private final int INF = 1000000000;
  private int[] rowInc = {1, -1, 0, 0};
  private int[] colInc = {0, 0, 1, -1};

  public int longestIncreasingPath(int[][] matrix) {
    int n = matrix.length;
    if (n == 0) {
      return 0;
    }
    int m = matrix[0].length;
    int[][] longestIncreasing = new int[n][m];
    for (int[] row : longestIncreasing) {
      Arrays.fill(row, -INF);
    }
    int ans = 1;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        ans = Math.max(ans, dfs(i, j, -1, -1, matrix, longestIncreasing, n, m));
      }
    }
    return ans;
  }

  private int dfs(int i, int j, int prevI, int prevJ, int[][] matrix, int[][] longestIncreasing,
      int n, int m) {
    if (i < 0 || j < 0 || i == n || j == m || (prevI >= 0
        && matrix[i][j] <= matrix[prevI][prevJ])) {
      return 0;
    }
    if (longestIncreasing[i][j] != -INF) {
      return longestIncreasing[i][j];
    }
    for (int k = 0; k < rowInc.length; k++) {
      int i_ = i + rowInc[k];
      int j_ = j + colInc[k];
      longestIncreasing[i][j] = Math
          .max(longestIncreasing[i][j], 1 + dfs(i_, j_, i, j, matrix, longestIncreasing, n, m));
    }
    return longestIncreasing[i][j];
  }

}
