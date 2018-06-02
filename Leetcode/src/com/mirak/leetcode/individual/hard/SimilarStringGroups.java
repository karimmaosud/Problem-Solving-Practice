package com.mirak.leetcode.individual.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SimilarStringGroups {
  private int[] p = new int[2010];

  public int numSimilarGroups(String[] A) {

    initP();

    for (int i = 0; i < A.length; i++) {
      for (int j = i + 1; j < A.length; j++) {
        if (isValidPair(A[i], A[j])) {
          union(i, j, p);
        }
      }
    }

    Set<Integer> parents = new HashSet<>();
    for (int i = 0; i < A.length; i++) {
      parents.add(findParent(i, p));
    }
    return parents.size();
  }

  private boolean isValidPair(String s1, String s2) {

    ArrayList<Integer> idxs = new ArrayList<>();
    for (int i = 0; i < s1.length(); i++) {
      if (s1.charAt(i) != s2.charAt(i)) {
        idxs.add(i);
      }

      if (idxs.size() > 2) {
        return false;
      }
    }

    if (idxs.size() != 2) {
      return false;
    }
    int i1 = idxs.get(0);
    int i2 = idxs.get(1);
    return s1.charAt(i1) == s2.charAt(i2) && s1.charAt(i2) == s2.charAt(i1);
  }

  private void initP() {
    for (int i = 0; i < p.length; i++) {
      p[i] = i;
    }
  }

  private int findParent(int i, int[] p) {
    if (p[i] == i) {
      return i;
    }
    p[i] = findParent(p[i], p);
    return p[i];
  }

  private void union(int i, int j, int[] p) {
    p[findParent(i, p)] = findParent(j, p);
  }
}
