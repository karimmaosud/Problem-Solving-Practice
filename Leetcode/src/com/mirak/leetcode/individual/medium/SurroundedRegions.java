package com.mirak.leetcode.individual.medium;

public class SurroundedRegions {

  private int[] rowInc = {1, -1, 0, 0};
  private int[] columnInc = {0, 0, 1, -1};

  public void solve(char[][] board) {
    int n = board.length;
    if (n == 0) {
      return;
    }
    int m = board[0].length;
    boolean[][] vis = new boolean[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (board[i][j] == 'O' && !vis[i][j]) {
          if (dfs(i, j, board, vis)) {
            color(i, j, board);
          }
        }
      }
    }
  }


  private void color(int i, int j, char[][] board) {
    if (board[i][j] == 'X') {
      return;
    }
    board[i][j] = 'X';
    for (int k = 0; k < rowInc.length; k++) {
      color(i + rowInc[k], j + columnInc[k], board);
    }
  }

  private boolean dfs(int i, int j, char[][] board, boolean[][] vis) {
    if (i < 0 || j < 0 || i == board.length || j == board[0].length) {
      return false;
    }
    if (board[i][j] == 'X' || vis[i][j]) {
      return true;
    }
    vis[i][j] = true;
    boolean ans = true;
    for (int k = 0; k < rowInc.length; k++) {
      ans &= dfs(i + rowInc[k], j + columnInc[k], board, vis);
    }
    return ans;
  }


}
