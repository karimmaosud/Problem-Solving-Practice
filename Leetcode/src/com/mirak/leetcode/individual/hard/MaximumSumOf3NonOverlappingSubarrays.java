package com.mirak.leetcode.individual.hard;

import java.util.*;

public class MaximumSumOf3NonOverlappingSubarrays {

  public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
    int n = nums.length;
    int[] ar = new int[n - k + 1];
    init(nums, ar, k);
    int[][] dp = new int[4][ar.length];
    int[][] parent = new int[4][ar.length];

    for (int[] row : parent) {
      Arrays.fill(row, -1);
    }

    for (int i = 1; i <= 3; i++) {
      for (int j = (i - 1) * k; j < ar.length; j++) {

        dp[i][j] = dp[i][Math.max(0, j - 1)];
        parent[i][j] = parent[i][Math.max(0, j - 1)];

        if (ar[j] + dp[i - 1][Math.max(0, j - k)] > dp[i][j]) {
          dp[i][j] = ar[j] + dp[i - 1][Math.max(0, j - k)];
          parent[i][j] = j;
        }
      }
    }

    int[] res = new int[3];
    int p = parent[3][ar.length - 1];
    for (int i = 3; i > 0; i--) {
      res[i - 1] = p;
      if (i > 1) {
        p = parent[i - 1][p - k];
      }
    }
    return res;
  }

  private void init(int[] nums, int[] ar, int k) {
    int n = nums.length;
    for (int i = 0; i < k; i++) {
      ar[0] += nums[i];
    }
    for (int i = k; i < n; i++) {
      ar[i - k + 1] = ar[i - k] + nums[i] - nums[i - k];
    }
  }
}
