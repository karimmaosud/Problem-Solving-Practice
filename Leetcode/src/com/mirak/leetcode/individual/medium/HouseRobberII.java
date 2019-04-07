package com.mirak.leetcode.individual.medium;

public class HouseRobberII {

  public int rob(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    if (nums.length == 1) {
      return nums[0];
    }
    return Math.max(rob(0, nums), rob(1, nums));
  }

  private int rob(int start, int[] nums) {
    int n = nums.length;

    int[] dp = new int[n];
    for (int i = start; i < n - (start ^ 1); ++i) {
      dp[i] = nums[i];
      if (i > 0) {
        dp[i] = Math.max(dp[i], dp[i - 1]);
      }
      if (i - 2 >= 0) {
        dp[i] = Math.max(dp[i], nums[i] + dp[i - 2]);
      }
    }
    return dp[n - 1 - (start ^ 1)];
  }
}
