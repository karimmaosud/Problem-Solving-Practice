package com.mirak.leetcode.individual.hard;

import java.util.*;

public class WordSquares {

  private class Node {

    Node[] c;
    ArrayList<Integer> passedWords;

    Node() {
      c = new Node[26];
      passedWords = new ArrayList<>();
    }
  }

  private class Trie {

    Node root;

    Trie() {
      root = new Node();
    }

    void insertWord(String word, int i) {
      Node current = root;
      root.passedWords.add(i);
      for (char a : word.toCharArray()) {
        if (current.c[a - 'a'] == null) {
          current.c[a - 'a'] = new Node();
        }
        current.c[a - 'a'].passedWords.add(i);
        current = current.c[a - 'a'];
      }
    }
  }

  public List<List<String>> wordSquares(String[] words) {
    Trie trie = getTrieFromWords(words);
    int n = words[0].length();
    char[][] square = new char[n][n];
    List<List<String>> res = new LinkedList<>();
    solve(trie, 0, words, square, n, res);
    return res;
  }


  private void solve(Trie trie, int column, String[] words, char[][] square, int n,
      List<List<String>> res) {

    if (column == n) {
      appendSquare(square, res);
      return;
    }

    Node node = trie.root;
    for (int i = 0; i < column; i++) {
      node = node.c[square[i][column] - 'a'];
    }

    for (int idx : node.passedWords) {
      for (int j = 0; j < n; j++) {
        square[column][j] = words[idx].charAt(j);
      }

      if (canPlaceWord(square, trie, column, n)) {
        solve(trie, column + 1, words, square, n, res);
      }
    }
  }

  private boolean canPlaceWord(char[][] square, Trie trie, int column, int n) {
    Node current;
    for (int i = 0; i < n; i++) {
      current = trie.root;
      for (int j = 0; j <= column; j++) {
        if (current.c[square[j][i] - 'a'] == null) {
          return false;
        }
        current = current.c[square[j][i] - 'a'];
      }
    }
    return true;
  }

  private Trie getTrieFromWords(String[] words) {
    Trie trie = new Trie();
    for (int i = 0; i < words.length; i++) {
      trie.insertWord(words[i], i);
    }
    return trie;
  }

  private void appendSquare(char[][] square, List<List<String>> res) {
    List<String> squareList = new LinkedList<>();
    for (char[] word : square) {
      squareList.add(new String(word));
    }
    res.add(squareList);
  }

}
