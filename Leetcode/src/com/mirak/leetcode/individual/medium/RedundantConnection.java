package com.mirak.leetcode.individual.medium;

public class RedundantConnection {

  public int[] findRedundantConnection(int[][] edges) {
    int n = edges.length;
    int[] p = new int[n + 1];
    int[] rank = new int[n + 1];
    for (int i = 0; i <= n; ++i) {
      p[i] = i;
    }
    for (int[] edge : edges) {
      if (!union(edge[0], edge[1], p, rank)) {
        return edge;
      }
    }
    return null;
  }

  private int findParent(int i, int[] p) {
    return p[i] == i ? i : (p[i] = findParent(p[i], p));
  }

  private boolean union(int i, int j, int[] p, int[] rank) {
    int pi = findParent(i, p);
    int pj = findParent(j, p);
    if (pi == pj) {
      return false;
    }
    if (rank[pi] > rank[pj]) {
      p[pj] = pi;
    } else {
      p[pi] = pj;
      if (rank[pi] == rank[pj]) {
        rank[pj]++;
      }
    }
    return true;
  }
}
