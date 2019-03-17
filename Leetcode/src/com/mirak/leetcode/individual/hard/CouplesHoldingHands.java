package com.mirak.leetcode.individual.hard;

import java.util.*;

public class CouplesHoldingHands {

  public int minSwapsCouples(int[] row) {
    return minSwapsCouplesGreedy(row);
  }

  private int minSwapsCouplesGreedy(int[] row) {
    int ans = 0;
    int n = row.length;
    int[] numIndex = new int[100];

    for (int i = 0; i < n; i++) {
      numIndex[row[i]] = i;
    }

    for (int i = 0; i < n; i += 2) {
      if (isCouple(row[i], row[i + 1])) {
        continue;
      }
      ans++;
      // either move row[i], or row[i + 1].
      int partner = getPartner(row[i]);
      int partnerIndex = numIndex[partner];
      swap(row, partnerIndex, i + 1);
      swap(numIndex, partner, row[partnerIndex]);
      if (coupleAtIndex(row, partnerIndex)) {
        continue;
      }
      swap(row, partnerIndex, i + 1);
      swap(numIndex, row[i + 1], row[partnerIndex]);

      partner = getPartner(row[i + 1]);
      partnerIndex = numIndex[partner];
      swap(row, partnerIndex, i);
      swap(numIndex, partner, row[partnerIndex]);
    }
    return ans;
  }

  private int minSwapsCouplesCycles(int[] row) {
    // n nodes.
    int n = row.length / 2;
    ArrayList<Integer>[] adjList = new ArrayList[n];
    for (int i = 0; i < n; i++) {
      adjList[i] = new ArrayList<>();
    }
    int[] couch = new int[2 * n];
    for (int i = 0; i < row.length; i++) {
      couch[row[i]] = i / 2;
    }
    for (int i = 0; i < 2 * n; i += 2) {
      if (isCouple(row[i], row[i + 1])) {
        continue;
      }
      int u = i / 2;
      int v1 = couch[getPartner(row[i])];
      int v2 = couch[getPartner(row[i + 1])];
      adjList[u].add(v1);
      adjList[u].add(v2);
    }
    boolean[] vis = new boolean[n];
    int res = 0;
    for (int i = 0; i < n; i++) {
      if (!vis[i]) {
        res += dfs(i, adjList, vis);
      }
    }
    return res;
  }

  private int dfs(int u, ArrayList<Integer>[] adjList, boolean[] vis) {
    vis[u] = true;

    for (int v : adjList[u]) {
      if (!vis[v]) {
        return 1 + dfs(v, adjList, vis);
      }
    }
    return 0;
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  private boolean isCouple(int i, int j) {
    return Math.abs(i - j) == 1 && (Math.min(i, j) & 1) == 0;
  }

  private int getPartner(int i) {
    return (i & 1) == 0 ? i + 1 : i - 1;
  }

  private boolean coupleAtIndex(int[] row, int index) {
    if ((index & 1) == 1) {
      index--;
    }
    return isCouple(row[index], row[index + 1]);
  }

}
