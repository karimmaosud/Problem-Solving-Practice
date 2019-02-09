package com.mirak.leetcode.individual.medium;

import java.util.*;

public class UniqueWordAbbreviation {

  class ValidWordAbbr {

    Set<String> words;
    private Map<String, Integer> abbreviationCount;

    public ValidWordAbbr(String[] dictionary) {
      abbreviationCount = new HashMap<>();
      words = new HashSet<>();

      for (String word : dictionary) {
        if (word.length() == 0) {
          continue;
        }
        if (words.contains(word)) {
          continue;
        }
        words.add(word);
        char first = word.charAt(0);
        char last = word.charAt(word.length() - 1);
        String code = "" + first + "#" + word.length() + "#" + last;
        abbreviationCount.put(code, abbreviationCount.getOrDefault(code, 0) + 1);
      }
    }

    public boolean isUnique(String word) {
      if (word.length() == 0) {
        return true;
      }
      char first = word.charAt(0);
      char last = word.charAt(word.length() - 1);
      String code = "" + first + "#" + word.length() + "#" + last;
      if (!abbreviationCount.containsKey(code)) {
        return true;
      }
      return abbreviationCount.get(code) == 1 && words.contains(word);
    }
  }
}
