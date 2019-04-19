package com.mirak.leetcode.contests.contest132;

public class DivisorGame {
  public boolean divisorGame(int N) {
    boolean[] canWin = new boolean[N + 1];
    for (int i = 2; i <= N; ++i) {
      for (int j = 1; j < i; ++j) {
        if (i % j == 0) {
          canWin[i] |= !canWin[i - j];
        }
      }
    }
    return canWin[N];
  }
}
