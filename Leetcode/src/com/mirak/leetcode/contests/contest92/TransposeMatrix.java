package com.mirak.leetcode.contests.contest92;

public class TransposeMatrix {
  public int[][] transpose(int[][] A) {
    int n = A.length;
    int m = A[0].length;
    int [][] A_T = new int[m][n];
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < m; j++) {
        A_T[j][i] = A[i][j];
      }
    }
    return A_T;
  }
}
