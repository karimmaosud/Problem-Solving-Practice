package com.mirak.leetcode.individual.hard;

public class SuperEggDrop {

  public int superEggDrop(int K, int N) {
    int[][] mem = new int[K + 1][N + 1];

    for (int k = 1; k <= K; k++) {
      int lastIndex = 1;
      for (int n = 1; n <= N; n++) {
        if (k == 1) {
          mem[k][n] = n;
          continue;
        }
        if (n < 3) {
          mem[k][n] = n;
          continue;
        }
        // n is at least 3
        mem[k][n] = 1 + Math
            .min(mem[k][n - 1], Math.max(mem[k][lastIndex], mem[k - 1][n - lastIndex - 1]));
        if (mem[k][n] > mem[k][n - 1]) {
          lastIndex = n - 1;
        }
      }
    }

    return mem[K][N];
  }
}
