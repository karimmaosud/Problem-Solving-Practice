package com.mirak.leetcode.individual.medium;

public class LongestPalindromicSubstring {

  public String longestPalindrome(String s) {
    int n = s.length();
    if (n == 0) {
      return s;
    }
    int[] dOdd = new int[n];
    int[] dEven = new int[n];
    char[] chars = s.toCharArray();
    manacher(chars, dOdd, 0);
    manacher(chars, dEven, 1);
    int maxLen = 0;
    int l = -1, r = -1;
    for (int i = 0; i < n; i++) {
      if (2 * dOdd[i] - 1 > maxLen) {
        maxLen = 2 * dOdd[i] - 1;
        l = i - dOdd[i] + 1;
        r = i + dOdd[i] - 1;
      }

      if (2 * dEven[i] > maxLen) {
        maxLen = 2 * dEven[i];
        l = i - dEven[i];
        r = i + dEven[i] - 1;
      }
    }
    return s.substring(l, r + 1);
  }

  private void manacher(char[] chars, int[] d, int even) {
    int n = chars.length;
    int l = 0, r = -1;
    for (int i = 0; i < n; i++) {
      int k = i > r ? 1 ^ even : Math.min(d[l + r - i + even], r - i);
      while (i - k - even >= 0 && i + k < n && chars[i - k - even] == chars[i + k]) {
        k++;
      }
      d[i] = k--;
      if (i + k > r) {
        r = i + k;
        l = i - k - even;
      }
    }
  }
}
