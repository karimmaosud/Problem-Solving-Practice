package com.mirak.leetcode.individual.hard;

import java.util.*;

public class RegularExpressionMatching {

  public boolean isMatch(String s, String p) {
    int[][] dp = new int[s.length() + 2][p.length() + 2];
    for (int i = 0; i < dp.length; i++) {
      Arrays.fill(dp[i], -1);
    }
    int ans = solve(0, 0, s, p, dp);
    return ans == 1;
  }

  private int solve(int is, int ip, String s, String p, int[][] dp) {
    if (is == s.length() && ip == p.length()) {
      return 1;
    }

    if (ip == p.length()) {
      return 0;
    }

    if (dp[is][ip] != -1) {
      return dp[is][ip];
    }

    int skip = 0, match = 0;
    boolean isStarNext = starNext(p, ip);
    if (isStarNext) {
      skip = solve(is, ip + 2, s, p, dp);
    }

    if (is < s.length() && matchChars(s.charAt(is), p.charAt(ip))) {
      match |= solve(is + 1, ip + (!isStarNext ? 1 : 0), s, p, dp);
    }
    return dp[is][ip] = match | skip;
  }

  private boolean matchChars(char a, char p) {
    return p == '.' || a == p;
  }

  private boolean starNext(String p, int idx) {
    return idx + 1 < p.length() && p.charAt(idx + 1) == '*';
  }
}
