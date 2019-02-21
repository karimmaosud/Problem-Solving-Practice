package com.mirak.leetcode.individual.easy;

public class RepeatedSubstringPattern {

  public boolean repeatedSubstringPattern(String s) {
    int n = s.length();
    if (n == 0) {
      return false;
    }
    int[] phi = new int[n];
    for (int i = 1; i < n; i++) {
      int j = phi[i - 1];
      while (j > 0 && s.charAt(j) != s.charAt(i)) {
        j = phi[j - 1];
      }
      if (s.charAt(j) == s.charAt(i)) {
        phi[i] = ++j;
      }
    }
    return phi[n - 1] > 0 && n % (n - phi[n - 1]) == 0;
  }
}
