package com.mirak.leetcode.individual.medium;

import java.util.List;

public class WordBreak {

  private class TrieNode {
    TrieNode[] c;
    boolean wordEnd;
    TrieNode() {
      c = new TrieNode[26];
      wordEnd = false;
    }
  }

  private class Trie {
    TrieNode root;

    Trie() {
      root = new TrieNode();
    }

    void insertWord(String word) {
      TrieNode current = root;
      for(int i = 0; i < word.length(); i++) {
        if(current.c[word.charAt(i) - 'a'] == null) {
          current.c[word.charAt(i) - 'a'] = new TrieNode();
        }
        current = current.c[word.charAt(i) - 'a'];
      }
      current.wordEnd = true;
    }
  }
  public boolean wordBreak(String s, List<String> wordDict) {

    if(s.length() == 0) {
      return wordDict.contains(s);
    }
    Trie trie = new Trie();
    for(String word: wordDict) {
      trie.insertWord(word);
    }
    boolean[] reached = new boolean[s.length() + 1];
    for(int i = 0; i < s.length(); i++) {
      if(i == 0 || reached[i - 1]) {
        walk(trie, s, i, reached);
      }
    }
    return reached[s.length() - 1];
  }

  private void walk(Trie trie, String s, int startIdx, boolean[] reached) {
    TrieNode current = trie.root;
    while(current != null && startIdx < s.length()) {
      current = current.c[s.charAt(startIdx) - 'a'];
      if(current != null && current.wordEnd) {
        reached[startIdx] = true;
      }
      startIdx++;
    }
  }

}
