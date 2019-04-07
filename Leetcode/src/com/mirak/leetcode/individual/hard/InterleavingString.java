package com.mirak.leetcode.individual.hard;

public class InterleavingString {

  public boolean isInterleave(String s1, String s2, String s3) {
    int n = s1.length();
    int m = s2.length();
    if (n + m != s3.length()) {
      return false;
    }
    boolean[][] dp = new boolean[n + 1][m + 1];
    dp[0][0] = true;
    for (int i = 0; i <= n; ++i) {
      for (int j = 0; j <= m; ++j) {
        if (i == 0 && j == 0) {
          continue;
        }
        if (i > 0 && s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
          dp[i][j] |= dp[i - 1][j];
        }
        if (j > 0 && s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
          dp[i][j] |= dp[i][j - 1];
        }
      }
    }
    return dp[n][m];
  }

  public boolean isInterleave1D_DP(String s1, String s2, String s3) {
    int n = s1.length();
    int m = s2.length();
    if (n + m != s3.length()) {
      return false;
    }
    boolean[] dp = new boolean[m + 1];
    dp[0] = true;
    for (int i = 0; i <= n; ++i) {
      for (int j = 0; j <= m; ++j) {
        if (i == 0 && j == 0) {
          continue;
        }
        if (i == 0) {
          // j is not 0.
          dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
        } else {
          // i is not 0.
          dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
          if (j > 0) {
            dp[j] |= (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
          }
        }
      }
    }
    return dp[m];
  }
}
