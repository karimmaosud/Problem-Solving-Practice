package com.mirak.leetcode.individual.medium;

public class FindTheCelebrity {

  private static boolean knows(int a, int b) {
    return false;
  }

  public static int findCelebrity(int n) {
    int c = 0;
    for (int i = 1; i < n; i++) {
      if (!knows(i, c)) {
        c = i;
      }
    }
    for (int i = 0; i < n; i++) {
      if (i == c) {
        continue;
      }
      if (knows(c, i) || !knows(i, c)) {
        return -1;
      }
    }
    return c;
  }
}
