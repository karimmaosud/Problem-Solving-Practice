package com.mirak.leetcode.individual.hard;

public class BestTimeToBuyAndSellStockIV {

  public int maxProfit(int k, int[] prices) {
    int n = prices.length;
    if (n == 0) {
      return 0;
    }
    int[][] dp = new int[2][n];
    k = Math.min(k, n / 2);
    for (int k_ = 1; k_ <= k; k_++) {
      int idx = k_ & 1;
      int min = prices[0];
      for (int i = 1; i < n; i++) {
        dp[idx][i] = Math.max(dp[idx][i - 1], prices[i] - min);
        min = Math.min(min, prices[i] - dp[idx ^ 1][i - 1]);
      }
      if (dp[idx][n - 1] == dp[idx ^ 1][n - 1]) {
        return dp[idx][n - 1];
      }
    }
    return dp[k & 1][n - 1];
  }
}
