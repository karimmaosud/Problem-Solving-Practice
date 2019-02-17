package com.mirak.leetcode.contests.contest124;

import java.util.*;

public class NumberOfSquarefulArrays {

  public int numSquarefulPerms(int[] A) {

    if (A.length == 1) {
      return isPerfectSquare(A[0]) ? 1 : 0;
    }

    int[][] dp = new int[A.length + 1][1 << (A.length + 1)];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    return solve(A.length, 0, A, dp);
  }

  private int solve(int last, int mask, int[] A, int[][] dp) {
    if (mask == (1 << A.length) - 1) {
      return 1;
    }
    if (dp[last][mask] != -1) {
      return dp[last][mask];
    }
    Set<Integer> chosen = new HashSet<>();
    int ans = 0;
    for (int i = 0; i < A.length; i++) {
      if ((mask & (1 << i)) == 0 && !chosen.contains(A[i])) {
        chosen.add(A[i]);
        if (last == A.length || isPerfectSquare(A[last] + A[i])) {
          ans += solve(i, mask | (1 << i), A, dp);
        }
      }
    }
    return dp[last][mask] = ans;
  }

  private boolean isPerfectSquare(int num) {
    int sq = (int) Math.sqrt(1.0 * num);
    return sq * sq == num;
  }
}
