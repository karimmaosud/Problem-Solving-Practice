package com.mirak.leetcode.individual.hard;

public class KInversePairsArray {

  private final long MOD = 1000000007;

  public int kInversePairs(int n, int k) {

    long[][] dp = new long[2][k + 1];
    dp[1][0] = 1;
    long[] sum = new long[k + 1];
    for (int i = 2; i <= n; i++) {
      int idx = i % 2;

      sum[0] = dp[idx ^ 1][0];

      for (int j = 1; j <= k; j++) {
        sum[j] = (sum[j - 1] + dp[idx ^ 1][j]) % MOD;
      }

      dp[idx][0] = 1;
      for (int j = 1; j <= k; j++) {
        dp[idx][j] = sum[j];
        if (j - i >= 0) {
          dp[idx][j] = (dp[idx][j] - sum[j - i] + MOD) % MOD;
        }
      }
    }
    return (int) dp[n % 2][k];
  }
}
