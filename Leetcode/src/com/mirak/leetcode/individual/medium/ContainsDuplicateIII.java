package com.mirak.leetcode.individual.medium;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicateIII {
  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

    if(t < 0) {
      return false;
    }

    Map<Long, Long> buckets = new HashMap<>();
    for(int i = 0; i < nums.length; i++) {

      if(buckets.size() == k + 1) {
        long deleteBucket = getBucket(nums[i - k - 1], t);
        buckets.remove(deleteBucket);
      }

      long newBucket = getBucket(nums[i], t);

      if(buckets.containsKey(newBucket)) {
        return true;
      }

      if(buckets.containsKey(newBucket + 1) && buckets.get(newBucket + 1) - nums[i] <= t) {
        return true;
      }

      if(buckets.containsKey(newBucket - 1) && (long) nums[i] - buckets.get(newBucket - 1) <= t) {
        return true;
      }

      buckets.put(newBucket, (long) nums[i]);
    }
    return false;
  }

  private long getBucket(int num, int t) {
    if(num >= 0) {
      return (long) num / (t + 1);
    }else {
      return (-1 * (Math.abs((long) num) / (t + 1)) - 1);
    }
  }
}
