package com.mirak.leetcode.individual.medium;

public class TicTacToe {

  private int[] rowCount;
  private int[] columnCount;
  private int diag1;
  private int diag2;
  private int n;

  /**
   * Initialize your data structure here.
   */
  public TicTacToe(int n) {
    this.n = n;
    rowCount = new int[n];
    columnCount = new int[n];
  }

  /**
   * Player {player} makes a move at ({row}, {col}).
   *
   * @param row The row of the board.
   * @param col The column of the board.
   * @param player The player, can be either 1 or 2.
   * @return The current winning condition, can be either:
   * 0: No one wins.
   * 1: Player 1 wins.
   * 2: Player 2 wins.
   */
  public int move(int row, int col, int player) {
    int add = player == 1 ? 1 : -1;
    addToArray(rowCount, row, add);
    addToArray(columnCount, col, add);

    if (row == col && (diag1 >= 0 && add > 0 || diag1 <= 0 && add < 0)) {
      diag1 += add;
    }

    if (row + col == n - 1 && (diag2 >= 0 && add > 0 || diag2 <= 0 && add < 0)) {
      diag2 += add;
    }

    return Math.abs(rowCount[row]) == n || Math.abs(columnCount[col]) == n || Math.abs(diag1) == n
        || Math.abs(diag2) == n ? player : 0;
  }


  private void addToArray(int[] count, int i, int add) {
    if ((count[i] >= 0 && add > 0) || (count[i] <= 0 && add < 0)) {
      count[i] += add;
    }
  }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */


