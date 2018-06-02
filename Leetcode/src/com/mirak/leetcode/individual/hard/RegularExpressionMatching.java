package com.mirak.leetcode.individual.hard;

public class RegularExpressionMatching {

  public boolean isMatch(String s, String p) {
    int[][] dp = new int[s.length()][p.length()];
    initDp(dp);
    int ans = solve(0, 0, s, p, dp);
    return ans == 1;
  }

  private void initDp(int[][] dp) {
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp[i].length; j++) {
        dp[i][j] = -1;
      }
    }
  }

  private int solve(int sIdx, int pIdx, String s, String p, int[][] dp) {
    // base cases.
    if (sIdx == s.length() && pIdx == p.length()) {
      return 1;
    }

    if (pIdx == p.length()) {
      // no more pattern to match s
      return 0;
    }

    if (sIdx == s.length()) {
      // s has been matched, look for ({char}*)* remaining in the pattern.
      if (p.charAt(pIdx) == '*') {
        return solve(sIdx, pIdx + 1, s, p, dp);
      }

      if (pIdx + 1 < p.length() && p.charAt(pIdx + 1) == '*') {
        return solve(sIdx, pIdx + 2, s, p, dp);
      } else {
        return 0;
      }
    }

    if (dp[sIdx][pIdx] != -1) {
      return dp[sIdx][pIdx];
    }

    if (p.charAt(pIdx) == '*') {
      int ans = solve(sIdx, pIdx + 1, s, p, dp);
      if (isMatch(s.charAt(sIdx), p.charAt(pIdx - 1))) {
        ans |= solve(sIdx + 1, pIdx, s, p, dp);
      }
      return dp[sIdx][pIdx] = ans;
    } else {

      boolean match = isMatch(s.charAt(sIdx), p.charAt(pIdx));
      int ans = 0;
      if (pIdx + 1 < p.length() && p.charAt(pIdx + 1) == '*') {
        ans |= solve(sIdx, pIdx + 2, s, p, dp);
      }
      if (match) {
        ans |= solve(sIdx + 1, pIdx + 1, s, p, dp);
      }

      return dp[sIdx][pIdx] = ans;
    }
  }

  private boolean isMatch(char a, char b) {
    return a == b || b == '.';
  }
}
