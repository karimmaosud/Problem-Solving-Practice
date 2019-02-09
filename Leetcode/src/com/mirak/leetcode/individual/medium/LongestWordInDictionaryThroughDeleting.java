package com.mirak.leetcode.individual.medium;

import java.util.*;

public class LongestWordInDictionaryThroughDeleting {

  public String findLongestWord(String s, List<String> d) {
    Collections.sort(d);
    String res = "";
    for (String word : d) {
      if (canMakeWord(s, word)) {
        if (word.length() > res.length()) {
          res = word;
        }
      }
    }
    return res;
  }

  private boolean canMakeWord(String s, String word) {
    int i = 0, j = 0;
    while (i < s.length() && j < word.length()) {
      if (s.charAt(i) == word.charAt(j)) {
        i++;
        j++;
      } else {
        i++;
      }
    }
    return j == word.length();
  }

}
