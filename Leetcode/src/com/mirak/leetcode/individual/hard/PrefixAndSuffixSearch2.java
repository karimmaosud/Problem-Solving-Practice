package com.mirak.leetcode.individual.hard;

public class PrefixAndSuffixSearch2 {

  class WordFilter {

    private class Node {

      Node[] c;
      int weight;

      Node() {
        this.c = new Node[27];
        this.weight = -1;
      }
    }

    private class Trie {

      Node root;

      Trie() {
        this.root = new Node();
      }

      void insert(String word, int weight) {
        Node current = this.root;
        for (char a : word.toCharArray()) {
          int mapping = getCharMapping(a);
          if (current.c[mapping] == null) {
            current.c[mapping] = new Node();
          }
          current = current.c[mapping];
        }
        current.weight = weight;
      }

      int searchPrefixSuffix(String word) {
        Node current = this.root;
        for (char a : word.toCharArray()) {
          int mapping = getCharMapping(a);
          if (current.c[mapping] == null) {
            return -1;
          }
          current = current.c[mapping];
        }
        return current.weight;
      }
    }

    private Trie trie;

    public WordFilter(String[] words) {
      this.trie = new Trie();
      for (int i = 0; i < words.length; i++) {
        insertAllPrefixSuffixCombinations(words[i], trie, i);
      }
    }

    public int f(String prefix, String suffix) {
      return this.trie.searchPrefixSuffix(prefix + "$" + suffix);
    }

    private void insertAllPrefixSuffixCombinations(String word, Trie trie, int weight) {
      int m = word.length();
      for (int i = 0; i <= m; i++) {
        for (int j = 0; j <= m; j++) {
          String prefix = word.substring(0, i);
          String suffix = word.substring(m - j, m);
          trie.insert(prefix + "$" + suffix, weight);
        }
      }
    }

    private int getCharMapping(char a) {
      return a >= 'a' && a <= 'z' ? a - 'a' : 26;
    }
  }
}
