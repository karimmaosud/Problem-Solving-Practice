package com.mirak.leetcode.individual.medium;

import java.util.*;

public class OutOfBoundaryPaths {

  private final int MOD = 1000000007;

  private int[] rowInc = {1, -1, 0, 0};
  private int[] colInc = {0, 0, 1, -1};

  public int findPaths(int m, int n, int N, int i, int j) {
    int[][][] dp = new int[m][n][N + 1];
    for (int[][] ar : dp) {
      for (int[] row : ar) {
        Arrays.fill(row, -1);
      }
    }
    return solve(i, j, N, m, n, dp);
  }

  private int solve(int i, int j, int N, int n, int m, int[][][] dp) {
    if (i < 0 || j < 0 || i == n || j == m) {
      return 1;
    }
    if (N == 0) {
      return 0;
    }
    if (dp[i][j][N] != -1) {
      return dp[i][j][N];
    }
    int ans = 0;
    for (int k = 0; k < rowInc.length; k++) {
      ans = (ans % MOD + solve(i + rowInc[k], j + colInc[k], N - 1, n, m, dp) % MOD) % MOD;
    }
    return dp[i][j][N] = ans;
  }
}
