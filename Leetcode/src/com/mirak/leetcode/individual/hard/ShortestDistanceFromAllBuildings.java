package com.mirak.leetcode.individual.hard;

import java.util.*;

public class ShortestDistanceFromAllBuildings {

  private int[] rowInc = {1, -1, 0, 0};
  private int[] colInc = {0, 0, 1, -1};

  public int shortestDistance(int[][] grid) {
    int n = grid.length;
    if (n == 0) {
      return -1;
    }
    int m = grid[0].length;
    int[][] reached = new int[n][m];
    int[][] dist = new int[n][m];
    int count = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == 1) {
          count++;
          bfs(i, j, grid, reached, dist, n, m);
        }
      }
    }
    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (reached[i][j] == count) {
          ans = Math.min(ans, dist[i][j]);
        }
      }
    }
    return ans == Integer.MAX_VALUE ? -1 : ans;
  }

  private void bfs(int i, int j, int[][] grid, int[][] reached, int[][] dist, int n, int m) {

    Queue<int[]> q = new LinkedList<>();
    q.add(new int[]{i, j});

    boolean[][] vis = new boolean[n][m];

    vis[i][j] = true;
    int level = 0;
    while (!q.isEmpty()) {
      int size = q.size();
      for (int k = 0; k < size; k++) {
        int[] cell = q.poll();
        for (int l = 0; l < rowInc.length; l++) {
          int i_ = cell[0] + rowInc[l];
          int j_ = cell[1] + colInc[l];
          if (i_ < 0 || j_ < 0 || i_ == n || j_ == m || grid[i_][j_] == 2 || vis[i_][j_]) {
            continue;
          }
          vis[i_][j_] = true;
          if (grid[i_][j_] == 0) {
            dist[i_][j_] += level + 1;
            reached[i_][j_]++;
            q.add(new int[]{i_, j_});
          }
        }
      }
      level++;
    }
  }

}
