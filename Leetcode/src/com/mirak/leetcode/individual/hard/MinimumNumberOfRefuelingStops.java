package com.mirak.leetcode.individual.hard;

import java.util.*;

public class MinimumNumberOfRefuelingStops {

  private final long INF = 1000000000000000000L;

  public int minRefuelStops(int target, int startFuel, int[][] stations) {
    ArrayList<int[]> list = new ArrayList<>();

    list.add(new int[]{0, startFuel});
    list.addAll(Arrays.asList(stations));
    list.add(new int[]{target, 0});

    long[][] dp = new long[2][list.size()];
    Arrays.fill(dp[0], -INF);
    dp[0][0] = 0;

    for (int i = 1; i < list.size(); i++) {
      if (list.get(0)[1] >= list.get(i)[0]) {
        dp[0][i] = list.get(0)[1] - list.get(i)[0];
      } else {
        break;
      }
    }

    if (dp[0][list.size() - 1] >= 0) {
      return 0;
    }

    for (int stops = 1; stops <= stations.length; stops++) {
      int prev = (stops & 1) ^ 1;
      int current = (stops & 1);
      Arrays.fill(dp[current], -INF);

      dp[current][0] = 0;
      int maxPrev = 0;
      for (int i = 1; i < list.size(); i++) {
        long valueFromMaxPrev = getValueFromIndex(prev, i, maxPrev, dp, list);
        long valueFromPrev = getValueFromIndex(prev, i, i - 1, dp, list);
        if (valueFromPrev >= valueFromMaxPrev) {
          maxPrev = i - 1;
        }
        dp[current][i] = Math.max(valueFromMaxPrev, valueFromPrev);
      }
      if (dp[current][list.size() - 1] >= 0) {
        return stops;
      }
    }
    return -1;
  }

  private long getValueFromIndex(int prev, int i, int j, long[][] dp,
      ArrayList<int[]> list) {
    if (dp[prev][j] < 0) {
      return -INF;
    }
    return dp[prev][j] + list.get(j)[1] - (list.get(i)[0] - list.get(j)[0]);
  }
}