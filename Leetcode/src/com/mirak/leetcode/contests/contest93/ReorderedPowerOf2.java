package com.mirak.leetcode.contests.contest93;

public class ReorderedPowerOf2 {
  public boolean reorderedPowerOf2(int N) {
    for(int i = 0; i < 31; i++) {
      if(isAnagramNumbers(1 << i, N)) {
        return true;
      }
    }
    return false;
  }

  private boolean isAnagramNumbers(int a, int b) {
    int[] digitCount = new int[10];
    while(a != 0) {
      digitCount[a % 10]++;
      a /= 10;
    }
    while(b != 0) {
      digitCount[b % 10]--;
      b /= 10;
    }
    for(int i = 0; i < 10; i++) {
      if(digitCount[i] != 0) {
        return false;
      }
    }
    return true;
  }
}
