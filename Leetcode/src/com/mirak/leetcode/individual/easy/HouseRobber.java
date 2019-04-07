package com.mirak.leetcode.individual.easy;

public class HouseRobber {

  public int rob(int[] nums) {
    int n = nums.length;
    if (n == 0) {
      return 0;
    }
    int[] dp = new int[n];
    for (int i = 0; i < n; ++i) {
      dp[i] = nums[i];
      if (i > 0) {
        dp[i] = Math.max(dp[i], dp[i - 1]);
      }
      if (i - 2 >= 0) {
        dp[i] = Math.max(dp[i], nums[i] + dp[i - 2]);
      }
    }
    return dp[n - 1];
  }

}
