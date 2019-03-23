package com.mirak.leetcode.individual.hard;

import java.util.*;


public class UniquePathsIII {

  private static int[] rowInc = {1, -1, 0, 0};
  private static int[] colInc = {0, 0, 1, -1};

  public static int uniquePathsIII(int[][] grid) {
    int n = grid.length;
    if (n == 0) {
      return 0;
    }
    int m = grid[0].length;
    int[][] dp = new int[1 << (n * m)][n * m];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }

    int start = getStart(grid, n, m);
    return solve(getInitialMask(grid, n, m) | (1 << start), start, grid, n, m, dp);
  }

  private static int getStart(int[][] grid, int n, int m) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == 1) {
          return i * m + j;
        }
      }
    }
    return 0;
  }

  private static int getInitialMask(int[][] grid, int n, int m) {
    int mask = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == -1) {
          int cell = i * m + j;
          mask |= (1 << cell);
        }
      }
    }
    return mask;
  }

  private static int solve(int mask, int cell, int[][] grid, int n, int m, int[][] dp) {

    int i = cell / m;
    int j = cell % m;

    if (grid[i][j] == 2) {
      return mask == (1 << (n * m)) - 1 ? 1 : 0;
    }
    if (dp[mask][cell] != -1) {
      return dp[mask][cell];
    }
    int ans = 0;
    for (int k = 0; k < rowInc.length; k++) {
      int i_ = i + rowInc[k];
      int j_ = j + colInc[k];
      if (i_ < 0 || j_ < 0 || i_ == n || j_ == m) {
        continue;
      }
      int nextCell = i_ * m + j_;
      if ((mask & (1 << nextCell)) != 0) {
        continue;
      }
      ans += solve(mask | (1 << nextCell), nextCell, grid, n, m, dp);
    }
    return dp[mask][cell] = ans;
  }

  public static void main(String[] args) {
    System.out.println(uniquePathsIII(new int[][] {{1,0},{0,2}}));
  }

}
