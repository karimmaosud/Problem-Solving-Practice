package com.mirak.leetcode.contests.contest91;

public class ScoreAfterFlippingMatrix {
  public int matrixScore(int[][] A) {
    if(A.length == 0) {
      return 0;
    }
    int n = A.length;
    int m = A[0].length;
    for(int i = 0; i < n; i++) {
      if(A[i][0] == 0) {
        flipRow(A, i);
      }
    }
    int res = n * (1 << (m - 1));
    for(int j = 1; j < m; j++) {
      int oneCount = 0;
      for(int i = 0; i < n; i++) {
        oneCount += A[i][j];
      }
      int max = Math.max(oneCount, n - oneCount);
      res += max * (1 << (m - j - 1));
    }
    return res;
  }

  private void flipRow(int[][] A, int row) {
    for(int j = 0; j < A[row].length; j++) {
      A[row][j] ^= 1;
    }
  }
}
