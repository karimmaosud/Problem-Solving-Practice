package com.mirak.leetcode.individual.hard;

public class ShortestPalindrome {
  public String shortestPalindrome(String s) {
    StringBuilder builder = new StringBuilder(s);
    builder.append('$');
    for (int i = s.length() - 1; i >= 0; i--) {
      builder.append(s.charAt(i));
    }
    int[] pi = new int[builder.length() + 1];
    for (int i = 1; i < builder.length(); i++) {
      int j = pi[i - 1];
      while (j > 0 && builder.charAt(i) != builder.charAt(j)) {
        j = pi[j - 1];
      }
      if (builder.charAt(j) == builder.charAt(i)) {
        j++;
      }
      pi[i] = j;
    }
    int added = s.length() - pi[builder.length() - 1];
    StringBuilder res = new StringBuilder(s).reverse();
    for (int i = 0; i < added; i++) {
      res.append(s.charAt(s.length() - added + i));
    }
    return res.reverse().toString();
  }
}
