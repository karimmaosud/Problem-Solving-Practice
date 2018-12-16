package com.mirak.leetcode.contests.contest114;

public class VerifyingAnAlienDictionary {
  public boolean isAlienSorted(String[] words, String order) {
    for (int i = 0; i < words.length - 1; i++) {
      if (compare(words[i], words[i + 1], order) > 0) {
        return false;
      }
    }
    return true;
  }
  private int compare(String s1, String s2, String order) {
    for (int i = 0; i < s1.length(); i++) {
      if (i == s2.length()) {
        return 1;
      }
      if (s1.charAt(i) == s2.charAt(i)) {
        continue;
      }
      return order.indexOf(s1.charAt(i)) - order.indexOf(s2.charAt(i));
    }

    return 0;
  }
}
