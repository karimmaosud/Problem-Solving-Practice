package com.mirak.leetcode.contests.contest129;

public class BestSightseeingPair {
  public int maxScoreSightseeingPair(int[] A) {
    int n = A.length;
    if (n <= 1) {
      return 0;
    }
    int max = A[0];
    int res = 0;
    for (int i = 1; i < n; i++) {
      res = Math.max(res, A[i] - i + max);
      max = Math.max(max, A[i] + i);
    }
    return res;
  }
}
