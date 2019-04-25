package com.mirak.leetcode.contests.contest133;

public class StreamOfCharacters {

  class StreamChecker {

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

      void insertWord(String word) {
        Node current = root;
        for (int i = word.length() - 1; i >= 0; --i) {
          int idx = word.charAt(i) - 'a';
          if (current.c[idx] == null) {
            current.c[idx] = new Node();
          }
          current = current.c[idx];
        }
        current.wordEnd = true;
      }

      boolean suffixExist(StringBuilder builder) {
        Node current = root;
        for (int i = builder.length() - 1; i >= 0; --i) {
          int idx = builder.charAt(i) - 'a';
          if (current.c[idx] == null) {
            return false;
          }
          current = current.c[idx];
          if (current.wordEnd) {
            return true;
          }
        }
        return false;
      }
    }

    private Trie trie;
    private StringBuilder builder;

    public StreamChecker(String[] words) {
      trie = new Trie();
      for (String word : words) {
        trie.insertWord(word);
      }
      builder = new StringBuilder();
    }

    public boolean query(char letter) {
      builder.append(letter);
      return trie.suffixExist(builder);
    }
  }

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */


}
