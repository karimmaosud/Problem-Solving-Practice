package com.mirak.leetcode.individual.medium;

import java.util.*;

public class MaximalSquare {

  public int maximalSquare(char[][] matrix) {
    int n = matrix.length;
    if (n == 0) {
      return 0;
    }
    int m = matrix[0].length;
    int[][] dp = new int[2][m];
    int res = 0;
    for (int i = 0; i < n; ++i) {
      int idx = i & 1;
      Arrays.fill(dp[idx], 0);
      for (int j = 0; j < m; ++j) {
        if (matrix[i][j] == '0') {
          continue;
        }
        dp[idx][j] = 1;
        if (i > 0 && j > 0) {
          dp[idx][j] = Math.min(dp[idx ^ 1][j], Math.min(dp[idx][j - 1], dp[idx ^ 1][j - 1])) + 1;
        }
        res = Math.max(res, dp[idx][j] * dp[idx][j]);
      }
    }
    return res;
  }

}
