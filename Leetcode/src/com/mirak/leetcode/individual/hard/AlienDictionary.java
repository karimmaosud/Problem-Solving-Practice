package com.mirak.leetcode.individual.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;

public class AlienDictionary {

  public String alienOrder(String[] words) {
    ArrayList<String> wordsList = new ArrayList<>();
    for (String word : words) {
      wordsList.add(word);
    }

    ArrayList<Integer>[] adjList = new ArrayList[26];
    for (int i = 0; i < adjList.length; i++) {
      adjList[i] = new ArrayList<>();
    }
    boolean[] visChar = new boolean[26];

    if (!processList(wordsList, 0, adjList, visChar)) {
      return "";
    }

    int[] vis = new int[26];
    Arrays.fill(vis, -1);

    LinkedList<Character> orderedChars = new LinkedList<>();
    for (int i = 0; i < 26; i++) {
      if (visChar[i] && vis[i] == -1) {
        if (!dfs(i, adjList, vis, orderedChars)) {
          return "";
        }
      }
    }
    StringBuilder builder = new StringBuilder();
    while (!orderedChars.isEmpty()) {
      builder.append(orderedChars.removeFirst());
    }
    return builder.toString();
  }

  private boolean processList(ArrayList<String> list, int hashIndex, ArrayList<Integer>[] adjList,
      boolean[] visChar) {

    if (list.size() == 0) {
      return true;
    }

    LinkedHashMap<Character, ArrayList<String>> map = new LinkedHashMap<>();
    for (int i = 0; i < list.size(); i++) {
      String word = list.get(i);
      char c = word.charAt(hashIndex);
      if (i > 0 && list.get(i - 1).charAt(hashIndex) != c && map.containsKey(c)) {
        return false;
      }
      map.putIfAbsent(c, new ArrayList<>());
      if (hashIndex + 1 < word.length()) {
        map.get(c).add(word);
      }
    }

    Set<Character> orderedChars = map.keySet();
    Iterator iterator = orderedChars.iterator();
    char prev = (char) iterator.next();
    while (iterator.hasNext()) {
      char current = (char) iterator.next();
      // make edge from prev to current
      adjList[prev - 'a'].add(current - 'a');
      prev = current;
    }

    for (char c : orderedChars) {
      visChar[c - 'a'] = true;
      processList(map.get(c), hashIndex + 1, adjList, visChar);
    }
    return true;
  }

  /*
   * vis[i] = -1 --> unvisited node.
   * vis[i] =  1 --> currently visiting node.
   * vis[i] =  2 --> finished visiting node.
   */
  private boolean dfs(int node, ArrayList<Integer>[] adjList, int[] vis,
      LinkedList<Character> orderedChars) {

    vis[node] = 1;

    for (int i = 0; i < adjList[node].size(); i++) {
      int v = adjList[node].get(i);
      if (vis[v] == -1) {
        if (!dfs(v, adjList, vis, orderedChars)) {
          return false;
        }
      } else if (vis[v] == 1) {
        return false;
      }
    }
    orderedChars.addFirst((char) (node + 'a'));
    vis[node] = 2;
    return true;
  }
}
