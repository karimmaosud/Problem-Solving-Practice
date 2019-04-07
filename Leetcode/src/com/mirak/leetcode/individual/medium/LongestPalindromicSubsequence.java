package com.mirak.leetcode.individual.medium;

public class LongestPalindromicSubsequence {

  public int longestPalindromeSubseq(String s) {
    int n = s.length();
    String s2 = new StringBuilder(s).reverse().toString();
    int[][] dp = new int[n + 1][n + 1];
    for (int i = n - 1; i >= 0; --i) {
      for (int j = n - 1; j >= 0; --j) {
        if (s.charAt(i) == s2.charAt(j)) {
          dp[i][j] = 1 + dp[i + 1][j + 1];
        } else {
          dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
        }
      }
    }
    return dp[0][0];
  }

}
