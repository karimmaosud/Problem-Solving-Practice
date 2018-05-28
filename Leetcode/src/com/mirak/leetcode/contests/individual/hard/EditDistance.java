package com.mirak.leetcode.contests.individual.hard;

public class EditDistance {
  public int minDistance(String word1, String word2) {
    int[][] dp = new int[word1.length()][word2.length()];
    initDp(dp);
    return solve(0, 0, word1, word2, dp);
  }

  private void initDp(int[][] dp) {
    for(int i = 0; i < dp.length; i++) {
      for(int j = 0; j < dp[i].length; j++) {
        dp[i][j] = -1;
      }
    }
  }

  private int solve(int ps, int pt, String s, String t, int[][] dp) {
    if(ps == s.length() || pt == t.length()) {
      // base case.
      return Math.max(s.length() - ps, t.length() - pt);
    }

    if(dp[ps][pt] != -1) {
      return dp[ps][pt];
    }

    int add = s.charAt(ps) == t.charAt(pt)? 0: 1;
    int matchBoth = add + solve(ps + 1, pt + 1, s, t, dp);
    int insert = 1 + solve(ps, pt + 1, s, t, dp);
    int delete = 1 + solve(ps + 1, pt, s, t, dp);
    dp[ps][pt] = Math.min(matchBoth, Math.min(insert, delete));
    return dp[ps][pt];
  }
}
