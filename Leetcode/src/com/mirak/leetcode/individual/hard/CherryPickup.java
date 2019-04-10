package com.mirak.leetcode.individual.hard;

import java.util.*;

public class CherryPickup {

  private final int INF = 1000000000;

  public int cherryPickup(int[][] grid) {
    int n = grid.length;
    int[][][] dp = new int[n][n][n];
    init(dp);
    return Math.max(solve(0, 0, 0, dp, grid, n), 0);
  }

  private int solve(int i1, int j1, int i2, int[][][] dp, int[][] grid, int n) {
    int j2 = i1 + j1 - i2;
    if (invalidCell(i1, j1, grid, n) || invalidCell(i2, j2, grid, n)) {
      return -INF;
    }
    if (sameCell(i1, j1, i2, j2) && i1 == n - 1 && j1 == n - 1) {
      return grid[i1][j1];
    }
    if (dp[i1][j1][i2] != -1) {
      return dp[i1][j1][i2];
    }
    if (dp[i2][j2][i1] != -1) {
      return dp[i2][j2][i1];
    }
    int count = sameCell(i1, j1, i2, j2) ? grid[i1][j1] : grid[i1][j1] + grid[i2][j2];
    int ans = -INF;
    ans = Math.max(ans, count + solve(i1 + 1, j1, i2 + 1, dp, grid, n));
    ans = Math.max(ans, count + solve(i1 + 1, j1, i2, dp, grid, n));
    ans = Math.max(ans, count + solve(i1, j1 + 1, i2 + 1, dp, grid, n));
    ans = Math.max(ans, count + solve(i1, j1 + 1, i2, dp, grid, n));
    return dp[i1][j1][i2] = dp[i2][j2][i1] = ans;
  }

  private boolean invalidCell(int i, int j, int[][] grid, int n) {
    return i < 0 || j < 0 || i == n || j == n || grid[i][j] == -1;
  }

  private void init(int[][][] dp) {

    for (int[][] ar : dp) {
      for (int[] row : ar) {
        Arrays.fill(row, -1);
      }
    }
  }

  private boolean sameCell(int i1, int j1, int i2, int j2) {
    return i1 == i2 && j1 == j2;
  }
}
