package com.mirak.leetcode.contests.contest124;

import java.util.*;

public class RottingOranges {

  private final int INF = 1000000000;
  private final int SHIFT = 100;
  private int[] rowInc = {1, -1, 0, 0};
  private int[] columnInc = {0, 0, 1, -1};

  public int orangesRotting(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;
    int[][] dist = new int[n][m];
    for (int[] row : dist) {
      Arrays.fill(row, INF);
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == 2) {
          bfs(i, j, grid, dist, n, m);
        }
      }
    }
    int max = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] != 1) {
          continue;
        }
        max = Math.max(max, dist[i][j]);
      }
    }
    return max == INF ? -1 : max;
  }

  private void bfs(int row, int column, int[][] grid, int[][] dist, int n, int m) {
    Queue<Integer> q = new LinkedList<>();
    q.add(row * SHIFT + column);
    dist[row][column] = 0;
    while (!q.isEmpty()) {
      int cell = q.poll();
      int i = cell / SHIFT;
      int j = cell % SHIFT;
      for (int k = 0; k < rowInc.length; k++) {
        int i_ = i + rowInc[k];
        int j_ = j + columnInc[k];
        if (i_ < 0 || j_ < 0 || i_ == n || j_ == m || grid[i_][j_] != 1) {
          continue;
        }
        if (dist[i_][j_] >= dist[i][j] + 1) {
          dist[i_][j_] = dist[i][j] + 1;
          q.add(i_ * SHIFT + j_);
        }
      }
    }
  }


}
