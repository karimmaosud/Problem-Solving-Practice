package com.mirak.leetcode.individual.medium;

import java.util.*;

public class ShortestBridge {

  private int[] rowInc = {1, -1, 0, 0};
  private int[] colInc = {0, 0, 1, -1};
  private final int INF = 1000000000;

  public int shortestBridge(int[][] A) {
    int n = A.length;
    int m = A[0].length;
    int[][] dist = new int[n][m];
    for (int[] row : dist) {
      Arrays.fill(row, INF);
    }
    Queue<int[]> q = new LinkedList<>();
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        if (A[i][j] == 1) {
          dfs(i, j, A, dist, n, m, q);
          break;
        }
      }
      if (q.size() > 0) {
        break;
      }
    }
    bfs(A, n, m, q, dist);
    int res = INF;
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        if (dist[i][j] > 0 && A[i][j] == 1) {
          res = Math.min(res, dist[i][j]);
        }
      }
    }
    return res - 1;
  }

  private void bfs(int[][] A, int n, int m, Queue<int[]> q, int[][] dist) {
    while (!q.isEmpty()) {
      int[] cell = q.poll();
      int i = cell[0], j = cell[1];
      for (int k = 0; k < rowInc.length; ++k) {
        int i_ = i + rowInc[k];
        int j_ = j + colInc[k];
        if (outGrid(i_, j_, n, m) || dist[i_][j_] != INF) {
          continue;
        }
        dist[i_][j_] = dist[i][j] + 1;
        if (A[i_][j_] == 0) {
          q.add(new int[]{i_, j_});
        }
      }
    }
  }

  private void dfs(int i, int j, int[][] A, int[][] dist, int n, int m, Queue<int[]> q) {
    if (outGrid(i, j, n, m) || A[i][j] == 0 || dist[i][j] != INF) {
      return;
    }
    if (adjacentToZero(A, i, j, n, m)) {
      dist[i][j] = 0;
      q.add(new int[]{i, j});
    } else {
      dist[i][j] = -INF;
    }
    for (int k = 0; k < rowInc.length; k++) {
      dfs(i + rowInc[k], j + colInc[k], A, dist, n, m, q);
    }
  }

  private boolean adjacentToZero(int[][] A, int i, int j, int n, int m) {
    for (int k = 0; k < rowInc.length; ++k) {
      int i_ = i + rowInc[k];
      int j_ = j + colInc[k];
      if (outGrid(i_, j_, n, m)) {
        continue;
      }
      if (A[i_][j_] == 0) {
        return true;
      }
    }
    return false;
  }

  private boolean outGrid(int i, int j, int n, int m) {
    return i < 0 || j < 0 || i == n || j == m;
  }

}
