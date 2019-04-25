package com.mirak.leetcode.contests.contest133;

import java.util.Arrays;

public class MaximumSumOfTwoNonOverlappingSubarrays {

  private static final int INF = 1000000000;

  public static int maxSumTwoNoOverlap(int[] A, int L, int M) {
    int n = A.length;
    int[] lMaxSum = new int[n];
    int[] rMaxSum = new int[n];

    Arrays.fill(lMaxSum, -INF);
    Arrays.fill(rMaxSum, -INF);

    init(A, lMaxSum, 0, 1, L);
    init(A, rMaxSum, n - 1, -1, L);

    int[] runnerSum = new int[n];
    int max = -INF;

    for (int i = 0; i < n; ++i) {
      runnerSum[i] = A[i] + (i - 1 >= 0 ? runnerSum[i - 1] : 0);

      if (i == M - 1) {
        max = Math.max(max, runnerSum[i] + rMaxSum[i + 1]);
      } else if (i - M >= 0) {
        int sum = runnerSum[i] - runnerSum[i - M];
        max = Math.max(max, sum + lMaxSum[i - M]);
        if (i + 1 < n) {
          max = Math.max(max, sum + rMaxSum[i + 1]);
        }
      }
    }
    return max;
  }

  private static void init(int[] A, int[] maxSum, int start, int inc, int L) {
    int n = A.length;
    int[] runnerSum = new int[n];
    for (int i = start; i >= 0 && i < n; i += inc) {
      runnerSum[i] = A[i];
      if (i - inc >= 0 && i - inc < n) {
        runnerSum[i] += runnerSum[i - inc];
      }
      if (i - inc * L == -1 || i - inc * L == n) {
        maxSum[i] = runnerSum[i];
      }
      if (i - inc * L >= 0 && i - inc * L < n) {
        int sum = runnerSum[i] - runnerSum[i - inc * L];
        maxSum[i] = Math.max(sum, maxSum[i - inc]);
      }
    }
  }

  public static void main(String[] args) {

  }
}
