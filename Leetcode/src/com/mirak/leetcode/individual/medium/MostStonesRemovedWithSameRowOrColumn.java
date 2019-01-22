package com.mirak.leetcode.individual.medium;

import java.util.*;

public class MostStonesRemovedWithSameRowOrColumn {

  public int removeStones(int[][] stones) {

    int[] p = new int[20000];
    int[] rank = new int[20000];
    for (int i = 0; i < p.length; i++) {
      p[i] = i;
    }

    for (int[] stone : stones) {
      unionPoints(stone[0], 10000 + stone[1], p, rank);
    }
    Set<Integer> components = new HashSet<>();
    for (int i = 0; i < stones.length; i++) {
      components.add(findParent(stones[i][0], p));
    }
    return stones.length - components.size();
  }


  private void unionPoints(int i, int j, int[] p, int[] rank) {
    int pi = findParent(i, p);
    int pj = findParent(j, p);
    if (rank[pi] > rank[pj]) {
      p[pj] = pi;
    } else {
      p[pi] = pj;
      if (rank[pi] == rank[pj]) {
        rank[pj]++;
      }
    }
  }

  private int findParent(int i, int[] p) {
    if (p[i] != i) {
      p[i] = findParent(p[i], p);
    }
    return p[i];
  }
}
