package com.mirak.leetcode.individual.easy;

import java.util.*;

public class RotatedDigits {

  public int rotatedDigits(int N) {
    Map<Integer, Integer> rotatedDigit = new HashMap<>();
    rotatedDigit.put(0, 0);
    rotatedDigit.put(1, 1);
    rotatedDigit.put(2, 5);
    rotatedDigit.put(5, 2);
    rotatedDigit.put(6, 9);
    rotatedDigit.put(8, 8);
    rotatedDigit.put(9, 6);

    int count = 0;
    for (int i = 1; i <= N; i++) {
      if (isGood(i, rotatedDigit)) {
        count++;
      }
    }
    return count;
  }

  private boolean isGood(int N, Map<Integer, Integer> rotatedDigit) {
    int savedN = N;
    int rotated = 0;
    int tenPower = 1;
    while (N != 0) {
      int d = N % 10;
      if (!rotatedDigit.containsKey(d)) {
        return false;
      }
      rotated = (tenPower * rotatedDigit.get(d)) + rotated;
      tenPower *= 10;
      N /= 10;
    }
    return rotated != savedN;
  }

}
