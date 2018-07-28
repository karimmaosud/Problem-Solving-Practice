package com.mirak.leetcode.individual.hard;

public class MaximumAverageSubarrayII {

  public double findMaxAverage(int[] nums, int k) {

    if (nums.length == 0) {
      return 0.0;
    }
    return binarySearch(nums, k);
  }

  private double binarySearch(int[] nums, int k) {
    double low = -10000;
    double high = 10000;
    while (high - low > 1e-5) {
      double mid = (low + high) / 2;
      if (averageOrHigher(nums, k, mid)) {
        low = mid + 1e-5;
      } else {
        high = mid - 1e-5;
      }
    }
    return high;
  }

  private boolean averageOrHigher(int[] nums, int k, double minAverage) {
    double runnerSum = 0;
    for (int i = 0; i < k; i++) {
      runnerSum += (nums[i] - minAverage);
    }
    if (runnerSum >= 0) {
      return true;
    }
    double minSum = nums[0] - minAverage;
    double prevSum = minSum;
    for (int i = k; i < nums.length; i++) {
      runnerSum += (nums[i] - minAverage);
      if (runnerSum >= 0 || runnerSum - minSum >= 0) {
        return true;
      }
      prevSum += nums[i - k + 1] - minAverage;
      minSum = Math.min(minSum, prevSum);
    }
    return false;
  }
}
