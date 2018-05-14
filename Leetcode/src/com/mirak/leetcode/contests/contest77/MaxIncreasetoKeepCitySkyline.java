package com.mirak.leetcode.contests.contest77;

public class MaxIncreasetoKeepCitySkyline {
  public int maxIncreaseKeepingSkyline(int[][] grid) {
    int[] rowMax = new int[grid.length];
    int[] columnMax = new int[grid[0].length];
    for(int i = 0; i < grid.length; i++) {
      for(int j = 0; j < grid[i].length; j++) {
        columnMax[j] = Math.max(columnMax[j], grid[i][j]);
        rowMax[i] = Math.max(rowMax[i], grid[i][j]);
      }
    }
    int res = 0;
    for(int i = 0; i < grid.length; i++) {
      for(int j = 0; j < grid[i].length; j++) {
        res += Math.min(rowMax[i], columnMax[j]) - grid[i][j];
      }
    }
    return res;
  }
}
