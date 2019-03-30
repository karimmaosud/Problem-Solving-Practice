package com.mirak.leetcode.individual.medium;

public class MaximumProductSubarray {

  public int maxProduct(int[] nums) {
    long minNeg = 0, maxPos = 0, ans = 0;
    if (nums.length == 0) {
      return 0;
    }
    if (nums.length == 1) {
      return nums[0];
    }
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] == 0) {
        minNeg = maxPos = 0;
        continue;
      }
      if (nums[i] < 0) {
        long saved = maxPos;
        maxPos = minNeg * nums[i];
        minNeg = Math.min(nums[i], saved * nums[i]);
      } else {
        minNeg = minNeg * nums[i];
        maxPos = Math.max(nums[i], maxPos * nums[i]);
      }
      ans = Math.max(ans, maxPos);
    }
    return (int) ans;
  }
}
