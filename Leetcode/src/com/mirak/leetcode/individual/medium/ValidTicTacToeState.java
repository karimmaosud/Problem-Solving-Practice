package com.mirak.leetcode.individual.medium;

public class ValidTicTacToeState {

  public static boolean validTicTacToe(String[] board) {
    int xCount = getCharCountInBoard(board, 'X');
    int oCount = getCharCountInBoard(board, 'O');
    if (oCount > xCount || xCount - oCount > 1) {
      return false;
    }
    return validWinningState(board, xCount, oCount);
  }

  private static int getCharCountInBoard(String[] board, char c) {
    int count = 0;
    for (String row : board) {
      for (char a : row.toCharArray()) {
        count += a == c ? 1 : 0;
      }
    }
    return count;
  }

  private static boolean validWinningState(String[] board, int xCount, int oCount) {
    int n = board.length;
    int[] rowVals = new int[n];
    int[] colVals = new int[n];

    int dig1 = 0, dig2 = 0;
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        int charValue = getCharValue(board[i].charAt(j));

        if (charValue == -1) {
          // empty cell.
          rowVals[i] = colVals[j] = -1;
        } else {
          rowVals[i] = rowVals[i] == -1 ? -1 : rowVals[i] | charValue;
          colVals[j] |= colVals[j] == -1 ? -1 : colVals[j] | charValue;
        }
        if (i == j) {
          dig1 = charValue == -1 || dig1 == -1 ? -1 : dig1 | charValue;
        }
        if (i + j == n - 1) {
          dig2 = charValue == -1 || dig2 == -1 ? -1 : dig2 | charValue;
        }
      }
    }
    int winner = 0;
    for (int i = 0; i < n; ++i) {
      int[] vals = {rowVals[i], colVals[i]};
      for (int val : vals) {
        if (val != -1 && val != 3) {
          winner |= val;
        }
      }
    }

    int[] vals = {dig1, dig2};
    for (int val : vals) {
      if (val != -1 && val != 3) {
        winner |= val;
      }
    }

    if (winner == 3) {
      return false;
    }

    if (winner == 1) {
      return xCount - oCount == 1;
    } else if (winner == 2) {
      return oCount - xCount == 0;
    }
    // no winner
    return true;
  }

  private static int getCharValue(char c) {
    return c == 'X' ? 1 : c == 'O' ? 2 : -1;
  }

  public static void main(String[] args) {
    System.out.println(validTicTacToe(new String[]{"XOX","OOX","XO "}));
  }

}
