package com.mirak.leetcode.contests.contest133;

import java.util.*;


public class MatrixCellsInDistanceOrder {

  private int[] rowInc = {1, -1, 0, 0};
  private int[] colInc = {0, 0, 1, -1};

  public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
    int[][] res = new int[R * C][2];
    Queue<int[]> q = new LinkedList<>();
    boolean[][] vis = new boolean[R][C];
    q.add(new int[]{r0, c0});
    vis[r0][c0] = true;
    res[0] = new int[]{r0, c0};
    int idx = 1;
    while (!q.isEmpty()) {
      int[] cell = q.poll();
      int i = cell[0];
      int j = cell[1];
      for (int k = 0; k < rowInc.length; ++k) {
        int i_ = i + rowInc[k];
        int j_ = j + colInc[k];
        if (i_ < 0 || j_ < 0 || i_ == R || j_ == C || vis[i_][j_]) {
          continue;
        }
        res[idx] = new int[]{i_, j_};
        vis[i_][j_] = true;
        q.add(res[idx++]);
      }
    }
    return res;
  }
}
