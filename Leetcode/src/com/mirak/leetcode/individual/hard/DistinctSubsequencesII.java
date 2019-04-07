package com.mirak.leetcode.individual.hard;

import java.util.*;

public class DistinctSubsequencesII {

  private final int MOD = 1000000007;

  public int distinctSubseqII(String S) {
    int[] dp = new int[S.length()];
    int[] lastIndex = new int[26];
    Arrays.fill(lastIndex, -1);

    for (int i = 0; i < S.length(); ++i) {
      dp[i] = lastIndex[S.charAt(i) - 'a'] == -1 ? 1 : 0;
      if (i - 1 >= 0) {
        dp[i] = (dp[i] + (dp[i - 1] % MOD * 2 % MOD) % MOD) % MOD;
      }
      if (lastIndex[S.charAt(i) - 'a'] - 1 >= 0) {
        dp[i] = (dp[i] - dp[lastIndex[S.charAt(i) - 'a'] - 1] + MOD) % MOD;
      }
      lastIndex[S.charAt(i) - 'a'] = i;
    }
    return dp[S.length() - 1];
  }

  // recursive solution. O(n * 26).
  private int count(int i, String s, int[] dp) {
    if (i == s.length()) {
      return 0;
    }
    if (dp[i] != -1) {
      return dp[i];
    }
    int ans = 0;
    for (char a = 'a'; a <= 'z'; ++a) {
      int next = s.indexOf(a, i);
      if (next != -1) {
        ans = (ans % MOD + (1 + count(next + 1, s, dp)) % MOD) % MOD;
      }
    }
    return dp[i] = ans;
  }

}
