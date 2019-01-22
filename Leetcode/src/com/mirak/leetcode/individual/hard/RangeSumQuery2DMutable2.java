package com.mirak.leetcode.individual.hard;

public class RangeSumQuery2DMutable2 {

  static class NumMatrix {

    int[][] matrix;
    int[][] tree;
    int n, m;

    public NumMatrix(int[][] matrix) {
      this.matrix = matrix;
      n = matrix.length;
      if (n == 0) {
        return;
      }
      m = matrix[0].length;
      if (m == 0) {
        return;
      }
      tree = new int[n + 1][m + 1];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          update(i, j, tree, matrix[i][j]);
        }
      }
    }

    public void update(int row, int col, int val) {
      update(row, col, tree, val - matrix[row][col]);
      matrix[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
      return read(row2, col2, tree) - read(row2, col1 - 1, tree) - read(row1 - 1, col2, tree)
          + read(row1 - 1, col1 - 1, tree);
    }

    private void update(int r, int c, int[][] tree, int delta) {
      r++;
      c++;
      while (r <= n) {
        // update c.
        int j = c;
        while (j <= m) {
          tree[r][j] += delta;
          j += (j & -j);
        }
        r += (r & -r);
      }
    }

    private int read(int r, int c, int[][] tree) {
      r++;
      c++;
      int sum = 0;
      while (r > 0) {
        int j = c;
        while (j > 0) {
          sum += tree[r][j];
          j -= (j & -j);
        }
        r -= (r & -r);
      }
      return sum;
    }
  }

//  public static void main(String[] args) {
//    int[][] matrix = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7},
//        {1, 0, 3, 0, 5}};
//    NumMatrix numMatrix = new NumMatrix(matrix);
//
//    System.out.println("ere");
//  }

}
