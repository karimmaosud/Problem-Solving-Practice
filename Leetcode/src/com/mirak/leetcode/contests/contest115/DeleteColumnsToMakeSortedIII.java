package com.mirak.leetcode.contests.contest115;

import java.util.*;

public class DeleteColumnsToMakeSortedIII {

  private final int OFFSET = 101;
  public int minDeletionSize(String[] A) {
    int[][] dp = new int[205][100];
    for (int i = 0; i < dp.length; i++) {
      Arrays.fill(dp[i], -1);
    }
    return solve(-1, 0, A, dp);
  }

  private int solve (int prevColumn, int currentColumn, String[] A, int[][] dp) {
    if (currentColumn == A[0].length()) {
      return 0;
    }

    if (dp[prevColumn + OFFSET][currentColumn] != -1) {
      return dp[prevColumn + OFFSET][currentColumn];
    }

    int ans = 1 + solve(prevColumn, currentColumn + 1, A, dp);

    if (canKeep(prevColumn, currentColumn, A)) {
      ans = Math.min(ans, solve(currentColumn, currentColumn + 1, A, dp));
    }

    return dp[prevColumn + OFFSET][currentColumn] = ans;
  }

  private boolean canKeep(int prevColumn, int currentColumn, String[] A) {

    if (prevColumn == -1) {
      return true;
    }

    for (int i = 0; i < A.length; i++) {
      if (A[i].charAt(currentColumn) < A[i].charAt(prevColumn)) {
        return false;
      }
    }
    return true;
  }
}
