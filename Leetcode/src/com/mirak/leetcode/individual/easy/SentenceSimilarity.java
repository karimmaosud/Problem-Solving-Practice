package com.mirak.leetcode.individual.easy;

import java.util.*;

public class SentenceSimilarity {

  public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {

    if (words1.length != words2.length) {
      return false;
    }

    Map<String, Set<String>> similarWords = new HashMap<>();
    for (String[] pair : pairs) {
      similarWords.putIfAbsent(pair[0], new HashSet<>());
      similarWords.get(pair[0]).add(pair[1]);
    }
    for (int i = 0; i < words1.length; i++) {
      if (words1[i].equals(words2[i]) || isSimilarWords(words1[i], words2[i], similarWords)
          || isSimilarWords(words2[i], words1[i], similarWords)) {
        continue;
      }
      return false;
    }
    return true;
  }

  private boolean isSimilarWords(String a, String b, Map<String, Set<String>> similarWords) {
    return similarWords.containsKey(a) && similarWords.get(a).contains(b);
  }


}
