package com.mirak.leetcode.individual.medium;

public class BestTimeToBuyAndSellStockWithCooldown {

  public int maxProfit(int[] prices) {
    int n = prices.length;
    if (n == 0) {
      return 0;
    }
    int[] dp = new int[n];
    int min = prices[0];
    for (int i = 1; i < n; ++i) {
      dp[i] = Math.max(prices[i] - min, dp[i - 1]);
      min = Math.min(min, prices[i] - (i - 2 >= 0 ? dp[i - 2] : 0));
    }
    return dp[n - 1];
  }
}
