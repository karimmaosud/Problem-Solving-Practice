package com.mirak.leetcode.individual.medium;

public class CustomSortString {
  public String customSortString(String S, String T) {
    int[] charCount = new int[26];

    for (char c : T.toCharArray()) {
      charCount[c - 'a']++;
    }
    StringBuilder builder = new StringBuilder();
    for (char c : S.toCharArray()) {
      for (int i = 0; i < charCount[c - 'a']; i++) {
        builder.append(c);
      }
      charCount[c - 'a'] = 0;
    }

    for (char c = 'a'; c <= 'z'; c++) {
      for (int i = 0; i < charCount[c - 'a']; i++) {
        builder.append(c);
      }
    }
    return builder.toString();
  }
}
