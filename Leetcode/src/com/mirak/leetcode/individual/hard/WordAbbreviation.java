package com.mirak.leetcode.individual.hard;

import java.util.*;

public class WordAbbreviation {

  private class Node {

    Node[] c;
    int passedWords;

    Node() {
      c = new Node[26];
      passedWords = 0;
    }
  }

  private class Trie {

    Node root;

    Trie() {
      root = new Node();
    }
  }

  public List<String> wordsAbbreviation(List<String> dict) {

    Map<String, String> wordAbbreviation = new HashMap<>();
    Map<String, ArrayList<String>> abbreviationMap = getAbbreviationMap(dict);

    List<String> abbreviations = new LinkedList<>();

    for (String word : dict) {
      if (word.length() <= 3) {
        abbreviations.add(word);
        continue;
      }

      if (wordAbbreviation.containsKey(word)) {
        abbreviations.add(wordAbbreviation.get(word));
        continue;
      }

      String abbreviation = getWordAbbreviation(word);
      if (abbreviationMap.get(abbreviation).size() == 1) {
        abbreviations.add(abbreviation);
        continue;
      }
      processAbbreviation(abbreviation, abbreviationMap, wordAbbreviation);
      abbreviations.add(wordAbbreviation.get(word));
    }

    return abbreviations;
  }

  private Map<String, ArrayList<String>> getAbbreviationMap(List<String> dict) {
    Map<String, ArrayList<String>> abbreviationMap = new HashMap<>();
    for (String word : dict) {
      if (word.length() <= 3) {
        continue;
      }
      String abbreviation = getWordAbbreviation(word);
      ArrayList<String> mappedAbbreviations = abbreviationMap
          .getOrDefault(abbreviation, new ArrayList<>());
      mappedAbbreviations.add(word);
      abbreviationMap.put(abbreviation, mappedAbbreviations);
    }
    return abbreviationMap;
  }

  private String getWordAbbreviation(String word) {
    return "" + word.charAt(0) + (word.length() - 2) + word.charAt(word.length() - 1);
  }

  private void processAbbreviation(String abbreviation,
      Map<String, ArrayList<String>> abbreviationMap, Map<String, String> wordAbbreviation) {

    Trie trie = getTrieFromDict(abbreviationMap.get(abbreviation));
    for (String word : abbreviationMap.get(abbreviation)) {
      Node current = trie.root;
      StringBuilder builder = new StringBuilder();

      int i;
      for (i = 0; i < word.length(); i++) {
        char c = word.charAt(i);
        current = current.c[c - 'a'];
        builder.append(c);
        if (current.passedWords == 1) {
          break;
        }
      }
      if (word.length() - i - 2 > 1) {
        builder.append(word.length() - i - 2);
        builder.append(word.charAt(word.length() - 1));
      } else {
        builder.append(word.substring(i + 1));
      }
      wordAbbreviation.put(word, builder.toString());
    }
  }

  private Trie getTrieFromDict(List<String> dict) {
    Trie trie = new Trie();
    for (String word : dict) {
      Node current = trie.root;
      for (char c : word.toCharArray()) {
        if (current.c[c - 'a'] == null) {
          current.c[c - 'a'] = new Node();
        }
        current = current.c[c - 'a'];
        current.passedWords++;
      }
    }
    return trie;
  }
}
