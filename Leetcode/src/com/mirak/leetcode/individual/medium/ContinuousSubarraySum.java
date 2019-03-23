package com.mirak.leetcode.individual.medium;

import java.util.*;

public class ContinuousSubarraySum {

  public boolean checkSubarraySum(int[] nums, int k) {
    if (k == 0) {
      return passedZeroCase(nums);
    }
    Map<Integer, Integer> index = new HashMap<>();
    int prevSum = 0;
    for (int i = 0; i < nums.length; i++) {
      int sum = (nums[i] + prevSum) % k;
      if (sum == 0 && i > 0) {
        return true;
      }
      if (index.containsKey(sum) && i - index.get(sum) >= 2) {
        return true;
      }
      if (!index.containsKey(sum)) {
        index.put(sum, i);
      }
      prevSum = sum;
    }
    return false;
  }

  private boolean passedZeroCase(int[] nums) {
    for (int i = 0; i < nums.length - 1; i++) {
      if (nums[i] == 0 && nums[i + 1] == 0) {
        return true;
      }
    }
    return false;
  }
}
