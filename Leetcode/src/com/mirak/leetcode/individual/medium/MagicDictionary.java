package com.mirak.leetcode.individual.medium;

import java.util.*;

public class MagicDictionary {


  /**
   * Initialize your data structure here.
   */

  Set<String> words;
  Map<String, Integer> wordCount;
  public MagicDictionary() {
    words = new HashSet<>();
    wordCount = new HashMap<>();
  }

  /**
   * Build a dictionary through a list of words
   */
  public void buildDict(String[] dict) {
    for (String word : dict) {
      char[] wordChars = word.toCharArray();
      for (int i = 0; i < wordChars.length; i++) {
        char a = wordChars[i];
        wordChars[i] = '*';
        String neighbour = new String(wordChars);
        wordCount.put(neighbour, wordCount.getOrDefault(neighbour, 0) + 1);
        wordChars[i] = a;
      }
      words.add(word);
    }
  }

  /**
   * Returns if there is any word in the trie that equals to the given word after modifying exactly one character
   */
  public boolean search(String word) {
    char[] wordChars = word.toCharArray();
    for (int i = 0; i < wordChars.length; i++) {
      char a = wordChars[i];
      wordChars[i] = '*';
      String neighbour = new String(wordChars);
      int count = wordCount.getOrDefault(neighbour, 0);
      if (count > 1 || count == 1 && !words.contains(word)) {
        return true;
      }
      wordChars[i] = a;
    }
    return false;
  }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */


