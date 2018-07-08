package com.mirak.leetcode.individual.easy;

public class NthDigit {

  public int findNthDigit(int n) {

    if (n < 10) {
      return n;
    }

    long len = 0;
    long nine = 9;
    int digitsCount = 1;
    while (len + nine * digitsCount <= n) {
      len += nine * digitsCount;
      nine *= 10;
      digitsCount++;
    }
    long rem = n - len;
    if (rem == 0) {
      return 9;
    }
    int index = (int) (rem % digitsCount);
    long num = nine / 9 + (rem / digitsCount);
    if (index == 0) {
      index = digitsCount;
      num--;
    }
    return getDigitAtIndex(num, index);
  }

  private int getDigitAtIndex(long num, int index) {
    return ("" + num).charAt(index - 1) - '0';
  }
}
