package com.mirak.leetcode.individual.hard;

import java.util.*;

public class MaximumVacationDays {

  public int maxVacationDays(int[][] flights, int[][] days) {
    int n = flights.length;
    if (n == 0) {
      return 0;
    }
    int k = days[0].length;
    int[][] dp = new int[n][k + 1];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    return solve(0, 0, flights, days, dp);
  }

  private int solve(int city, int week, int[][] flights, int[][] days, int[][] dp) {

    if (week == days[0].length) {
      return 0;
    }

    if (dp[city][week] != -1) {
      return dp[city][week];
    }

    int ans = 0;
    for (int i = 0; i < flights.length; i++) {
      if (flights[city][i] != 0 || city == i) {
        ans = Math.max(ans, days[i][week] + solve(i, week + 1, flights, days, dp));
      }
    }
    return dp[city][week] = ans;
  }

}
