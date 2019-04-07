package com.mirak.leetcode.individual.medium;

public class KeysKeyboard {
  public int maxA(int N) {
    if (N == 0) {
      return 0;
    }

    int[] dp = new int[N + 1];
    dp[1] = 1;
    for (int i = 2; i <= N; ++i) {
      dp[i] = dp[i - 1] + 1;
      for (int j = i - 3; j >= 0; --j) {
        dp[i] = Math.max(dp[i], dp[j] * (i - j - 1));
      }
    }
    return dp[N];
  }

}
