package com.mirak.leetcode.individual.medium;

public class GameOfLife {
  private int[] rowInc = 		  {1, -1, 0, 0, 1, 1, -1, -1};
  private int[] columnInc = 	{0, 0, 1, -1, 1, -1, 1, -1};
  public void gameOfLife(int[][] board) {
    int n = board.length;
    if(n == 0) {
      return;
    }
    int m = board[0].length;
    for(int i = 0;i < n; i++) {
      for(int j = 0; j < m; j++) {
        int live = 0;
        for(int k = 0; k < rowInc.length; k++) {
          int i_ = i + rowInc[k];
          int j_ = j + columnInc[k];
          if(i_ < 0 || j_ < 0 || i_ == n || j_ == m) {
            continue;
          }
          if(isLive(i_, j_, board)) {
            live++;
          }
        }
        if(board[i][j] == 0) {
          if(live == 3) {
            board[i][j] = 2;
          }
        }else {
          if(live < 2 || live > 3) {
            board[i][j] = 3;
          }
        }
      }
    }
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < m; j++) {
        if(board[i][j] == 2 || board[i][j] == 3) {
          board[i][j] ^= 3;
        }
      }
    }
  }

  private boolean isLive(int i, int j, int[][] board) {
    return board[i][j] == 1 || board[i][j] == 3;
  }
}

