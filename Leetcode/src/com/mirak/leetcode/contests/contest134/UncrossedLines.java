package com.mirak.leetcode.contests.contest134;

import java.util.*;

public class UncrossedLines {

  public static int maxUncrossedLines(int[] A, int[] B) {
    int n = A.length, m = B.length;
    int[][] dp = new int[2][m];
    for (int i = 0; i < n; ++i) {
      int idx = i & 1;
      Arrays.fill(dp[idx], 0);
      for (int j = 0; j < m; ++j) {
        if (j > 0) {
          dp[idx][j] = Math.max(dp[idx][j], dp[idx][j - 1]);
        }
        if (i > 0) {
          dp[idx][j] = Math.max(dp[idx][j], dp[idx ^ 1][j]);
        }
        if (A[i] == B[j]) {
          dp[idx][j] = Math.max(dp[idx][j], 1 + (i > 0 && j > 0 ? dp[idx ^ 1][j - 1] : 0));
        }
      }
    }
    return dp[(n - 1) & 1][m - 1];
  }
}
