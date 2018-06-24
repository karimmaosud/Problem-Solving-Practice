package com.mirak.codeforces.rounds.regular.round491;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemD {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    String[] gridStr = new String[2];
    for(int i = 0; i < 2; i++) {
      gridStr[i] = reader.readLine();
    }

    int[][] dp = new int[105][1 << 6];
    initDp(dp);

    char[][] grid = {gridStr[0].toCharArray(), gridStr[1].toCharArray()};

    System.out.println(solve(0, grid, dp));
  }

  private static void initDp(int[][] dp) {
    for(int i = 0; i < dp.length; i++) {
      for(int j = 0; j < dp[i].length; j++) {
        dp[i][j] = -1;
      }
    }
  }

  private static int solve(int index, char[][] grid, int[][] dp) {

    if(index == grid[0].length) {
      return 0;
    }

    int hash = getHash(index, grid);
    if(dp[index][hash] != -1) {
      return dp[index][hash];
    }

    // skip anyway.
    int ans = solve(index + 1, grid, dp);

    if(!(grid[0][index] == '0' && grid[1][index] == '0')) {
      return dp[index][hash] = ans;
    }

    grid[0][index] = grid[1][index] = 'X';

    if(index - 1 >= 0 && grid[0][index - 1] == '0') {
      /*
       * I .
       *   .
       */
      grid[0][index - 1] = 'X';
      ans = Math.max(ans, 1 + solve(index + 1, grid, dp));
      grid[0][index - 1] = '0';
    }

    if(index + 1 < grid[0].length && grid[0][index + 1] == '0') {
      /*
       * . I
       * .
       */
      grid[0][index + 1] = 'X';
      ans = Math.max(ans, 1 + solve(index + 1, grid, dp));
      grid[0][index + 1] = '0';
    }

    if(index - 1 >= 0 && grid[1][index - 1] == '0') {
      /*
       *   .
       * I .
       */
      grid[1][index - 1] = 'X';
      ans = Math.max(ans, 1 + solve(index + 1, grid, dp));
      grid[1][index - 1] = '0';
    }

    if(index + 1 < grid[0].length && grid[1][index + 1] == '0') {
      /*
       * .
       * . I
       */
      grid[1][index + 1] = 'X';
      ans = Math.max(ans, 1 + solve(index + 1, grid, dp));
      grid[1][index + 1] = '0';
    }

    grid[0][index] = grid[1][index] = '0';

    return dp[index][hash] = ans;
  }

  private static int getHash(int index, char[][] grid) {
    int base = 1;
    int res = 0;
    for(int i = 0; i < grid.length; i++) {
      for(int j = index - 1; j <= index + 1; j++) {
        if(j < 0 || j >= grid[i].length) {
          // Assume full state in borders.
          res += base;
        }else {
          if(grid[i][j] == 'X') {
            res += base;
          }
        }
        base *= 2;
      }
    }
    return res;
  }
}
