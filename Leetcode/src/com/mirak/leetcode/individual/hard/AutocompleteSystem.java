package com.mirak.leetcode.individual.hard;

import java.util.*;
import java.util.stream.Collectors;

class AutocompleteSystem {

  private class RankedWord implements Comparable<RankedWord> {

    int id;
    String word;
    int rank;

    RankedWord(int id, String word, int rank) {
      this.id = id;
      this.word = word;
      this.rank = rank;
    }

    @Override
    public int compareTo(RankedWord o) {
      return this.rank > o.rank ? -1 : this.rank < o.rank ? 1 : this.word.compareTo(o.word);
    }
  }

  private class Node {

    Node[] c;
    Set<Integer> set;

    Node() {
      this.c = new Node[27];
      this.set = new HashSet<>();
    }

    void insertId(int id) {
      set.remove(id);
      set.add(id);
      if (set.size() > 3) {
        adjustSet();
      }
    }

    void adjustSet() {
      ArrayList<RankedWord> words = new ArrayList<>();
      for (int id : set) {
        words.add(rankedWords.get(id));
      }
      Collections.sort(words);
      set.remove(words.get(words.size() - 1).id);
    }
  }


  private class Trie {

    Node root;

    Trie() {
      this.root = new Node();
    }


    void updateTrieWithWord(String word, int id) {
      Node current = trie.root;
      for (char a : word.toCharArray()) {
        int i = charMapping(a);
        if (current.c[i] == null) {
          current.c[i] = new Node();
        }
        current = current.c[i];
        current.insertId(id);
      }
    }
  }

  private Map<Integer, RankedWord> rankedWords;
  private Map<String, Integer> wordId;
  private Trie trie;
  private StringBuilder builder;
  private Node runnerTrieNode;

  private ArrayList<String> emptyList;

  public AutocompleteSystem(String[] sentences, int[] times) {
    rankedWords = new HashMap<>();
    wordId = new HashMap<>();
    trie = new Trie();
    init(sentences, times);
    builder = new StringBuilder();
    runnerTrieNode = trie.root;
    emptyList = new ArrayList<>();
  }


  public List<String> input(char c) {
    if (c == '#') {
      String current = builder.toString();
      int id = getWordId(current);

      builder = new StringBuilder();
      runnerTrieNode = trie.root;
      if (!rankedWords.containsKey(id)) {
        wordId.put(current, id);
        rankedWords.put(id, new RankedWord(id, current, 1));
      } else {
        // increase rank.
        rankedWords.get(id).rank++;
      }
      trie.updateTrieWithWord(current, id);
      return emptyList;
    } else {
      builder.append(c);

      int i = charMapping(c);

      if (runnerTrieNode == null) {
        return emptyList;
      }
      runnerTrieNode = runnerTrieNode.c[i];
      if (runnerTrieNode == null) {
        return emptyList;
      }
      ArrayList<RankedWord> suggestions = new ArrayList<>();
      for (int id : runnerTrieNode.set) {
        suggestions.add(rankedWords.get(id));
      }
      Collections.sort(suggestions);
      return suggestions.stream().map(rankedWord -> rankedWord.word).collect(Collectors.toList());
    }
  }

  private void init(String[] sentences, int[] times) {
    for (int i = 0; i < sentences.length; i++) {
      int id = getWordId(sentences[i]);
      rankedWords.put(id, new RankedWord(id, sentences[i], times[i]));
      wordId.put(sentences[i], id);
    }
    for (int id : rankedWords.keySet()) {
      trie.updateTrieWithWord(rankedWords.get(id).word, id);
    }
  }

  private int charMapping(char a) {
    return a == ' ' ? 26 : a - 'a';
  }

  private int getWordId(String word) {
    return wordId.getOrDefault(word, wordId.size());
  }
}