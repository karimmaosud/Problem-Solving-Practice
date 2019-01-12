package com.mirak.leetcode.individual.medium;

public class StringToInteger_atoi {

  public int myAtoi(String str) {
    long maxPositive = (1L << 31) - 1;
    long minNegative = -(1L << 31);

    long current = 0;
    int i = 0;
    int mul = 1;
    while (i < str.length() && str.charAt(i) == ' ') {
      i++;
    }
    if (i == str.length() || (!isDigit(str.charAt(i)) && !isOp(str.charAt(i)))) {
      return 0;
    }

    if (isOp(str.charAt(i))) {
      mul = str.charAt(i) == '-' ? -1 : 1;
      i++;
    }

    if (i == str.length() || !isDigit(str.charAt(i))) {
      return 0;
    }

    while (i < str.length() && isDigit(str.charAt(i))) {
      current = current * 10 + (str.charAt(i) - '0');
      if (mul * current > maxPositive) {
        return (int) maxPositive;
      }
      if(mul * current < minNegative) {
        return (int) minNegative;
      }
      i++;
    }
    if (mul * current <= maxPositive && mul * current >= minNegative) {
      return (int) (mul * current);
    }
    return (int) minNegative;
  }

  private boolean isOp(char a) {
    return a == '+' || a == '-';
  }

  private boolean isDigit(char a) {
    return Character.isDigit(a);
  }
}
