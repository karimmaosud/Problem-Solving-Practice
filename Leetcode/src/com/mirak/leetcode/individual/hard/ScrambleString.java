package com.mirak.leetcode.individual.hard;

import java.util.*;

public class ScrambleString {

  public boolean isScramble(String s1, String s2) {
    int n = s1.length();
    if (n == 0) {
      return true;
    }
    int[][][] dp = new int[n][n][n + 1];
    init(dp);
    return solve(s1, s2, 0, 0, n, dp) == 1;
  }

  private void init(int[][][] dp) {
    for (int[][] ar : dp) {
      for (int[] row : ar) {
        Arrays.fill(row, -1);
      }
    }
  }

  private int solve(String s1, String s2, int l1, int l2, int len, int[][][] dp) {

    if (dp[l1][l2][len] != -1) {
      return dp[l1][l2][len];
    }

    if (match(s1, s2, l1, l2, len)) {
      return dp[l1][l2][len] = 1;
    }

    if (!isAnagrams(s1, s2, l1, l2, len)) {
      return dp[l1][l2][len] = 0;
    }

    for (int i = 1; i < len; i++) {
      int prefixPrefixMatch =
          solve(s1, s2, l1, l2, i, dp) & solve(s1, s2, l1 + i, l2 + i, len - i, dp);

      if (prefixPrefixMatch == 1) {
        return dp[l1][l2][len] = 1;
      }

      int prefixSuffixMatch =
          solve(s1, s2, l1, l2 + len - i, i, dp) & solve(s1, s2, l1 + i, l2, len - i, dp);

      if (prefixSuffixMatch == 1) {
        return dp[l1][l2][len] = 1;
      }
    }

    return dp[l1][l2][len] = 0;
  }

  private boolean match(String s1, String s2, int l1, int l2, int len) {
    for (int i = 0; i < len; i++) {
      if (s1.charAt(l1 + i) != s2.charAt(l2 + i)) {
        return false;
      }
    }
    return true;
  }

  private boolean isAnagrams(String s1, String s2, int l1, int l2, int len) {
    int[] count = new int[26];
    for (int i = 0; i < len; i++) {
      count[s1.charAt(l1 + i) - 'a']++;
    }

    for (int i = 0; i < len; i++) {
      count[s2.charAt(l2 + i) - 'a']--;
      if (count[s2.charAt(l2 + i) - 'a'] < 0) {
        return false;
      }
    }
    return true;
  }
}
