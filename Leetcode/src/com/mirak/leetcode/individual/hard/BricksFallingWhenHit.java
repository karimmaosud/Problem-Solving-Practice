package com.mirak.leetcode.individual.hard;

import java.util.*;

public class BricksFallingWhenHit {

  private int[] rowInc = {1, -1, 0, 0};
  private int[] columnInc = {0, 0, 1, -1};

  private class DSU {

    int[] p;
    int[] rank;
    int[] size;
    int roofParent;

    DSU(int len) {
      p = new int[len];
      rank = new int[len];
      size = new int[len];
      roofParent = len - 1;
      init();
    }

    void init() {
      for (int i = 0; i < p.length; i++) {
        p[i] = i;
        size[i] = 1;
      }
      size[roofParent] = 0;
    }

    int findParent(int i) {
      if (p[i] == i) {
        return i;
      }
      p[i] = findParent(p[i]);
      return p[i];
    }

    void union(int i, int j) {

      int pi = findParent(i);
      int pj = findParent(j);
      if (pi == pj) {
        return;
      }
      if (pi == roofParent) {
        unionToK(pj, pi);
      } else if (pj == roofParent) {
        unionToK(pi, pj);
      } else {
        if (rank[pi] > rank[pj]) {
          unionToK(pj, pi);
        } else {
          unionToK(pi, pj);
        }
      }
    }

    void unionToK(int i, int k) {
      p[i] = k;
      size[k] += size[i];
      rank[k] += (rank[k] ^ rank[i]) == 0 ? 1 : 0;
    }

  }

  public int[] hitBricks(int[][] grid, int[][] hits) {

    int n = grid.length;
    int m = grid[0].length;

    int[][] reverseGrid = Arrays.copyOf(grid, n);
    eraseAllCells(hits, reverseGrid);

    // 1. build a DSU (Disjoint Set Union)
    DSU dsu = new DSU(n * m + 1);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (reverseGrid[i][j] <= 0) {
          continue;
        }
        if (i == 0) {
          dsu.union(i * m + j, n * m);
          continue;
        }
        for (int k = 0; k < 4; k++) {
          int i_ = i + rowInc[k];
          int j_ = j + columnInc[k];
          if (i_ < 0 || j_ < 0 || i_ == n || j_ == m || reverseGrid[i_][j_] <= 0) {
            continue;
          }
          dsu.union(i * m + j, i_ * m + j_);
        }
      }
    }
    int[] res = new int[hits.length];
    for (int i = hits.length - 1; i >= 0; i--) {
      int r = hits[i][0];
      int c = hits[i][1];

      reverseGrid[r][c]++;

      if (reverseGrid[r][c] <= 0) {
        continue;
      }

      Set<Integer> adjParents = new HashSet<>();
      for (int k = 0; k < 4; k++) {
        int r_ = r + rowInc[k];
        int c_ = c + columnInc[k];
        if (r_ < 0 || c_ < 0 || r_ == n || c_ == m || reverseGrid[r_][c_] <= 0) {
          continue;
        }
        adjParents.add(dsu.findParent(r_ * m + c_));
      }
      int prevRoofSize = dsu.size[n * m];
      if (r == 0) {
        dsu.union(r * m + c, n * m);
      }
      for (int parent : adjParents) {
        dsu.union(r * m + c, parent);
      }
      res[i] = dsu.size[n * m] - prevRoofSize - (dsu.findParent(r * m + c) == n * m ? 1 : 0);
      reverseGrid[r][c] = 1;
    }
    return res;
  }

  private void eraseAllCells(int[][] hits, int[][] reverseGrid) {
    for (int[] hit : hits) {
      reverseGrid[hit[0]][hit[1]]--;
    }
  }
}
