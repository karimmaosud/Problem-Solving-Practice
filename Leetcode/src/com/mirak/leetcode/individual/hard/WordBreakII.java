package com.mirak.leetcode.individual.hard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WordBreakII {

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

  public List<String> wordBreak(String s, List<String> wordDict) {
    List<String> res = new LinkedList<>();
    if(s.length() == 0) {
      return res;
    }
    Trie trie = new Trie();
    for(String word: wordDict) {
      trie.insertWord(word);
    }
    ArrayList<String>[] dp = new ArrayList[s.length() + 1];
    return solve(0, s, trie, dp);
  }

  private ArrayList<String> solve(int start, String s, Trie trie, ArrayList<String>[] dp) {
    if(start == s.length()) {
      return null;
    }

    if(dp[start] != null) {
      return dp[start];
    }

    TrieNode node = trie.root;

    StringBuilder builder = new StringBuilder();

    ArrayList<String> ans = new ArrayList<>();

    for(int i = start; i < s.length(); i++) {
      if(node.c[s.charAt(i) - 'a'] == null) {
        break;
      }
      builder.append(s.charAt(i));
      node = node.c[s.charAt(i) - 'a'];
      if(node.wordEnd) {
        List<String> suffixList = solve(i + 1, s, trie, dp);
        if(suffixList == null || suffixList.isEmpty()) {
          if(i + 1 == s.length()){
            ans.add(builder.toString());
          }
          continue;
        }
        for(String suffix: suffixList) {
          StringBuilder dummyBuilder = new StringBuilder();
          dummyBuilder.append(builder).append(" ").append(suffix);
          ans.add(dummyBuilder.toString());
        }
      }
    }

    return dp[start] = ans;
  }
}
