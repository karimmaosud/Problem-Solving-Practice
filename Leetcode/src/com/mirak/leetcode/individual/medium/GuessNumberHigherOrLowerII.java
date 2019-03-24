package com.mirak.leetcode.individual.medium;

import java.util.*;

public class GuessNumberHigherOrLowerII {

  private final int INF = 1000000000;

  public int getMoneyAmount(int n) {
    int[][] dp = new int[n + 1][n + 1];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    return solve(1, n, dp);
  }

  private int solve(int left, int right, int[][] dp) {
    if (left >= right) {
      return 0;
    }
    if (dp[left][right] != -1) {
      return dp[left][right];
    }

    int ans = INF;
    int start = Math.max((left + (right - left) / 2) - 1, left);
    for (int i = start; i <= right; i++) {
      ans = Math.min(ans, i + Math.max(solve(left, i - 1, dp), solve(i + 1, right, dp)));
    }
    return dp[left][right] = ans;
  }


}
