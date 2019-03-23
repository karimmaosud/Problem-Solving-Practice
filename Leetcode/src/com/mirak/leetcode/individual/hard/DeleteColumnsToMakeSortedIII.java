package com.mirak.leetcode.individual.hard;

import java.util.*;

public class DeleteColumnsToMakeSortedIII {

  public int minDeletionSize(String[] A) {
    if (A.length == 0) {
      return 0;
    }
    int n = A[0].length();
    int[][] dp = new int[n + 1][n];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    return solve(n, 0, A, dp);
  }

  private int solve(int prevIndex, int currentIndex, String[] A, int[][] dp) {
    if (currentIndex == A[0].length()) {
      return 0;
    }
    if (dp[prevIndex][currentIndex] != -1) {
      return dp[prevIndex][currentIndex];
    }
    int ans = Integer.MAX_VALUE;
    if (canTakeColumn(prevIndex, currentIndex, A)) {
      ans = Math.min(ans, solve(currentIndex, currentIndex + 1, A, dp));
    }
    return dp[prevIndex][currentIndex] = Math
        .min(ans, 1 + solve(prevIndex, currentIndex + 1, A, dp));
  }

  private boolean canTakeColumn(int prevIndex, int currentIndex, String[] A) {
    if (prevIndex == A[0].length()) {
      return true;
    }
    for (int i = 0; i < A.length; i++) {
      if (A[i].charAt(prevIndex) > A[i].charAt(currentIndex)) {
        return false;
      }
    }
    return true;
  }


}
