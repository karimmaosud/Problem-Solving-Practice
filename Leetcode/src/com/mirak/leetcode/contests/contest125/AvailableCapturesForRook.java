package com.mirak.leetcode.contests.contest125;

public class AvailableCapturesForRook {

  public int numRookCaptures(char[][] board) {
    int ans = 0;
    int n = board.length;
    int m = board[0].length;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (board[i][j] == 'R') {
          ans += getCapturedPawns(board, i, i + 1, j + 1, m, 1, 1);
          ans += getCapturedPawns(board, i, i + 1, j - 1, -1, 1, -1);
          ans += getCapturedPawns(board, i + 1, n, j, j + 1, 1, 1);
          ans += getCapturedPawns(board, i - 1, -1, j, j + 1, -1, 1);
        }
      }
    }
    return ans;
  }

  private int getCapturedPawns(char[][] board, int startRow, int endRow, int startColumn, int endColumn, int rInc, int cInc) {
    int captured = 0;
    for (int i = startRow; i != endRow; i += rInc) {
      for (int j = startColumn; j != endColumn; j += cInc) {
        System.out.println("passed by: " + i + " " + j);
        if (board[i][j] == 'p') {
          captured++;
          board[i][j] = 'V';
          return captured;
        } else if (board[i][j] != '.') {
          return captured;
        }
      }
    }
    return captured;
  }
}
