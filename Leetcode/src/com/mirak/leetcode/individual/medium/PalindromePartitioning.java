package com.mirak.leetcode.individual.medium;

import java.util.*;

public class PalindromePartitioning {

  public List<List<String>> partition(String s) {

    int n = s.length();
    if (n == 0) {
      return new ArrayList<>();
    }

    int[] dEven = new int[n];
    int[] dOdd = new int[n];
    runManacher(s, dOdd, 0);
    runManacher(s, dEven, 1);

    List<List<String>>[] dp = new ArrayList[n + 1];
    dp[n] = new ArrayList<>();
    for (int i = n - 1; i >= 0; --i) {
      dp[i] = new ArrayList<>();
      for (int j = i; j < n; ++j) {
        if (isPalindrome(i, j, ((j - i + 1) & 1) == 1 ? dOdd : dEven)) {
          String current = s.substring(i, j + 1);
          List<List<String>> partitions = dp[j + 1];
          if (partitions.isEmpty()) {
            List<String> newPartition = new ArrayList<>();
            newPartition.add(current);
            dp[i].add(newPartition);
            continue;
          }
          for (List<String> partition : partitions) {
            ArrayList<String> newPartition = new ArrayList<>();
            newPartition.add(current);
            newPartition.addAll(partition);
            dp[i].add(newPartition);
          }
        }
      }
    }
    return dp[0];
  }

  private void runManacher(String s, int[] d, int even) {
    int l = 0, r = -1;
    int n = s.length();
    for (int i = 0; i < n; ++i) {
      int k = i > r ? 1 ^ even : Math.min(d[l + r - i + even], r - i);
      while (i - k - even >= 0 && i + k < n && s.charAt(i - k - even) == s.charAt(i + k)) {
        k++;
      }
      d[i] = k--;
      if (i + k > r) {
        r = i + k;
        l = i - k - even;
      }
    }
  }


  private boolean isPalindrome(int i, int j, int[] d) {
    int evenPalindrome = (j + i) & 1;
    int len = 2 * d[(i + j) / 2 + evenPalindrome];
    if (evenPalindrome == 0) {
      len--;
    }
    return len >= j - i + 1;
  }
}
