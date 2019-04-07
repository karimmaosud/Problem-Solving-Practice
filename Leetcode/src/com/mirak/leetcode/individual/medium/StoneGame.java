package com.mirak.leetcode.individual.medium;

public class StoneGame {

  public boolean stoneGame(int[] piles) {
    int n = piles.length;
    int[][] dp = new int[n][n];
    for (int l = 1; l <= n; ++l) {
      for (int i = 0; i + l <= n; ++i) {
        int j = i + l - 1;
        if (i == j) {
          dp[i][j] = -piles[i];
          continue;
        }
        if ((l & 1) == 1) {
          // odd, other player's turn.
          dp[i][j] = Math.min(-piles[i] + dp[i + 1][j], -piles[j] + dp[i][j - 1]);
        } else {
          dp[i][j] = Math.max(piles[i] + dp[i + 1][j], piles[j] + dp[i][j - 1]);
        }
      }
    }
    return dp[0][n - 1] > 0;
  }
}
