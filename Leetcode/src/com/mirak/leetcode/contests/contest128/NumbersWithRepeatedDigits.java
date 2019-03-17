package com.mirak.leetcode.contests.contest128;

public class NumbersWithRepeatedDigits {

  public int numDupDigitsAtMostN(int N) {
    if (N <= 10) {
      return 0;
    }
    int numDigits = getNumDigits(N);
    int uniqueDigits = 0;
    for (int i = 1; i < numDigits; i++) {
      uniqueDigits += getUniqueNumsWithLengthN(i);
    }
    int strictCount = getStrictCount(getDigitsArrayFromNum(N, numDigits), 0, 0);
    return N - uniqueDigits - strictCount;
  }

  private int getStrictCount(int[] digits, int mask, int index) {
    if (index == digits.length) {
      return 1;
    }
    int maxChosen = 0;
    for (int i = index == 0 ? 1 : 0; i <= digits[index]; i++) {
      if ((mask & (1 << i)) == 0) {
        maxChosen++;
      }
    }

    if (maxChosen == 0) {
      return 0;
    }

    if ((mask & (1 << digits[index])) != 0) {
      return maxChosen * getCountFromChoseSoFar(index + 1, digits.length - index - 1);
    }

    return (maxChosen - 1) * getCountFromChoseSoFar(index + 1, digits.length - index - 1)
        + getStrictCount(
        digits, mask | (1 << digits[index]), index + 1);
  }

  private int[] getDigitsArrayFromNum(int num, int numDigits) {
    int[] digits = new int[numDigits];
    for (int i = numDigits - 1; i >= 0; i--) {
      digits[i] = num % 10;
      num /= 10;
    }
    return digits;
  }

  private int getCountFromChoseSoFar(int chosen, int len) {
    if (len == 0) {
      return 1;
    }
    int res = 1;
    int mul = 10 - chosen;
    for (int i = 0; i < len; i++) {
      res *= mul;
      mul--;
    }
    return res;
  }

  private int getUniqueNumsWithLengthN(int n) {
    int res = 9;
    int mul = 9;
    for (int i = 0; i < n - 1; i++) {
      res *= mul;
      mul--;
    }
    return res;
  }

  private int getNumDigits(int num) {
    int digits = 0;
    while (num != 0) {
      digits++;
      num /= 10;
    }
    return digits;
  }

}
