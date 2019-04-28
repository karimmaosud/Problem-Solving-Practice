package com.mirak.codeforces.rounds.regular.round554;

import java.util.*;
import java.io.*;

public class NekoAndAkisPrank {

  private static int MOD = 1000000007;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    int[][][] dp = new int[n + 1][n + 1][2];
    for (int[][] ar : dp) {
      for (int[] row : ar) {
        Arrays.fill(row, -1);
      }
    }
    System.out.println(solve(0, 0, 1, n, dp));
  }

  private static int solve(int open, int close, int take, int n, int[][][] dp) {
    if (open + close == 2 * n) {
      return 0;
    }
    if (dp[open][close][take] != -1) {
      return dp[open][close][take];
    }
    if (open == n) {
      return dp[open][close][take] = Math.max(solve(open, close + 1, 1, n, dp),
          take == 1 ? (1 + solve(open, close + 1, 0, n, dp) % MOD) % MOD : 0);
    }

    if (open == close) {
      return dp[open][close][take] = Math.max(solve(open + 1, close, 1, n, dp),
          take == 1 ? (1 + solve(open + 1, close, 0, n, dp) % MOD) % MOD : 0);
    }

    int leave =
        (solve(open + 1, close, 1, n, dp) % MOD + solve(open, close + 1, 1, n, dp) % MOD) % MOD;

    if (take == 0) {
      return dp[open][close][take] = leave;
    }
    int a = (1 + (solve(open + 1, close, 1, n, dp) % MOD + solve(open, close + 1, 0, n, dp) % MOD) % MOD) % MOD;
    int b = (1 + (solve(open + 1, close, 0, n, dp) % MOD + solve(open, close + 1, 1, n, dp) % MOD) % MOD) % MOD;
    return dp[open][close][take] = Math.max(leave, Math.max(a, b));
  }

}
