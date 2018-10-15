package com.mirak.leetcode.individual.hard;

public class BestTimeToBuyAndSellStockIII {

  public int maxProfit(int[] prices) {
    int n = prices.length;
    if (n <= 1) {
      return 0;
    }
    int[] maxToRight = new int[n];
    int max = prices[n - 1];
    int min = prices[n - 2];
    int rightToLeftMax = 0;
    for (int i = n - 2; i >= 0; i--) {
      min = Math.min(min, prices[i]);
      rightToLeftMax = Math.max(rightToLeftMax, max - min);
      maxToRight[i] = rightToLeftMax;
      if (prices[i] > max) {
        max = min = prices[i];
      }
    }
    int res = rightToLeftMax;
    min = prices[0];
    max = prices[1];
    int leftToRightMax = 0;
    for (int i = 1; i < n; i++) {
      max = Math.max(max, prices[i]);
      leftToRightMax = Math.max(leftToRightMax, max - min);
      if (i + 1 < n) {
        res = Math.max(res, leftToRightMax + maxToRight[i + 1]);
      }
      if (prices[i] < min) {
        min = max = prices[i];
      }
    }
    return res;
  }
}
