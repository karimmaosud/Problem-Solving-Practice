package com.mirak.leetcode.individual.hard;

import java.util.*;

public class MinimumCostToMergeStones {

  private final int INF = 1000000000;

  public int mergeStones(int[] stones, int K) {
    int n = stones.length;
    if (n == 0) {
      return 0;
    }
    int[][][] dp = new int[n][n][K + 1];
    for (int[][] ar : dp) {
      for (int[] row : ar) {
        Arrays.fill(row, -1);
      }
    }
    int[] sum = new int[n];
    sum[0] = stones[0];
    for (int i = 0; i < n; i++) {
      sum[i] = stones[i] + (i > 0 ? sum[i - 1] : 0);
    }
    int ans = solve(0, n - 1, 1, dp, sum, K);
    return ans == INF ? -1 : ans;
  }

  private int solve(int l, int r, int divisions, int[][][] dp, int[] sum, int K) {

    if (r - l + 1 == K) {
      return divisions == 1 ? sum[r] - (l > 0 ? sum[l - 1] : 0) : INF;
    }
    if (r - l + 1 == divisions) {
      return 0;
    }

    if (r - l + 1 < divisions || r - l + 1 < K) {
      return INF;
    }

    if (dp[l][r][divisions] != -1) {
      return dp[l][r][divisions];
    }

    int cost = divisions == 1 ? sum[r] - (l > 0 ? sum[l - 1] : 0) : 0;
    int ans = INF;
    for (int i = l; i < r; i++) {
      ans = Math.min(ans, cost + solve(l, i, 1, dp, sum, K) + solve(i + 1, r,
          divisions == 1 ? K - 1 : divisions - 1, dp, sum, K));
    }
    for (int i = r; i > l; i--) {
      ans = Math.min(ans,
          cost + solve(l, i - 1, divisions == 1 ? K - 1 : divisions - 1, dp, sum, K) + solve(i, r,
              1, dp, sum, K));
    }
    return dp[l][r][divisions] = ans;
  }
}
