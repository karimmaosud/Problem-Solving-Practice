package com.mirak.leetcode.individual.medium;

import java.util.*;

public class CanIWin {

  public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    if (desiredTotal > (maxChoosableInteger * (maxChoosableInteger + 1)) / 2) {
      return false;
    }
    if (desiredTotal == 0) {
      return true;
    }
    int[][] dp = new int[1 << maxChoosableInteger][2];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    return solve(0, 0, desiredTotal, maxChoosableInteger, dp) == 0;
  }


  private int solve(int mask, int turn, int desiredTotal, int n, int[][] dp) {
    if (getMaskValue(mask) >= desiredTotal) {
      return turn ^ 1;
    }

    if (dp[mask][turn] != -1) {
      return dp[mask][turn];
    }

    for (int i = 0; i < n; i++) {
      if ((mask & (1 << i)) == 0 && solve(mask | (1 << i), turn ^ 1, desiredTotal, n, dp) == turn) {
        return dp[mask][turn] = turn;
      }
    }
    return dp[mask][turn] = turn ^ 1;
  }

  private int getMaskValue(int mask) {
    int sum = 0;
    int i = 1;
    while (mask > 0) {
      if ((mask & 1) == 1) {
        sum += i;
      }
      i++;
      mask >>= 1;
    }
    return sum;
  }

}
