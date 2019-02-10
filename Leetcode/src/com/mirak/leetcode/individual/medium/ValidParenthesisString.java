package com.mirak.leetcode.individual.medium;

import java.util.*;

public class ValidParenthesisString {

  public boolean checkValidString(String s) {
    int[][] dp = new int[s.length()][s.length()];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    return solve(s, 0, 0, dp) == 1;
  }

  private int solve(String s, int idx, int open, int[][] dp) {
    if (open < 0) {
      return 0;
    }
    if (idx == s.length()) {
      return open == 0 ? 1 : 0;
    }
    if (dp[idx][open] != -1) {
      return dp[idx][open];
    }
    if (s.charAt(idx) == '*') {
      return dp[idx][open] = solve(s, idx + 1, open + 1, dp) |
          solve(s, idx + 1, open - 1, dp) | solve(s, idx + 1, open, dp);
    }
    return dp[idx][open] = solve(s, idx + 1, open + (s.charAt(idx) == '(' ? 1 : -1), dp);
  }
}
