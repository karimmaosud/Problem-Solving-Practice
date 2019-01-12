package com.mirak.leetcode.individual.medium;

public class ZigZagConversion {

  public String convert(String s, int numRows) {
    if (numRows >= s.length()) {
      return s;
    }
    StringBuilder[] rowStrs = new StringBuilder[numRows];
    for (int i = 0; i < numRows; i++) {
      rowStrs[i] = new StringBuilder();
    }
    int r = 0;
    int inc = 1;
    int index = 0;
    while (index < s.length()) {
      rowStrs[r].append(s.charAt(index));
      r += inc;
      if (r == numRows) {
        r = numRows == 1 ? 0 : numRows - 2;
        inc = -1;
      } else if (r < 0) {
        r = numRows == 1 ? 0 : 1;
        inc = 1;
      }
      index++;
    }
    for (int i = 1; i < numRows; i++) {
      rowStrs[0].append(rowStrs[i]);
    }
    return rowStrs[0].toString();
  }

}
