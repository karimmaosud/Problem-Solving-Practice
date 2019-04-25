package com.mirak.leetcode.individual.hard;

public class KInversePairsArray {

  private final int MOD = 1000000007;

  public int kInversePairs(int n, int k) {
    int[][] dp = new int[2][k + 1];
    dp[1][0] = 1;
    for (int i = 2; i <= n; ++i) {
      int idx = i & 1;
      dp[idx][0] = 1;
      int sub = 0;
      int prefixSum = 1;
      for (int j = 1; j <= k; ++j) {
        dp[idx][j] = ((prefixSum + dp[idx ^ 1][j]) % MOD - sub + MOD) % MOD;
        if (j >= i - 1) {
          sub = (sub + dp[idx ^ 1][j - i + 1]) % MOD;
        }
        prefixSum = (prefixSum + dp[idx ^ 1][j]) % MOD;
        if (dp[idx][j] == 0) {
          break;
        }
      }
    }
    return dp[n & 1][k];
  }
}
