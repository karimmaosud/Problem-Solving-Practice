package com.mirak.leetcode.individual.hard;

public class MaximumAverageSubarrayII {

  public double findMaxAverage(int[] nums, int k) {
    double low = -100000;
    double high = 100000;
    while (Math.abs(low - high) > 1e-5) {
      double mid = low + (high - low) / 2;
      if (canObtainAverage(nums, mid, k)) {
        low = mid;
      } else {
        high = mid;
      }
    }
    return low;
  }

  private boolean canObtainAverage(int[] nums, double avg, int k) {
    double[] ar = new double[nums.length];
    for (int i = 0; i < nums.length; ++i) {
      ar[i] = nums[i] - avg;
    }
    double min = 100000000000000.0;
    double sum = 0;
    double prefixSum = 0.0;
    for (int i = 0; i < ar.length; ++i) {
      sum += ar[i];
      if (i >= k - 1 && (sum >= 0 || sum - min >= 0)) {
        return true;
      }
      if (i - k + 1 >= 0) {
        prefixSum += ar[i - k + 1];
        min = Math.min(min, prefixSum);
      }
    }
    return false;
  }
}
