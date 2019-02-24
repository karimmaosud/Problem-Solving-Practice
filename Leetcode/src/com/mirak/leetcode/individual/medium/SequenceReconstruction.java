package com.mirak.leetcode.individual.medium;

import java.util.*;

public class SequenceReconstruction {

  public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {

    int n = org.length;

    HashSet<Integer>[] adjList = new HashSet[n + 1];
    for (int i = 0; i < adjList.length; i++) {
      adjList[i] = new HashSet<>();
    }

    int[] inDegree = new int[n + 1];
    /*
      state = 0: node is not visited.
      state = 1: node is being visited.
      state = 2: node is visited.
     */
    int[] state = new int[n + 1];

    for (List<Integer> seq : seqs) {
      if (seq.isEmpty()) {
        continue;
      }
      int prev = seq.get(0);
      if (!validNumber(prev, n)) {
        return false;
      }
      state[prev] = 1;
      for (int i = 1; i < seq.size(); i++) {
        int next = seq.get(i);
        if (!validNumber(prev, n) || !validNumber(next, n)) {
          return false;
        }
        state[next] = 1;
        adjList[prev].add(next);
        inDegree[next]++;
        prev = next;
      }
    }

    int root = getRoot(inDegree);

    if (root == -1 || state[root] != 1) {
      return false;
    }

    Arrays.fill(state, 0);

    LinkedList<Integer> constructedSeq = new LinkedList<>();
    if (!dfs(root, adjList, state, constructedSeq) || constructedSeq.size() < n) {
      return false;
    }

    for (int num : org) {
      if (num != constructedSeq.removeFirst()) {
        return false;
      }
    }
    return true;
  }

  private boolean dfs(int node, HashSet<Integer>[] adjList, int[] state,
      LinkedList<Integer> constructedSeq) {

    state[node] = 1;

    for (int next : adjList[node]) {
      if (state[next] == 1) {
        // cycle.
        return false;
      }
      if (state[next] == 0 && !dfs(next, adjList, state, constructedSeq)) {
        return false;
      }
    }
    state[node] = 2;

    if (constructedSeq.size() != 0 && !adjList[node].contains(constructedSeq.getFirst())) {
      return false;
    }

    constructedSeq.addFirst(node);
    return true;
  }

  private int getRoot(int[] inDegree) {
    int root = -1;
    for (int i = 1; i < inDegree.length; i++) {
      if (inDegree[i] == 0) {
        if (root != -1) {
          return -1;
        }
        root = i;
      }
    }
    return root;
  }

  private boolean validNumber(int num, int n) {
    return num > 0 && num <= n;
  }

}
