package com.mirak.leetcode.individual.medium;

import java.util.*;

public class MinimumSwapsToMakeSequencesIncreasing {

  private final int INF = 1000000000;

  public int minSwap(int[] A, int[] B) {
    int n = A.length;
    int[][] dp = new int[n][2];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    return solve(A, B, 0, 0, dp);
  }

  private int solve(int[] A, int[] B, int i, int prevSwapped, int[][] dp) {
    if (i == A.length) {
      return 0;
    }

    if (dp[i][prevSwapped] != -1) {
      return dp[i][prevSwapped];
    }

    int ans = INF;

    // 1. Swap
    swap(A, B, i);
    if (compare(A, B, i)) {
      ans = 1 + solve(A, B, i + 1, 1, dp);
    }
    // Swap back.
    swap(A, B, i);
    if (compare(A, B, i)) {
      ans = Math.min(ans, solve(A, B, i + 1, 0, dp));
    }

    return dp[i][prevSwapped] = ans;
  }

  private void swap(int[] A, int[] B, int i) {
    int temp = A[i];
    A[i] = B[i];
    B[i] = temp;
  }


  private boolean compare(int[] A, int[] B, int i) {
    return i == 0 || A[i] > A[i - 1] && B[i] > B[i - 1];
  }

}
