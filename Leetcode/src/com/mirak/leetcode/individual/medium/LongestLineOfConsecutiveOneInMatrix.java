package com.mirak.leetcode.individual.medium;

public class LongestLineOfConsecutiveOneInMatrix {

  private int[] rowInc = {0, -1, -1, -1};
  private int[] colInc = {-1, -1, 0, 1};

  public int longestLine(int[][] M) {
    int n = M.length;
    if (n == 0) {
      return 0;
    }
    int m = M[0].length;
    int[][][] dp = new int[n][m][4];
    int ans = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (M[i][j] == 0) {
          continue;
        }
        for (int k = 0; k < rowInc.length; k++) {
          dp[i][j][k] = 1;
          ans = Math.max(ans, dp[i][j][k]);
          int i_ = i + rowInc[k];
          int j_ = j + colInc[k];
          if (i_ < 0 || j_ < 0 || i_ == n || j_ == m) {
            continue;
          }
          dp[i][j][k] = dp[i_][j_][k] + 1;
          ans = Math.max(ans, dp[i][j][k]);
        }
      }
    }
    return ans;
  }
}
