package com.mirak.leetcode.individual.hard;

import java.util.*;

public class PrefixAndSuffixSearch {

  class WordFilter {

    private class TrieNode {

      Map<Character, TrieNode> child;
      int weight;

      TrieNode() {
        child = new HashMap<>();
        weight = 0;
      }
    }

    private class Trie {

      TrieNode root;

      Trie() {
        root = new TrieNode();
      }

      void insert(String word, int weight) {
        TrieNode node = this.root;
        node.weight = weight;
        for (char c : word.toCharArray()) {
          if (node.child.get(c) == null) {
            node.child.put(c, new TrieNode());
          }
          node = node.child.get(c);
          node.weight = weight;
        }
      }
    }

    private Trie trie;

    public WordFilter(String[] words) {
      trie = new Trie();
      for (int i = 0; i < words.length; i++) {
        int l = words[i].length();
        for (int j = 0; j <= l; j++) {
          String suffix = words[i].substring(l - j, l);
          trie.insert(suffix + "#" + words[i], i);
        }
      }
    }

    public int f(String prefix, String suffix) {
      String searchWord = suffix + "#" + prefix;
      TrieNode node = trie.root;
      for (char c : searchWord.toCharArray()) {
        if (node.child.get(c) == null) {
          return -1;
        }
        node = node.child.get(c);
      }
      return node.weight;
    }
  }
}
