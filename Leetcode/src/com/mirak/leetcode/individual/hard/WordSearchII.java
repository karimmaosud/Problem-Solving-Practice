package com.mirak.leetcode.individual.hard;

import java.util.*;

public class WordSearchII {

  private int[] rowInc = {0, 0, 1, -1};
  private int[] columnInc = {1, -1, 0, 0};

  private class Node {

    Node[] c;
    int index;

    Node() {
      c = new Node[26];
      index = -1;
    }
  }

  private class Trie {

    Node root;

    Trie() {
      root = new Node();
    }

    void addWord(String word, int index) {
      Node current = root;
      for (char a : word.toCharArray()) {
        if (current.c[a - 'a'] == null) {
          current.c[a - 'a'] = new Node();
        }
        current = current.c[a - 'a'];
      }
      current.index = index;
    }
  }

  public List<String> findWords(char[][] board, String[] words) {

    Trie trie = new Trie();
    buildTrie(words, trie);

    List<String> foundWords = new LinkedList<>();

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        dfs(i, j, board, trie.root, words, foundWords);
      }
    }
    return foundWords;
  }


  private void dfs(int i, int j, char[][] board, Node trieNode, String[] words,
      List<String> foundWords) {
    if (i < 0 || j < 0 || i == board.length || j == board[0].length || board[i][j] == '#' ||
        trieNode.c[board[i][j] - 'a'] == null) {
      return;
    }

    char c = board[i][j];

    if (trieNode.c[c - 'a'].index != -1) {
      foundWords.add(words[trieNode.c[c - 'a'].index]);
      trieNode.c[c - 'a'].index = -1;
    }

    board[i][j] = '#';
    for (int k = 0; k < rowInc.length; k++) {
      dfs(i + rowInc[k], j + columnInc[k], board, trieNode.c[c - 'a'], words, foundWords);
    }
    board[i][j] = c;
  }

  private void buildTrie(String[] words, Trie trie) {
    for (int i = 0; i < words.length; i++) {
      trie.addWord(words[i], i);
    }
  }
}
