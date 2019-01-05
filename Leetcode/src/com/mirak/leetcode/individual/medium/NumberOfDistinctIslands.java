package com.mirak.leetcode.individual.medium;


public class NumberOfDistinctIslands {

  private int[] rowInc = {1, -1, 0, 0};
  private int[] colInc = {0, 0, 1, -1};

  private class Node {

    Node[] c;
    boolean pathEnd;

    Node() {
      c = new Node[5];
      pathEnd = false;
    }
  }

  private class Trie {

    Node root;

    Trie() {
      root = new Node();
    }
  }

  public int numDistinctIslands(int[][] grid) {
    int n = grid.length;
    if (n == 0) {
      return 0;
    }
    int m = grid[0].length;
    boolean[][] vis = new boolean[n][m];

    Trie trie = new Trie();
    int res = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == 1 && !vis[i][j]) {
          Node tailNode = dfs(i, j, n, m, grid, vis, trie.root);
          if (!tailNode.pathEnd) {
            tailNode.pathEnd = true;
            res++;
          }
        }
      }
    }
    return res;
  }

  private Node dfs(int i, int j, int n, int m, int[][] grid, boolean[][] vis, Node current) {
    vis[i][j] = true;
    for (int k = 0; k < rowInc.length; k++) {
      if (isValidCell(i + rowInc[k], j + colInc[k], n, m, grid, vis)) {
        if (current.c[k + 1] == null) {
          current.c[k + 1] = new Node();
        }
        current = dfs(i + rowInc[k], j + colInc[k], n, m, grid, vis, current.c[k + 1]);
      }
    }
    if (current.c[0] == null) {
      current.c[0] = new Node();
    }
    return current.c[0];
  }

  private boolean isValidCell(int i, int j, int n, int m, int[][] grid, boolean[][] vis) {
    return !(i < 0 || j < 0 || i == n || j == m || grid[i][j] == 0 || vis[i][j]);
  }
}
