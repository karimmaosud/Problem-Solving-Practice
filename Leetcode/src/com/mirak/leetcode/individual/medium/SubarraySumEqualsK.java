package com.mirak.leetcode.individual.medium;

import java.util.*;

public class SubarraySumEqualsK {

  public int subarraySum(int[] nums, int k) {
    Map<Integer, Integer> count = new HashMap<>();
    int n = nums.length;
    int[] sum = new int[n];
    int ans = 0;
    for (int i = 0; i < n; i++) {
      sum[i] = nums[i];
      if (i - 1 >= 0) {
        sum[i] += sum[i - 1];
      }
      int diff = sum[i] - k;
      ans += count.getOrDefault(diff, 0);
      ans += (diff == 0 ? 1 : 0);
      count.put(sum[i], count.getOrDefault(sum[i], 0) + 1);
    }
    return ans;
  }

}
