package com.mirak.leetcode.individual.hard;

import java.util.*;

public class AutocompleteSystem {

  private class StringWrapper {

    String sentence;
    int rank;

    StringWrapper(String sentence, int rank) {
      this.sentence = sentence;
      this.rank = rank;
    }
  }

  private class Node {

    Node[] c;
    ArrayList<Integer> indexes;
    int wordIndex;

    Node() {
      c = new Node[27];
      indexes = new ArrayList<>();
      wordIndex = -1;
    }
  }

  private class Trie {

    Node root;

    Trie() {
      root = new Node();
    }
  }

  private ArrayList<String> emptyList;
  private ArrayList<String> sentencesList;
  private ArrayList<Integer> timesList;
  private Trie trie;
  private Node runnerNode;
  private Map<String, StringWrapper> sentenceWrapperMap;
  private StringBuilder typedWord;

  public AutocompleteSystem(String[] sentences, int[] times) {

    emptyList = new ArrayList<>();
    sentencesList = stringArrayToList(sentences);
    timesList = intArrayToList(times);
    trie = new Trie();
    runnerNode = trie.root;
    sentenceWrapperMap = new HashMap<>();
    typedWord = new StringBuilder();

    insertSentencesToTrie();
  }

  public List<String> input(char c) {
    if (c == '#') {
      if (runnerNode.wordIndex == -1) {
        String st = typedWord.toString();
        addSentenceIndexToTrie(st, sentencesList.size());
        sentenceWrapperMap.put(st, new StringWrapper(st, 1));

        sentencesList.add(st);
        timesList.add(1);
      } else {
        sentenceWrapperMap.get(typedWord.toString()).rank++;
        timesList.set(runnerNode.wordIndex, timesList.get(runnerNode.wordIndex) + 1);
      }
      typedWord = new StringBuilder();
      runnerNode = trie.root;
      return emptyList;
    }
    typedWord.append(c);
    ArrayList<String> result = emptyList;
    int idx = charToIndex(c);
    if (runnerNode.c[idx] == null) {
      runnerNode.c[idx] = new Node();
    } else {
      result = getRankedSentences(runnerNode.c[idx]);
    }
    runnerNode = runnerNode.c[idx];
    return result;
  }

  private void addSentenceIndexToTrie(String word, int index) {
    Node current = trie.root;
    for (char c : word.toCharArray()) {
      int idx = charToIndex(c);
      current.c[idx].indexes.add(index);
      current = current.c[idx];
    }
    current.wordIndex = index;
  }

  private ArrayList<String> getRankedSentences(Node node) {
    PriorityQueue<StringWrapper> pq = new PriorityQueue<>(new Comparator<StringWrapper>() {
      @Override
      public int compare(StringWrapper o1, StringWrapper o2) {
        int rankDiff = o1.rank - o2.rank;
        return rankDiff != 0 ? rankDiff : -1 * o1.sentence.compareTo(o2.sentence);
      }
    });
    for (int index : node.indexes) {
      pq.add(sentenceWrapperMap.get(sentencesList.get(index)));
      if (pq.size() > 3) {
        pq.poll();
      }
    }
    ArrayList<String> res = new ArrayList<>();
    while (!pq.isEmpty()) {
      res.add(pq.poll().sentence);
    }
    Collections.reverse(res);
    return res;
  }

  private void insertSentencesToTrie() {
    for (int i = 0; i < sentencesList.size(); i++) {
      sentenceWrapperMap
          .put(sentencesList.get(i), new StringWrapper(sentencesList.get(i), timesList.get(i)));

      Node current = trie.root;
      for (char a : sentencesList.get(i).toCharArray()) {
        int idx = charToIndex(a);
        if (current.c[idx] == null) {
          current.c[idx] = new Node();
        }
        current = current.c[idx];
        current.indexes.add(i);
      }
      current.wordIndex = i;
    }
  }

  private int charToIndex(char a) {
    return a >= 'a' && a <= 'z' ? a - 'a' : 26;
  }

  private ArrayList<Integer> intArrayToList(int[] nums) {
    ArrayList<Integer> res = new ArrayList<>();
    for (int num : nums) {
      res.add(num);
    }
    return res;
  }

  private ArrayList<String> stringArrayToList(String[] strs) {
    ArrayList<String> res = new ArrayList<>();
    for (String str : strs) {
      res.add(str);
    }
    return res;
  }
}
