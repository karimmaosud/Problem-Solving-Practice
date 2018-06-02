package com.mirak.leetcode.individual.medium;

import java.util.LinkedList;
import java.util.List;

class SpiralMatrix {
  private int[] rowInc =	 	{0, 1, 0, -1};
  private int[] columnInc = 	{1, 0, -1, 0};
  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> list = new LinkedList<>();
    if(matrix.length == 0) {
      return list;
    }
    int n = matrix.length;
    int m = matrix[0].length;
    boolean[][] vis = new boolean[n][m];
    int index = 0;
    int i = 0, j = 0;
    while(list.size() < n * m) {
      list.add(matrix[i][j]);
      vis[i][j] = true;
      int i_ = i + rowInc[index];
      int j_ = j + columnInc[index];
      if(i_ == n || i_ < 0 || j_ == m || j_ < 0 || vis[i_][j_]) {
        index = (index + 1) % rowInc.length;
        i_ = i + rowInc[index];
        j_ = j + columnInc[index];
      }
      i = i_;
      j = j_;
    }
    return list;
  }
}
