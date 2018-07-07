package com.mirak.leetcode.individual.medium;

public class IntegerReplacement {
  public int integerReplacement(long n) {
    if(n == 0) {
      return 1;
    }

    if(n == 1) {
      return 0;
    }

    if(n == 3) {
      return 2;
    }

    if((n & 1) == 0) {
      return 1 + integerReplacement(n >> 1);
    }

    int plusOneTrailingZeros = getTrailingZerosCount(n + 1);
    int minusOneTrailingZeros = getTrailingZerosCount(n - 1);

    if(plusOneTrailingZeros > minusOneTrailingZeros) {
      return 1 + integerReplacement(n + 1);
    }

    return 1 + integerReplacement(n - 1);
  }

  private int getTrailingZerosCount(long n) {
    int trailingZeros = 0;
    while((n & 1) == 0) {
      trailingZeros++;
      n >>= 1;
    }
    return trailingZeros;
  }
}
