package com.mirak.leetcode.individual.hard;

import java.util.Arrays;

public class FindKthSmallestPairDistance {

  public int smallestDistancePair(int[] nums, int k) {

    Arrays.sort(nums);
    int low = 0;
    int high = 1000000;
    while (low <= high) {
      int mid = (low + high) / 2;
      int count = getCount(nums, mid);
      if (count >= k) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }

  private int getCount(int[] nums, int max) {
    int right = nums.length - 1;
    int left = right;
    int count = 0;
    while (right > 0) {
      while (left >= 0 && nums[right] - nums[left] <= max) {
        left--;
      }
      count += (right - left - 1);
      right--;
    }
    return count;
  }
}
