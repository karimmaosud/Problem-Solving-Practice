package com.mirak.leetcode.individual.hard;

import java.util.*;

public class DungeonGame {

  private static final int MAX = 100000000;

  public int calculateMinimumHP(int[][] dungeon) {
    int n = dungeon.length;
    int m = dungeon[0].length;
    int[][] dp = new int[n][m];
    for (int i = 0; i < n; i++) {
      Arrays.fill(dp[i], MAX);
    }
    for (int i = n - 1; i >= 0; i--) {
      for (int j = m - 1; j >= 0; j--) {
        if (i + 1 < n) {
          dp[i][j] = Math.min(dp[i][j], Math.max(0, -1 * dungeon[i][j] + dp[i + 1][j]));
        }
        if (j + 1 < m) {
          dp[i][j] = Math.min(dp[i][j], Math.max(0, -1 * dungeon[i][j] + dp[i][j + 1]));
        }
        if (dp[i][j] == MAX || dp[i][j] + dungeon[i][j] <= 0) {
          dp[i][j] = Math.max(1, -1 * dungeon[i][j] + 1);
        }
      }
    }
    return Math.max(1, dp[0][0]);
  }
}
