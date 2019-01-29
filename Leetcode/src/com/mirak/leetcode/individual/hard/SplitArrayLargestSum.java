package com.mirak.leetcode.individual.hard;

public class SplitArrayLargestSum {

  private class DpSolution {

    public int splitArray(int[] nums, int m) {
      int n = nums.length;
      long[][] mem = new long[2][n];
      // init mem with m = 1.
      mem[1][n - 1] = nums[n - 1];
      for (int i = n - 2; i >= 0; i--) {
        mem[1][i] = nums[i] + mem[1][i + 1];
      }

      for (int j = 2; j <= m; j++) {
        for (int i = n - 1; i >= 0; i--) {
          mem[j % 2][i] = Long.MAX_VALUE;
          if (n - i < j) {
            continue;
          }
          long sum = nums[i];
          for (int k = i + 1; k < n; k++) {
            if (n - k < j - 1) {
              break;
            }
            mem[j % 2][i] = Math.min(mem[j % 2][i], Math.max(sum, mem[(j + 1) % 2][k]));
            sum += nums[k];
          }
        }
      }
      return (int) mem[m % 2][0];
    }
  }

  private class BinarySearchTheAnswerSolution {

    public int splitArray(int[] nums, int m) {
      long arraySum = 0;
      for (int num : nums) {
        arraySum += num;
      }
      long low = 0;
      long high = arraySum;
      if (m == 1) {
        return (int) arraySum;
      }
      while (low <= high) {
        long mid = low + (high - low) / 2;
        if (canSplit(nums, mid, m, arraySum)) {
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      }
      return (int) low;
    }

    private boolean canSplit(int[] nums, long maxSum, int m, long arraySum) {
      long runningSum = 0;
      m--;
      for (int num : nums) {
        if (m < 0 || num > maxSum) {
          return false;
        }
        if (m == 0) {
          return runningSum + arraySum <= maxSum;
        }
        arraySum -= num;
        runningSum += num;
        if (arraySum <= maxSum && runningSum <= maxSum) {
          return true;
        }
        if (runningSum > maxSum) {
          runningSum = num;
          m--;
        }
      }
      return m >= 0 && runningSum <= maxSum && arraySum <= maxSum;
    }
  }
}
