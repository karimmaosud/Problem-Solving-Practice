package com.mirak.leetcode.individual.hard;

public class SuperEggDrop {

  public int superEggDrop(int K, int N) {
    if (K == 1) {
      return N;
    }
    int[][] dp = new int[2][N + 1];
    for (int i = 1; i <= N; i++) {
      dp[1][i] = i;
    }
    for (int k = 2; k <= K; k++) {
      int idx = k % 2, prevN = 1, pairedN = 1;
      for (int n = 1; n <= N; n++) {
        if (n == 1) {
          dp[idx][n] = 1;
          continue;
        }
        dp[idx][n] = 1 + Math.max(dp[idx][prevN], dp[idx ^ 1][pairedN]);
        if (dp[idx][n] > dp[idx][n - 1]) {
          prevN = n - 1;
          pairedN = 1;
        } else {
          pairedN++;
        }
      }
    }
    return dp[K % 2][N];
  }
}
