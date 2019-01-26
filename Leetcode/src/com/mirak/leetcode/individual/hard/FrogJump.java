package com.mirak.leetcode.individual.hard;

import java.util.*;

public class FrogJump {

  public boolean canCross(int[] stones) {
    Map<Integer, Integer> index = new HashMap<>();
    for (int i = 0; i < stones.length; i++) {
      index.put(stones[i], i);
    }
    int[][] dp = new int[stones.length + 2][stones.length + 2];
    for (int[] ar : dp) {
      Arrays.fill(ar, -1);
    }
    return solve(0, 0, stones, dp, index) == 1;
  }

  private int solve(int i, int k, int[] stones, int[][] dp, Map<Integer, Integer> index) {
    if (i == stones.length - 1) {
      return 1;
    }
    if (dp[i][k] != -1) {
      return dp[i][k];
    }
    for (int j = -1; j <= 1; j++) {
      if (k + j > 0 && index.containsKey(stones[i] + k + j)) {
        if (solve(index.get(stones[i] + k + j), k + j, stones, dp, index) == 1) {
          return dp[i][k] = 1;
        }
      }
    }
    return dp[i][k] = 0;
  }

}
