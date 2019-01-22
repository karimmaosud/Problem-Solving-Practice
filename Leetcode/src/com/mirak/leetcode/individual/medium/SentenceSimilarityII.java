package com.mirak.leetcode.individual.medium;

import java.util.*;

public class SentenceSimilarityII {

  // 1. Map each string to a unique integer.
  // 2. Use union-find to union the pairs
  // 3. loop through the word length and check if the two words are on the same set.

  public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {

    if (words1.length != words2.length) {
      return false;
    }

    Map<String, Integer> wordToInt = new HashMap<>();
    int id = 1;
    for (String[] pair : pairs) {
      wordToInt.putIfAbsent(pair[0], id++);
      wordToInt.putIfAbsent(pair[1], id++);
    }

    int[] p = new int[id];
    int[] rank = new int[id];

    init(p);
    makeTree(pairs, wordToInt, p, rank);

    for (int i = 0; i < words1.length; i++) {
      if (words1[i].equals(words2[i])) {
        continue;
      }
      if (!wordToInt.containsKey(words1[i]) || !wordToInt.containsKey(words2[i]) || !isSameSet(
          wordToInt.get(words1[i]), wordToInt.get(words2[i]), p)) {
        return false;
      }
    }
    return true;
  }

  private void init(int[] p) {
    for (int i = 0; i < p.length; i++) {
      p[i] = i;
    }
  }

  private void makeTree(String[][] pairs, Map<String, Integer> wordToInt, int[] p, int[] rank) {
    for (String[] pair : pairs) {
      union(wordToInt.get(pair[0]), wordToInt.get(pair[1]), p, rank);
    }
  }


  private int findParent(int i, int[] p) {
    if (p[i] == i) {
      return i;
    }
    p[i] = findParent(p[i], p);
    return p[i];
  }

  private void union(int i, int j, int[] p, int[] rank) {
    int pi = findParent(i, p);
    int pj = findParent(j, p);
    if (rank[pi] > rank[pj]) {
      p[pj] = pi;
    } else {
      p[pi] = pj;
      if (rank[pi] == rank[pj]) {
        rank[pj]++;
      }
    }
  }

  private boolean isSameSet(int i, int j, int[] p) {
    return findParent(i, p) == findParent(j, p);
  }
}
