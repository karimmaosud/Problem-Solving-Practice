package com.mirak.leetcode.individual.medium;

public class CandyCrush {

  public int[][] candyCrush(int[][] board) {

    int n = board.length;
    int m = board[0].length;

    boolean end = false;
    while (!end) {
      for (int i = 0; i < n; i++) {
        end |= destroyRow(i, board, m);
      }
      for (int j = 0; j < m; j++) {
        end |= destroyColumn(j, board, n);
      }
      // If any cell has been destroyed, then end = true. We have to flip it again.
      end = !end;
      if (end) {
        continue;
      }

      removeDestroyedCells(board);

    }
    return board;
  }

  private boolean destroyRow(int row, int[][] board, int m) {

    boolean anyCellDestroyed = false;
    int column = 0;

    while (column + 2 < m) {

      if (board[row][column] == 0) {
        column++;
        continue;
      }

      if (equal(board, row, column, row, column + 1) && equal(board, row, column, row,
          column + 2)) {

        anyCellDestroyed = true;
        int runner = column;

        while (runner < m && equal(board, row, column, row, runner)) {
          board[row][runner] = -Math.abs(board[row][runner++]);
        }
        column = runner;

      } else {
        column++;
      }
    }
    return anyCellDestroyed;
  }

  private boolean destroyColumn(int column, int[][] board, int n) {

    boolean anyCellDestroyed = false;
    int row = 0;

    while (row + 2 < n) {

      if (board[row][column] == 0) {
        row++;
        continue;
      }

      if (equal(board, row, column, row + 1, column) && equal(board, row, column, row + 2,
          column)) {

        anyCellDestroyed = true;
        int runner = row;

        while (runner < n && equal(board, runner, column, row, column)) {
          board[runner][column] = -Math.abs(board[runner++][column]);
        }
        row = runner;
      } else {
        row++;
      }
    }
    return anyCellDestroyed;
  }

  private boolean equal(int[][] board, int i1, int j1, int i2, int j2) {
    return Math.abs(board[i1][j1]) == Math.abs(board[i2][j2]);
  }

  private void removeDestroyedCells(int[][] board) {
    for (int j = 0; j < board[0].length; j++) {

      int writePointer = board.length - 1;
      int readPointer = board.length - 1;

      while (readPointer >= 0) {
        if (board[readPointer][j] < 0) {
          readPointer--;
          continue;
        }
        board[writePointer--][j] = board[readPointer--][j];
      }

      while (writePointer >= 0) {
        board[writePointer--][j] = 0;
      }
    }
  }
}
