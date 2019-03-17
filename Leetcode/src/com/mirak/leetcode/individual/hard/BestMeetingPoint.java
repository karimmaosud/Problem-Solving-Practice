package com.mirak.leetcode.individual.hard;

import java.util.*;

public class BestMeetingPoint {

  private final int INF = 1000000000;

  public int minTotalDistance(int[][] grid) {
    int n = grid.length;
    if (n == 0) {
      return 0;
    }
    int m = grid[0].length;
    int[] rSum = new int[n];
    int[] cSum = new int[m];
    int[][] dist = new int[n][m];
    for (int[] row : dist) {
      Arrays.fill(row, INF);
    }
    init(rSum, cSum, grid);
    dist[0][0] = getInitialDistance(grid);
    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{0, 0});
    int ans = INF;
    while (!q.isEmpty()) {
      int[] cell = q.poll();
      int i = cell[0], j = cell[1];
      ans = Math.min(ans, dist[i][j]);
      if (i + 1 < n && dist[i + 1][j] == INF) {
        dist[i + 1][j] = dist[i][j] + 2 * rSum[i] - rSum[n - 1];
        q.add(new int[]{i + 1, j});
      }
      if (j + 1 < m && dist[i][j + 1] == INF) {
        dist[i][j + 1] = dist[i][j] + 2 * cSum[j] - cSum[m - 1];
        q.add(new int[]{i, j + 1});
      }
    }
    return ans;
  }


  private void init(int[] rSum, int[] cSum, int[][] grid) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        rSum[i] += grid[i][j];
        cSum[j] += grid[i][j];
      }
      if (i > 0) {
        rSum[i] += rSum[i - 1];
      }
    }
    for (int j = 1; j < grid[0].length; j++) {
      cSum[j] += cSum[j - 1];
    }
  }

  private int getInitialDistance(int[][] grid) {
    int distance = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        if (grid[i][j] == 1) {
          distance += (i + j);
        }
      }
    }
    return distance;
  }
}
