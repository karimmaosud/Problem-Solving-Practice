package com.mirak.leetcode.individual.hard;

import java.util.*;

public class SplitArrayWithSameAverage {

  public boolean splitArraySameAverage(int[] A) {
    return meetInTheMiddleSolution(A);
  }

  private boolean dpSolution(int[] A) {
    int n = A.length;
    if (n <= 1) {
      return false;
    }
    int totalSum = 0;
    for (int num : A) {
      totalSum += num;
    }

    boolean[][][] dp = new boolean[n][n][totalSum];
    boolean[][][] passed = new boolean[n][n][totalSum];
    for (int i = 1; i < n; i++) {
      if ((totalSum * i) % n == 0) {
        int sum = (totalSum * i) / n;
        if (solve(0, i, sum, A, dp, passed)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean solve(int idx, int rem, int sum, int[] A, boolean[][][] dp,
      boolean[][][] passed) {

    if (rem == 0) {
      return sum == 0;
    }

    if (sum <= 0 || idx == A.length) {
      return false;
    }

    if (passed[idx][rem][sum]) {
      return dp[idx][rem][sum];
    }

    passed[idx][rem][sum] = true;

    if (solve(idx + 1, rem - 1, sum - A[idx], A, dp, passed)) {
      return dp[idx][rem][sum] = true;
    }
    return dp[idx][rem][sum] = solve(idx + 1, rem, sum, A, dp, passed);
  }

  private boolean meetInTheMiddleSolution(int[] A) {
    int n = A.length;
    if (n <= 1) {
      return false;
    }

    int totalSum = getTotalSum(A);
    Map<Integer, Integer> sumCount = new HashMap<>();
    if (solve(A, 0, sumCount, true, totalSum)) {
      return true;
    }
    return solve(A, A.length / 2, sumCount, false, totalSum);
  }

  private int getTotalSum(int[] A) {
    int totalSum = 0;
    for (int num : A) {
      totalSum += num;
    }
    return totalSum;
  }


  private boolean solve(int[] A, int offset, Map<Integer, Integer> sumCount, boolean addToMap,
      int totalSum) {

    int N = A.length;

    int n = addToMap ? N / 2 : N - (N / 2);

    for (int i = 1; i < 1 << n; i++) {

      int partialSum = getPartialSum(A, i, offset, N, totalSum);

      if (partialSum == 0) {
        return true;
      }

      if (sumCount.containsKey(-partialSum)) {
        if (i == (1 << n) - 1) {
          return sumCount.get(-partialSum) > 1 || -partialSum != getPartialSum(A,
              (1 << (N / 2)) - 1,
              0, N, totalSum);
        }
        return true;
      }

      if (addToMap) {
        sumCount.put(partialSum, sumCount.getOrDefault(partialSum, 0) + 1);
      }
    }
    return false;
  }

  private int getPartialSum(int[] A, int mask, int offset, int N, int totalSum) {
    int chosen = 0;
    int partialSum = 0;
    int idx = 0;
    while (mask > 0) {
      if ((mask & 1) == 1) {
        chosen++;
        partialSum += A[offset + idx];
      }
      idx++;
      mask >>= 1;
    }
    return N * partialSum - chosen * totalSum;
  }
}
