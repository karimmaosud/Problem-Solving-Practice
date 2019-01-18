package com.mirak.leetcode.individual.medium;

import java.util.*;

public class LongestUncommonSubsequenceII {

  public int findLUSlength(String[] strs) {
    int res = -1;
    int[][] dp = new int[10][10];

    for (int i = 0; i < strs.length; i++) {
      boolean candidate = true;
      for (int j = 0; j < strs.length; j++) {
        if (j == i) {
          continue;
        }
        reset(dp);
        if (longestCommonSubsequence(strs[i], strs[j], 0, 0, dp) == strs[i].length()) {
          candidate = false;
        }
      }
      if (candidate) {
        res = Math.max(res, strs[i].length());
      }
    }
    return res;
  }

  private void reset(int[][] dp) {
    for (int i = 0; i < dp.length; i++) {
      Arrays.fill(dp[i], -1);
    }
  }

  private int longestCommonSubsequence(String s1, String s2, int i, int j, int[][] dp) {
    if (i == s1.length() || j == s2.length()) {
      return 0;
    }
    if (dp[i][j] != -1) {
      return dp[i][j];
    }
    if (s1.charAt(i) == s2.charAt(j)) {
      return dp[i][j] = 1 + longestCommonSubsequence(s1, s2, i + 1, j + 1, dp);
    }
    return dp[i][j] = Math.max(longestCommonSubsequence(s1, s2, i + 1, j, dp),
        longestCommonSubsequence(s1, s2, i, j + 1, dp));
  }

}
