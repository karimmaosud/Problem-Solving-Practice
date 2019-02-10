package com.mirak.leetcode.individual.medium;

public class DecodeWays {

  public int numDecodings(String s) {
    int[] mem = new int[s.length()];
    int n = s.length();
    mem[n - 1] = s.charAt(n - 1) == '0' ? 0 : 1;
    for (int i = n - 2; i >= 0; i--) {
      if (s.charAt(i) == '0') {
        continue;
      }
      if ((s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0') <= 26) {
        mem[i] += i + 2 < n ? mem[i + 2] : 1;
      }
      mem[i] += mem[i + 1];
    }
    return mem[0];
  }
}
