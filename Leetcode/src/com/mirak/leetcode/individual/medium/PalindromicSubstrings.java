package com.mirak.leetcode.individual.medium;

public class PalindromicSubstrings {

  public int countSubstrings(String s) {
    int[] oddCount = new int[s.length()];
    int[] evenCount = new int[s.length()];
    runManachersAlgorithm(s, oddCount, 0);
    runManachersAlgorithm(s, evenCount, 1);
    int res = 0;
    for (int i = 0; i < s.length(); i++) {
      res += oddCount[i] + evenCount[i];
    }
    return res;
  }

  private void runManachersAlgorithm(String s, int[] d, int even) {
    int n = s.length();
    for (int i = 0, l = 0, r = -1; i < n; i++) {
      int k = i > r ? even ^ 1 : Math.min(d[l + r - i + even], r - i + 1);
      while (0 <= i - k - even && i + k < n && s.charAt(i - k - even) == s.charAt(i + k)) {
        k++;
      }
      d[i] = k--;
      if (i + k > r) {
        l = i - k - even;
        r = i + k;
      }
    }
  }
}
