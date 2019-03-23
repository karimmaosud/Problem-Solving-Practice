package com.mirak.leetcode.individual.hard;

public class DecodeWaysII {

  private final int MOD = 1000000007;

  public int numDecodings(String s) {
    int n = s.length();
    if (n == 0) {
      return 0;
    }
    long[] dp = new long[3];
    for (int i = 0; i < n; i++) {
      dp[i % 3] = 0;
      dp[i % 3] = (dp[i % 3] + (numDecodings(s, i, i) * (i > 0 ? dp[(i - 1) % 3] : 1)) % MOD) % MOD;
      if (i == 0) {
        continue;
      }
      dp[i % 3] =
          (dp[i % 3] + (numDecodings(s, i - 1, i) * (i - 1 > 0 ? dp[(i - 2) % 3] : 1)) % MOD) % MOD;
    }
    return (int) dp[(n - 1) % 3];
  }

  private int numDecodings(String s, int l, int r) {
    if (s.charAt(l) == '0') {
      return 0;
    }
    if (l == r) {
      return s.charAt(l) == '*' ? 9 : 1;
    }
    if (s.charAt(l) == '*' && s.charAt(r) == '*') {
      return 15;
    }
    if (s.charAt(l) == '*') {
      return s.charAt(r) <= '6' ? 2 : 1;
    }
    if (s.charAt(r) == '*') {
      return s.charAt(l) == '1' ? 9 : s.charAt(l) == '2' ? 6 : 0;
    }
    return Integer.parseInt(s.substring(l, r + 1)) <= 26 ? 1 : 0;
  }

}
