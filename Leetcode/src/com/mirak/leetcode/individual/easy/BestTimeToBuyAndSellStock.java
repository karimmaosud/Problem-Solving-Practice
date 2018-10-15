package com.mirak.leetcode.individual.easy;

public class BestTimeToBuyAndSellStock {

  public int maxProfit(int[] prices) {
    int n = prices.length;
    if (n <= 1) {
      return 0;
    }
    int max = prices[n - 1];
    int min = prices[n - 2];
    int res = 0;
    for (int i = n - 2; i >= 0; i--) {
      min = Math.min(min, prices[i]);
      res = Math.max(res, max - min);
      if (prices[i] > max) {
        max = min = prices[i];
      }
    }
    return res;
  }
}