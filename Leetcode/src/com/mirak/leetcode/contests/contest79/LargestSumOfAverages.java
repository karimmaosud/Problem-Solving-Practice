package com.mirak.leetcode.contests.contest79;

public class LargestSumOfAverages {
  private double inf = 1000000000000000.0;

  public double largestSumOfAverages(int[] A, int K) {
    double[][] dp = new double[A.length][K + 1];
    int[] sum = new int[A.length];
    initDp(dp);
    initSum(A, sum);
    return solve(0, A, sum, dp, K);
  }

  private void initDp(double[][] dp) {
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp[i].length; j++) {
        dp[i][j] = -1.0;
      }
    }
  }

  private void initSum(int[] A, int[] sum) {
    sum[0] = A[0];
    for (int i = 1; i < A.length; i++) {
      sum[i] = A[i] + sum[i - 1];
    }
  }

  private double solve(int idx, int[] A, int[] sum, double[][] dp, int k) {

    if (k == 1) {
      if (idx < A.length) {
        int n = A.length - idx;
        int rangeSum = sum[A.length - 1];
        if (idx > 0) {
          rangeSum -= sum[idx - 1];
        }
        return (1.0 * rangeSum) / n;
      }
      return -inf;
    }

    if (idx == A.length) {
      return -inf;
    }

    if (dp[idx][k] != -1.0) {
      return dp[idx][k];
    }

    double ans = 0.0;
    int n;
    int rangeSum = idx > 0 ? -sum[idx - 1] : 0;
    for (int i = idx; i < A.length; i++) {
      n = i - idx + 1;
      rangeSum += sum[i];
      double avg = (1.0 * rangeSum) / n;
      ans = Math.max(ans, avg + solve(i + 1, A, sum, dp, k - 1));
      rangeSum -= sum[i];
    }
    return dp[idx][k] = ans;
  }
}
