package com.mirak.leetcode.individual.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PalindromePairs {

  private static class TrieNode {
    TrieNode[] c;
    List<Integer> passedWords;
    int wordEnd;

    TrieNode() {
      c = new TrieNode[26];
      passedWords = new ArrayList<>();
      wordEnd = -1;
    }
  }

  private static class Trie {
    TrieNode root;
    Trie() {
      root = new TrieNode();
    }

    void insertSuffix(String word, int wordIndex) {
      TrieNode current = root;

      current.passedWords.add(wordIndex);

      for(int i = word.length() - 1; i >= 0; i--) {
        if(current.c[word.charAt(i) - 'a'] == null) {
          current.c[word.charAt(i) - 'a'] = new TrieNode();
        }
        current = current.c[word.charAt(i) - 'a'];
        current.passedWords.add(wordIndex);
      }

      current.wordEnd = wordIndex;
    }
  }

  public static List<List<Integer>> palindromePairs(String[] words) {
    Map<Integer, boolean[]> prefixPalindrome = new HashMap<>();
    Map<Integer, boolean[]> suffixPalindrome = new HashMap<>();
    Trie trie = new Trie();
    insertWordsToTrie(trie, words);
    initMaps(prefixPalindrome, suffixPalindrome, words);
    List<List<Integer>> res = new LinkedList<>();

    for(int i = 0; i < words.length; i++) {
      processWord(words, i, trie, prefixPalindrome, suffixPalindrome, res);
    }
    return res;
  }

  private static void insertWordsToTrie(Trie trie, String[] words) {
    for(int i = 0; i < words.length; i++) {
      trie.insertSuffix(words[i], i);
    }
  }

  private static void initMaps (Map<Integer, boolean[]> prefixPalindrome, Map<Integer, boolean[]> suffixPalindrome, String[] words) {
    for(int idx = 0; idx < words.length; idx++) {
      // odd palindromes
      boolean[] prefixArray = new boolean[words[idx].length()];
      boolean[] suffixArray = new boolean[words[idx].length()];

      for(int i = 0; i < words[idx].length(); i++) {
        prefixArray[i] = isPalindrome(words[idx], 0, i);
        suffixArray[i] = isPalindrome(words[idx], i, words[idx].length() - 1);
      }
      prefixPalindrome.put(idx, prefixArray);
      suffixPalindrome.put(idx, suffixArray);
    }
  }

  private static boolean isPalindrome (String word, int i, int j) {
    while(i < j && word.charAt(i) == word.charAt(j)) {
      i++;
      j--;
    }
    return i >= j;
  }

  private static void processWord(String[] words, int wordIndex, Trie trie, Map<Integer, boolean[]> prefixPalindrome, Map<Integer, boolean[]> suffixPalindrome, List<List<Integer>> res) {
    TrieNode current = trie.root;
    int i;
    for(i = 0; i < words[wordIndex].length(); i++) {

      if(current.c[words[wordIndex].charAt(i) - 'a'] == null) {
        // Can't move forward with index i, because no matching suffix found at any other word.
        return;
      }

      current = current.c[words[wordIndex].charAt(i) - 'a'];
      if(current.wordEnd != -1) {
        // Some word ends here.
        if(current.wordEnd == wordIndex) {
          // words[wordIndex] is palindrome itself, check for empty string.
          if(trie.root.wordEnd != -1) {
            res.add(generatePair(wordIndex, trie.root.wordEnd));
          }
        }else{
          if(i + 1 == words[wordIndex].length() || suffixPalindrome.get(wordIndex)[i + 1]) {
            res.add(generatePair(wordIndex, current.wordEnd));
          }
        }
      }
    }

    for(int idx: current.passedWords) {
      if(idx == wordIndex) {
        continue;
      }
      // words[idx] is some word which shares the same prefix with words[wordIndex]
      int prefixIndex = words[idx].length() - words[wordIndex].length() - 1;
      // prefixIndex = -1 means that words[idx] == words[wordIndex] --> contradiction (unique).
      if(prefixIndex != -1 && prefixPalindrome.get(idx)[prefixIndex]) {
        res.add(generatePair(wordIndex, idx));
      }
    }
  }

  private static List<Integer> generatePair(int first, int second) {
    List<Integer> list = new LinkedList<>();
    list.add(first);
    list.add(second);
    return list;
  }

  public static void main(String[] args) {
    String[] words = {"a", "abba"};
    System.out.println(palindromePairs(words));
  }
}
