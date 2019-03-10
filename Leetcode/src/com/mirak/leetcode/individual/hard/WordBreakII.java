package com.mirak.leetcode.individual.hard;

import java.util.*;

public class WordBreakII {

  private class Node {

    Node[] c;
    boolean wordEnd;

    Node() {
      this.c = new Node[26];
      this.wordEnd = false;
    }
  }

  private class Trie {

    Node root;

    Trie() {
      root = new Node();
    }

    void insertWord(String word) {
      Node current = root;
      for (char a : word.toCharArray()) {
        if (current.c[a - 'a'] == null) {
          current.c[a - 'a'] = new Node();
        }
        current = current.c[a - 'a'];
      }
      current.wordEnd = true;
    }
  }

  public List<String> wordBreak(String s, List<String> wordDict) {
    Trie trie = getTrieFromDict(wordDict);
    ArrayList<String>[] dp = new ArrayList[s.length()];
    return solve(s, 0, trie, dp);
  }

  private ArrayList<String> solve(String s, int start, Trie trie, ArrayList<String>[] dp) {

    if (dp[start] != null) {
      return dp[start];
    }

    Node current = trie.root;
    StringBuilder builder = new StringBuilder();
    ArrayList<String> list = new ArrayList<>();
    for (int i = start; i < s.length(); i++) {
      current = current.c[s.charAt(i) - 'a'];
      if (current == null) {
        break;
      }
      builder.append(s.charAt(i));
      if (current.wordEnd) {
        if (i == s.length() - 1) {
          list.add(builder.toString());
          continue;
        }
        ArrayList<String> next = solve(s, i + 1, trie, dp);
        for (String right : next) {
          list.add(builder.toString() + " " + right);
        }
      }

    }
    return dp[start] = list;
  }


  private Trie getTrieFromDict(List<String> dict) {
    Trie trie = new Trie();
    for (String word : dict) {
      trie.insertWord(word);
    }
    return trie;
  }

}
