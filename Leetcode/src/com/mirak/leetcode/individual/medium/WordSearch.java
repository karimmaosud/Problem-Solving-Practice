package com.mirak.leetcode.individual.medium;

public class WordSearch {

  private int[] rowInc = {1, -1, 0, 0};
  private int[] columnInc = {0, 0, 1, -1};


  public boolean exist(char[][] board, String word) {
    if (board[0].length == 0) {
      return word.length() == 0;
    }
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (exist(i, j, 0, word, board)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean exist(int i, int j, int idx, String word, char[][] board) {
    if (idx == word.length()) {
      return true;
    }
    if (i < 0 || j < 0 || i == board.length || j == board[0].length || board[i][j] != word
        .charAt(idx)) {
      return false;
    }
    board[i][j] = '#';
    for (int k = 0; k < rowInc.length; k++) {
      if (exist(i + rowInc[k], j + columnInc[k], idx + 1, word, board)) {
        board[i][j] = word.charAt(idx);
        return true;
      }
    }
    board[i][j] = word.charAt(idx);
    return false;
  }


}
