package com.mirak.leetcode.individual.hard;

import java.util.*;


public class WildcardMatching {

  public boolean isMatch(String s, String p) {
    int[][] dp = new int[s.length()][p.length()];
    for (int i = 0; i < dp.length; i++) {
      Arrays.fill(dp[i], -1);
    }
    return solve(s, p, 0, 0, dp) == 1;
  }

  private int solve(String s, String p, int is, int ip, int[][] dp) {
    if (is == s.length() && ip == p.length()) {
      return 1;
    }
    if (is == s.length()) {
      return allStars(p, ip);
    }
    if (ip == p.length()) {
      return 0;
    }
    if (dp[is][ip] != -1) {
      return dp[is][ip];
    }
    if (p.charAt(ip) == '*') {
      return dp[is][ip] = solve(s, p, is + 1, ip, dp) | solve(s, p, is, ip + 1, dp);
    }

    if (isMatch(s.charAt(is), p.charAt(ip))) {
      return dp[is][ip] = solve(s, p, is + 1, ip + 1, dp);
    }
    return dp[is][ip] = 0;
  }

  private int allStars(String p, int i) {
    for (; i < p.length(); i++) {
      if (p.charAt(i) != '*') {
        return 0;
      }
    }
    return 1;
  }

  private boolean isMatch(char cS, char cP) {
    return cS == cP || cP == '?';
  }

}
