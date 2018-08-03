package com.mirak.leetcode.individual.hard;

import java.util.*;

public class PalindromePartitioningII {

  public int minCut(String s) {

    if (s.length() == 0) {
      return 0;
    }
    int[] dp = new int[s.length()];
    Arrays.fill(dp, -1);

    ArrayList<Integer>[] palindromeLengths = new ArrayList[s.length()];
    for (int i = 0; i < s.length(); i++) {
      palindromeLengths[i] = new ArrayList<>();
    }

    findPalindromes(s, palindromeLengths, true);
    findPalindromes(s, palindromeLengths, false);
    return solve(0, palindromeLengths, dp);
  }

  private void findPalindromes(String s, ArrayList<Integer>[] palindromeLengths,
      boolean oddPalindrome) {
    for (int i = 0; i < s.length(); i++) {
      int l = i;
      int r = oddPalindrome ? i : i + 1;
      while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
        palindromeLengths[l].add(r - l + 1);
        l--;
        r++;
      }
    }
  }

  int solve(int index, ArrayList<Integer>[] palindromeLengths, int[] dp) {
    if (index == palindromeLengths.length) {
      return -1;
    }
    if (dp[index] != -1) {
      return dp[index];
    }
    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < palindromeLengths[index].size(); i++) {
      ans = Math
          .min(ans, 1 + solve(index + palindromeLengths[index].get(i), palindromeLengths, dp));
    }
    return dp[index] = ans;
  }
}
