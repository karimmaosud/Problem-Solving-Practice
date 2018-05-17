package com.mirak.leetcode.contests.contest84;

public class FlippingAnImage {
  public int[][] flipAndInvertImage(int[][] A) {
    int n = A.length;
    if(n == 0){
      return A;
    }
    int m = A[0].length;
    for(int i = 0; i < A.length; i++) {
      for(int j = 0; j <= A[i].length / 2; j++) {
        if(j == m - j - 1) {
          A[i][j] ^= 1;
          continue;
        }

        if(j > m - j - 1){
          break;
        }

        int temp = A[i][j];
        A[i][j] = A[i][m - j - 1] ^ 1;
        A[i][m - j - 1] = temp ^ 1;
      }
    }
    return A;
  }
}
