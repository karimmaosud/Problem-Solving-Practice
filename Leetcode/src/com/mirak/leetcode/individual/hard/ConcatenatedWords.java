package com.mirak.leetcode.individual.hard;

import java.util.*;

public class ConcatenatedWords {

  private class Node {

    Node[] c;
    boolean wordEnd;

    Node() {
      c = new Node[26];
      wordEnd = false;
    }
  }

  private class Trie {

    Node root;

    Trie() {
      root = new Node();
    }
  }

  public List<String> findAllConcatenatedWordsInADict(String[] words) {
    List<String> res = new LinkedList<>();
    Trie trie = getWordsTrie(words);
    for (String word : words) {
      if (word.length() == 0) {
        continue;
      }
      int[] dp = new int[word.length()];
      Arrays.fill(dp, -1);
      if (canDivide(word, 0, trie, dp) == 1) {
        res.add(word);
      }
    }
    return res;
  }

  private Trie getWordsTrie(String[] words) {
    Trie trie = new Trie();
    for (String word : words) {
      Node current = trie.root;
      for (char c : word.toCharArray()) {
        if (current.c[c - 'a'] == null) {
          current.c[c - 'a'] = new Node();
        }
        current = current.c[c - 'a'];
      }
      current.wordEnd = true;
    }
    return trie;
  }


  private int canDivide(String word, int index, Trie trie, int[] dp) {
    if (index == word.length()) {
      return 1;
    }
    if (dp[index] != -1) {
      return dp[index];
    }
    Node current = trie.root;
    for (int i = index; i < word.length() - (index == 0 ? 1 : 0); i++) {
      char c = word.charAt(i);
      if (current.c[c - 'a'] == null) {
        return dp[index] = 0;
      }
      current = current.c[c - 'a'];
      if (current.wordEnd && canDivide(word, i + 1, trie, dp) == 1) {
        return dp[index] = 1;
      }
    }
    return dp[index] = 0;
  }

}
