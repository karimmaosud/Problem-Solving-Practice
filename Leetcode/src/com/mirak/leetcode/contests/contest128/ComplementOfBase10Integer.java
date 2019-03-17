package com.mirak.leetcode.contests.contest128;

public class ComplementOfBase10Integer {
  public int bitwiseComplement(int N) {
    if (N == 0) {
      return 1;
    }
    int res = 0;
    int base = 0;
    while (N > 0) {
      if ((N & 1) == 0) {
        res |= (1 << base);
      }
      base++;
      N >>= 1;
    }
    return res;
  }

}
