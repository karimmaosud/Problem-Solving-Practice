package com.mirak.leetcode.individual.medium;

public class PermutationInString {

  public boolean checkInclusion(String s1, String s2) {
    int n = s1.length(), m = s2.length();
    if (m < n) {
      return false;
    }
    int[] charCount = new int[26];
    for (char a : s1.toCharArray()) {
      charCount[a - 'a']++;
    }
    int[] runnerCharCount = new int[26];
    for (int i = 0; i < m; ++i) {
      runnerCharCount[s2.charAt(i) - 'a']++;
      if (i < n - 1) {
        continue;
      }
      if (i >= n) {
        runnerCharCount[s2.charAt(i - n) - 'a']--;
      }
      if (check(charCount, runnerCharCount)) {
        return true;
      }
    }
    return false;
  }

  private boolean check(int[] charCount, int[] runnerCharCount) {
    for (int i = 0; i < charCount.length; ++i) {
      if (charCount[i] != runnerCharCount[i]) {
        return false;
      }
    }
    return true;
  }


}
