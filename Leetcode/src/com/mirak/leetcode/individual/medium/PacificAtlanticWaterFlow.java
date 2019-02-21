package com.mirak.leetcode.individual.medium;

import java.util.*;

public class PacificAtlanticWaterFlow {

  private int[] rowInc = {1, -1, 0, 0};
  private int[] colInc = {0, 0, 1, -1};

  private final int SHIFT = 10000;

  // 0 → can reach neither.
  // 1 → can reach pacific.
  // 2 → can reach atlantic.
  // 3 → can reach both.
  public List<int[]> pacificAtlantic(int[][] matrix) {
    List<int[]> list = new LinkedList<>();
    int n = matrix.length;
    if (n == 0) {
      return list;
    }
    int m = matrix[0].length;

    boolean[][] vis = new boolean[n][m];
    int[][] ocean = new int[n][m];

    joinState(ocean, 0, n - 1, 0, 0, 1);
    joinState(ocean, 0, 0, 0, m - 1, 1);

    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      vis[i][0] = true;
      q.add(i);
    }
    for (int i = 1; i < m; i++) {
      vis[0][i] = true;
      q.add(i * SHIFT);
    }

    bfs(q, vis, ocean, matrix, n, m);

    joinState(ocean, 0, n - 1, m - 1, m - 1, 2);
    joinState(ocean, n - 1, n - 1, 0, m - 1, 2);

    for (boolean[] row : vis) {
      Arrays.fill(row, false);
    }

    for (int i = 0; i < n; i++) {
      vis[i][m - 1] = true;
      q.add(i + (m - 1) * SHIFT);
    }
    for (int i = 0; i < m - 1; i++) {
      vis[n - 1][i] = true;
      q.add(n - 1 + (i * SHIFT));
    }

    bfs(q, vis, ocean, matrix, n, m);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (ocean[i][j] == 3) {
          list.add(new int[]{i, j});
        }
      }
    }
    return list;
  }

  private void bfs(Queue<Integer> q, boolean[][] vis, int[][] ocean, int[][] matrix, int n, int m) {
    while (!q.isEmpty()) {
      int cell = q.poll();
      int row = cell % SHIFT;
      int col = cell / SHIFT;
      for (int k = 0; k < rowInc.length; k++) {
        int row_ = row + rowInc[k];
        int col_ = col + colInc[k];
        if (row_ < 0 || row_ == n || col_ < 0 || col_ == m || vis[row_][col_]
            || matrix[row_][col_] < matrix[row][col]) {
          continue;
        }
        vis[row_][col_] = true;
        ocean[row_][col_] |= ocean[row][col];
        q.add(row_ + col_ * SHIFT);
      }
    }
  }

  private void joinState(int[][] ocean, int startRow, int endRow, int startColumn, int endColumn,
      int state) {
    for (int i = startRow; i <= endRow; i++) {
      for (int j = startColumn; j <= endColumn; j++) {
        ocean[i][j] |= state;
      }
    }
  }
}
