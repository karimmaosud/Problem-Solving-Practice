package com.mirak.leetcode.contests.individual.medium;

import java.util.HashMap;
import java.util.Map;

public class MaximumSizeSubarraySumEqualsK {
  public int maxSubArrayLen(int[] nums, int k) {
    Map<Long, Integer> sumIndex = new HashMap<>();
    long runnerSum = 0;
    for(int i = 0; i < nums.length; i++) {
      runnerSum += nums[i];
      sumIndex.putIfAbsent(runnerSum, i);
    }
    runnerSum = 0;
    int res = 0;
    for(int i = 0; i < nums.length; i++) {
      runnerSum += nums[i];
      if(runnerSum == k) {
        res = Math.max(res, i + 1);
        continue;
      }
      long lookup = runnerSum - k;
      if(!sumIndex.containsKey(lookup) || sumIndex.get(lookup) >= i){
        continue;
      }
      res = Math.max(res, i - sumIndex.get(lookup));
    }
    return res;
  }
}
