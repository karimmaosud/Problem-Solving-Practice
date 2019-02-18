package com.mirak.leetcode.individual.hard;

public class ProfitableSchemes {

  private final int MOD = 1000000007;

  public int profitableSchemes(int G, int P, int[] group, int[] profit) {
    int[][] dp = new int[G + 1][P + 1];
    for (int i = 0; i < group.length; i++) {
      if (group[i] > G) {
        continue;
      }
      dp[group[i]][Math.min(P, profit[i])]++;
    }

    for (int i = 0; i < group.length; i++) {
      if (group[i] > G) {
        continue;
      }
      dp[group[i]][Math.min(P, profit[i])]++;
      for (int g = 0; g <= G; g++) {
        for (int p = 0; p <= P; p++) {
          int gi = g + group[i];
          if (gi > G) {
            continue;
          }
          dp[gi][Math.min(P, p + profit[i])] =
              (dp[gi][Math.min(P, p + profit[i])] + dp[g][p]) % MOD;
        }
      }
    }
    int ans = 0;
    for (int i = 0; i <= G; i++) {
      ans = (ans + dp[i][P]) % MOD;
    }
    return ans;
  }
}
