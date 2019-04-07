package com.mirak.leetcode.individual.medium;

public class MinimumFallingPathSum {

  public int minFallingPathSum(int[][] A) {
    int n = A.length;
    int[][] dp = new int[2][n];
    for (int j = 0; j < n; ++j) {
      dp[(n - 1) & 1][j] = A[n - 1][j];
    }
    for (int i = n - 2; i >= 0; --i) {
      int idx = i & 1;
      for (int j = 0; j < n; ++j) {
        dp[idx][j] = dp[idx ^ 1][j];
        if (j + 1 < n) {
          dp[idx][j] = Math.min(dp[idx][j], dp[idx ^ 1][j + 1]);
        }
        if (j - 1 >= 0) {
          dp[idx][j] = Math.min(dp[idx][j], dp[idx ^ 1][j - 1]);
        }
        dp[idx][j] += A[i][j];
      }
    }
    int min = Integer.MAX_VALUE;
    for (int j = 0; j < n; ++j) {
      min = Math.min(min, dp[0][j]);
    }
    return min;
  }

}
