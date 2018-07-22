package com.mirak.hackercup.round2018.round1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LetItFlow {

  private static final long MOD = 1000000007;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("/Users/masoud/Desktop/let_it_flow.txt"));
    BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/masoud/Documents/out.txt"));
    int T = Integer.parseInt(reader.readLine());
    for (int t = 1; t <= T; t++) {
      int n = Integer.parseInt(reader.readLine());
      char[][] grid = new char[3][n];
      for (int i = 0; i < 3; i++) {
        grid[i] = reader.readLine().toCharArray();
      }
      long[][][] dp = new long[3][n][4];
      init(dp);
      writer.write("Case #" + t + ": " + solve(0, 0, 0, dp, grid) + "\n");
    }
    reader.close();
    writer.close();
  }

  private static void init(long[][][] dp) {
    for (int i = 0; i < dp.length; i++) {
      for (int j = 0; j < dp[i].length; j++) {
        for (int k = 0; k < dp[i][j].length; k++) {
          dp[i][j][k] = -1;
        }
      }
    }
  }

  private static long solve(int i, int j, int direction, long[][][] dp, char[][] grid) {

    if (i < 0 || i == dp.length || j < 0 || j == dp[i].length || grid[i][j] == '#') {
      return 0;
    }

    if (i == dp.length - 1 && j == dp[i].length - 1) {
      if (grid[i][j] == '#' || direction != 1) {
        return 0;
      }
      return 1;
    }

    if (dp[i][j][direction] != -1) {
      return dp[i][j][direction];
    }

    if (direction == 0) {
      return dp[i][j][direction] =
          (solve(i + 1, j, 1, dp, grid) % MOD + solve(i - 1, j, 2, dp, grid) % MOD) % MOD;
    }

    return dp[i][j][direction] = solve(i, j + 1, 0, dp, grid) % MOD;

  }
}
