package com.mirak.leetcode.individual.hard;

import java.util.*;

public class NumbersAtMostNGivenDigitSet {

  public int atMostNGivenDigitSet(String[] D, int N) {
    List<Integer> numDigits = intToDigits(N);
    int count = 0;
    for (int i = 1; i < numDigits.size(); ++i) {
      count += (int) Math.pow(D.length, i);
    }

    for (int i = 0; i < numDigits.size(); ++i) {
      int idx = binSearch(D, numDigits.get(i));
      if (idx == -1) {
        return count;
      }
      boolean smallerDigit = Integer.parseInt(D[idx]) < numDigits.get(i);
      int numSmaller = smallerDigit ? idx + 1 : idx;
      count += numSmaller * (int) Math.pow(D.length, numDigits.size() - i - 1);
      if (smallerDigit) {
        return count;
      }
    }
    return count + 1;
  }

  private List<Integer> intToDigits(int N) {
    List<Integer> digits = new ArrayList<>();
    while (N > 0) {
      digits.add(N % 10);
      N /= 10;
    }
    Collections.reverse(digits);
    return digits;
  }

  private int binSearch(String[] D, int d) {
    int low = 0;
    int high = D.length - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (Integer.parseInt(D[mid]) > d) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return high;
  }
}
