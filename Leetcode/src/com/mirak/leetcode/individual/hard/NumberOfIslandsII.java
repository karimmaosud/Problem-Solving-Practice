package com.mirak.leetcode.individual.hard;

import java.util.*;

public class NumberOfIslandsII {

  private int[] rowInc = {1, -1, 0, 0};
  private int[] colInc = {0, 0, 1, -1};

  public List<Integer> numIslands2(int m, int n, int[][] positions) {
    Set<Integer> vis = new HashSet<>();
    Set<Integer> parents = new HashSet<>();
    int[] p = new int[n * m];
    int[] rank = new int[n * m];
    for (int i = 0; i < p.length; i++) {
      p[i] = i;
    }
    List<Integer> res = new ArrayList<>();
    for (int i = 0; i < positions.length; i++) {
      int row = positions[i][0];
      int col = positions[i][1];
      for (int k = 0; k < rowInc.length; k++) {
        int row_ = row + rowInc[k];
        int col_ = col + colInc[k];
        if (isEdge(row_, col_, m, n) || !vis.contains(row_ * n + col_)) {
          continue;
        }
        union(row * n + col, row_ * n + col_, p, rank, parents);
      }
      parents.add(findParent(row * n + col, p));
      vis.add(row * n + col);
      res.add(parents.size());
    }
    return res;
  }

  private boolean isEdge(int row, int col, int m, int n) {
    return row < 0 || col < 0 || row == m || col == n;
  }

  private int findParent(int i, int[] p) {
    if (p[i] == i) {
      return i;
    }
    return p[i] = findParent(p[i], p);
  }

  private void union(int i, int j, int[] p, int[] rank, Set<Integer> parents) {
    int pi = findParent(i, p);
    int pj = findParent(j, p);
    if (pi == pj) {
      return;
    }
    if (rank[pj] < rank[pi]) {
      p[pj] = pi;
      parents.remove(pj);
    } else {
      p[pi] = pj;
      if (rank[pi] == rank[pj]) {
        rank[pj]++;
      }
      parents.remove(pi);
    }
  }


}
