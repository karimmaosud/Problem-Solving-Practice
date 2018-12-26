package com.mirak.leetcode.individual.medium;

import java.util.*;

public class FindAndReplacePattern {

  public List<String> findAndReplacePattern(String[] words, String pattern) {
    List<String> res = new LinkedList<>();
    for (String word : words) {
      if (match(word, pattern)) {
        res.add(word);
      }
    }
    return res;
  }

  private boolean match(String word, String pattern) {

    if (word.length() != pattern.length()) {
      return false;
    }

    int[] charMap = new int[26];
    Arrays.fill(charMap, -1);

    boolean[] seen = new boolean[26];

    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      char p = pattern.charAt(i);

      if (charMap[c - 'a'] == -1) {
        if (seen[p - 'a']) {
          return false;
        }
        seen[p - 'a'] = true;
        charMap[c - 'a'] = p;
      } else if (charMap[c - 'a'] != p) {
        return false;
      }
    }

    return true;
  }
}
