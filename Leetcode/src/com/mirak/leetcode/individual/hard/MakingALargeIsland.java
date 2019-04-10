package com.mirak.leetcode.individual.hard;

import java.util.*;

public class MakingALargeIsland {

  private int[] rowInc = {1, -1, 0, 0};
  private int[] colInc = {0, 0, 1, -1};

  public int largestIsland(int[][] grid) {
    int n = grid.length;
    if (n == 0) {
      return 0;
    }
    int m = grid[0].length;
    int[] p = new int[n * m];
    int[] rank = new int[n * m];
    int[] size = new int[n * m];
    for (int i = 0; i < p.length; ++i) {
      int r = i / m;
      int c = i % m;
      if (grid[r][c] == 1) {
        size[i] = 1;
      }
      p[i] = i;
    }
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        if (grid[i][j] == 0) {
          continue;
        }
        for (int k = 0; k < rowInc.length; ++k) {
          int i_ = i + rowInc[k];
          int j_ = j + colInc[k];
          if (invalidCell(i_, j_, grid, n, m)) {
            continue;
          }
          union(i * m + j, i_ * m + j_, p, rank, size);
        }

      }
    }
    int max = 0;
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < m; ++j) {
        if (grid[i][j] == 1) {
          max = Math.max(max, size[findParent(i * m + j, p)]);
          continue;
        }

        Set<Integer> parents = new HashSet<>();
        for (int k = 0; k < rowInc.length; ++k) {
          int i_ = i + rowInc[k];
          int j_ = j + colInc[k];
          if (invalidCell(i_, j_, grid, n, m)) {
            continue;
          }
          parents.add(findParent(i_ * m + j_, p));
        }
        int componentSize = 0;
        for (int parent : parents) {
          componentSize += size[parent];
        }
        max = Math.max(max, componentSize + 1);
      }
    }
    return max;
  }


  private boolean invalidCell(int i, int j, int[][] grid, int n, int m) {
    return i < 0 || j < 0 || i == n || j == m || grid[i][j] == 0;
  }

  private int findParent(int i, int[] p) {
    return p[i] == i ? i : (p[i] = findParent(p[i], p));
  }


  private void union(int i, int j, int[] p, int[] rank, int[] size) {
    int pi = findParent(i, p);
    int pj = findParent(j, p);
    if (pi == pj) {
      return;
    }
    if (rank[pi] > rank[pj]) {
      p[pj] = pi;
      size[pi] += size[pj];
    } else {
      p[pi] = pj;
      size[pj] += size[pi];
      if (rank[pi] == rank[pj]) {
        rank[pj]++;
      }
    }
  }
}
