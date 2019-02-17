package com.mirak.leetcode.individual.hard;

public class PaintHouseII {

  public int minCostII(int[][] costs) {
    if (costs.length == 0) {
      return 0;
    }
    int[][] dp = new int[2][costs[0].length];
    for (int j = 0; j < costs[0].length; j++) {
      dp[0][j] = costs[0][j];
    }
    for (int i = 1; i < costs.length; i++) {
      int[] mins = new int[2];
      mins[0] = mins[1] = 1000000000;
      int currentIdx = i & 1;
      for (int j = 0; j < costs[i].length; j++) {
        if (dp[currentIdx ^ 1][j] < mins[1]) {
          mins[1] = dp[currentIdx ^ 1][j];
          adjustMins(mins);
        }
      }
      for (int j = 0; j < costs[i].length; j++) {
        int add = mins[0] == dp[currentIdx ^ 1][j] ? mins[1] : mins[0];
        dp[currentIdx][j] = costs[i][j] + add;
      }
    }
    int min = 1000000000;
    int idx = costs.length & 1;
    for (int j = 0; j < costs[0].length; j++) {
      min = Math.min(min, dp[idx ^ 1][j]);
    }
    return min;
  }

  private void adjustMins(int[] mins) {
    if (mins[0] > mins[1]) {
      int temp = mins[0];
      mins[0] = mins[1];
      mins[1] = temp;
    }
  }

}
