package com.mirak.leetcode.individual.hard;

public class DistinctSubsequences {

  public static int numDistinct(String s, String t) {
    if (s.length() == 0) {
      return t.length() == 0 ? 1 : 0;
    }

    int[][] dp = new int[t.length()][s.length()];
    for (int i = 0; i < t.length(); ++i) {
      for (int j = 0; j < s.length(); ++j) {

        if (j >= i && t.charAt(i) == s.charAt(j)) {
          dp[i][j] = i - 1 >= 0 ? dp[i - 1][j - 1] : 1;
        }

        if (j - 1 >= 0) {
          dp[i][j] += dp[i][j - 1];
        }
      }
    }
    return dp[t.length() - 1][s.length() - 1];
  }
}
