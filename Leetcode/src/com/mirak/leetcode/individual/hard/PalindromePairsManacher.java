package com.mirak.leetcode.individual.hard;

import java.util.*;

public class PalindromePairsManacher {

  private class Node {

    Node[] child;
    ArrayList<Integer> passedWords;
    int wordIndex;

    Node() {
      child = new Node[26];
      passedWords = new ArrayList<>();
      wordIndex = -1;
    }
  }

  private class Trie {

    Node root;

    Trie() {
      root = new Node();
    }

    void insertSuffix(String word, int index, Map<Integer, int[][]> manacherArrays) {
      Node current = root;
      for (int i = word.length() - 1; i >= 0; i--) {
        if (isPalindrome(0, i, manacherArrays.get(index))) {
          current.passedWords.add(index);
        }

        char c = word.charAt(i);
        if (current.child[c - 'a'] == null) {
          current.child[c - 'a'] = new Node();
        }
        current = current.child[c - 'a'];
      }
      current.passedWords.add(index);
      current.wordIndex = index;
    }
  }

  public List<List<Integer>> palindromePairs(String[] words) {
    Map<Integer, int[][]> manacherArrays = new HashMap<>();

    for (int i = 0; i < words.length; i++) {
      int[][] ds = new int[2][];
      ds[0] = manacher(words[i], 0);
      ds[1] = manacher(words[i], 1);
      manacherArrays.put(i, ds);
    }

    Trie trie = new Trie();
    insertSuffixesToTrie(trie, words, manacherArrays);
    List<List<Integer>> res = new LinkedList<>();
    processWords(words, trie, res, manacherArrays);
    return res;
  }

  private void insertSuffixesToTrie(Trie trie, String[] words,
      Map<Integer, int[][]> manacherArrays) {
    for (int i = 0; i < words.length; i++) {
      trie.insertSuffix(words[i], i, manacherArrays);
    }
  }


  private void processWords(String[] words, Trie trie, List<List<Integer>> res,
      Map<Integer, int[][]> manacherArrays) {
    for (int j = 0; j < words.length; j++) {
      String word = words[j];
      Node current = trie.root;
      int i;
      for (i = 0; i < word.length(); i++) {
        if (current.wordIndex != -1 && isPalindrome(i, word.length() - 1,
            manacherArrays.get(j))) {
          res.add(Arrays.asList(j, current.wordIndex));
        }
        char c = word.charAt(i);
        if (current.child[c - 'a'] == null) {
          break;
        }
        current = current.child[c - 'a'];
      }
      // current can not be null.
      if (i == word.length()) {
        // Matched the entire word.
        for (int index : current.passedWords) {
          if (index == j) {
            continue;
          }
          res.add(Arrays.asList(j, index));
        }
      }
    }
  }

  private boolean isPalindrome(int i, int j, int[][] ds) {
    int len = j - i + 1;
    int idx = i + len / 2;
    if ((len & 1) == 0) {
      return 2 * ds[1][idx] == len;
    } else {
      return 2 * ds[0][idx] - 1 == len;
    }
  }

  private int[] manacher(String s, int even) {
    int n = s.length();
    int l = 0, r = -1;
    int[] d = new int[n];
    for (int i = 0; i < s.length(); i++) {
      int k = i > r ? 1 ^ even : Math.min(d[l + r - i + even], r - i);
      while (i - k - even >= 0 && i + k < n && s.charAt(i - k - even) == s.charAt(i + k)) {
        k++;
      }
      d[i] = k--;
      if (i + k > r) {
        r = i + k;
        l = i - k - even;
      }
    }
    return d;
  }

}
