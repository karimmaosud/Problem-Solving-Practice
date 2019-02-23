package com.mirak.leetcode.individual.medium;

import java.util.*;

public class KnightProbabilityInChessboard {

  private final int SHIFT = 25;
  private int[] rowInc = {2, 2, -2, -2, 1, -1, 1, -1};
  private int[] colInc = {1, -1, 1, -1, 2, 2, -2, -2};

  public double knightProbability(int N, int K, int r, int c) {
    double[][] dp = new double[2][650];
    dp[0][r + c * SHIFT] = 1.0;
    for (int k = 1; k <= K; k++) {
      Arrays.fill(dp[k & 1], 0);
      for (int i = 0; i < dp[k & 1].length; i++) {
        if (dp[(k & 1) ^ 1][i] > 0) {
          int row = i % SHIFT;
          int col = i / SHIFT;
          for (int j = 0; j < rowInc.length; j++) {
            int row_ = row + rowInc[j];
            int col_ = col + colInc[j];
            if (row_ < 0 || row_ >= N || col_ < 0 || col_ >= N) {
              continue;
            }
            dp[k & 1][row_ + col_ * SHIFT] += (1.0 / 8.0) * dp[(k & 1) ^ 1][i];
          }
        }
      }
    }
    double ans = 0;
    for (int i = 0; i < dp[K & 1].length; i++) {
      ans += dp[K & 1][i];
    }

    return ans;
  }
}
