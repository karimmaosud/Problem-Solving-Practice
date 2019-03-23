package com.mirak.leetcode.individual.medium;

import java.util.*;

public class _01Matrix {

  private int[] rowInc = {1, -1, 0, 0};
  private int[] colInc = {0, 0, 1, -1};

  public int[][] updateMatrix(int[][] matrix) {
    int n = matrix.length;
    if (n == 0) {
      return new int[][]{};
    }
    int m = matrix[0].length;
    int[][] dist = new int[n][m];
    Queue<int[]> q = new LinkedList<>();
    boolean[][] vis = new boolean[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (matrix[i][j] == 0 && hasAdjacentOne(i, j, matrix)) {
          q.add(new int[]{i, j});
        }
      }
    }
    while (!q.isEmpty()) {
      int[] cell = q.poll();
      int i = cell[0], j = cell[1];
      for (int k = 0; k < rowInc.length; k++) {
        int i_ = i + rowInc[k];
        int j_ = j + colInc[k];
        if (i_ < 0 || j_ < 0 || i_ == n || j_ == m || matrix[i_][j_] == 0 || vis[i_][j_]) {
          continue;
        }
        vis[i_][j_] = true;
        dist[i_][j_] = dist[i][j] + 1;
        q.add(new int[]{i_, j_});
      }
    }
    return dist;
  }

  private boolean hasAdjacentOne(int i, int j, int[][] matrix) {
    for (int k = 0; k < rowInc.length; k++) {
      int i_ = i + rowInc[k];
      int j_ = j + colInc[k];
      if (i_ < 0 || j_ < 0 || i_ == matrix.length || j_ == matrix[0].length
          || matrix[i_][j_] == 0) {
        continue;
      }
      return true;
    }
    return false;
  }

}
