package com.mirak.leetcode.contests.individual;


public class BurstBalloons {
  public static int maxCoins(int[] nums){
    int[] numsCopy = new int[nums.length + 2];
    numsCopy[0] = numsCopy[numsCopy.length - 1] = 1;
    for(int i = 0; i < nums.length; i++) {
      numsCopy[i + 1] = nums[i];
    }
    int[][] dp = new int[numsCopy.length][numsCopy.length];
    for(int i = 0; i < dp.length; i++) {
      for(int j = 0; j < dp[i].length; j++) {
        dp[i][j] = -1;
      }
    }
    return solve(1, nums.length, numsCopy, dp);
  }

  private static int solve(int left, int right, int [] nums, int[][] dp){
    if(left > right) {
      return 0;
    }
    if(left == right) {
      return nums[left] * nums[left - 1] * nums[right + 1];
    }
    if(dp[left][right] != -1) {
      return dp[left][right];
    }

    int ans = 0;
    for(int i = left; i <= right; i++) {
      ans = Math.max(ans, nums[i] * nums[left - 1] * nums[right + 1] + solve(left, i - 1, nums, dp) + solve(i + 1, right, nums, dp));
    }
    dp[left][right] = ans;
    return ans;
  }
}