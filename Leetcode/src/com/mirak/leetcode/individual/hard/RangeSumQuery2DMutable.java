package com.mirak.leetcode.individual.hard;

public class RangeSumQuery2DMutable {

}


class NumMatrix {
  private int[][] matrix;
  private long[][] tree;
  public NumMatrix(int[][] matrix) {
    this.matrix = matrix;
    if(matrix.length > 0) {
      tree = new long[matrix.length + 2][matrix[0].length + 2];
      for (int i = 0; i < matrix.length; i++) {
        for(int j = 0; j < matrix[i].length; j++) {
          updateTree(i + 1, j + 1, matrix[i][j]);
        }
      }
    }
  }

  public void update(int row, int col, int val) {
    int oldValue = matrix[row][col];
    int inc = val - oldValue;
    updateTree(row + 1, col + 1, inc);
    matrix[row][col] = val;
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    row1++;
    col1++;
    row2++;
    col2++;
    return (int) (getSum(row2, col2) - getSum(row1 - 1, col2) - getSum(row2, col1 -1) + getSum(row1 - 1, col1 - 1));
  }

  private void updateTree(int row, int column, int val) {
    while (row < tree.length) {
      int col = column;
      while(col < tree[0].length) {
        tree[row][col] += val;
        col += (col & -col);
      }
      row += (row & -row);
    }
  }

  private long getSum(int row, int column) {
    long sum = 0;
    while (row > 0) {
      int col = column;
      while(col > 0){
        sum += tree[row][col];
        col -= (col & -col);
      }
      row -= (row & -row);
    }
    return sum;
  }
}


