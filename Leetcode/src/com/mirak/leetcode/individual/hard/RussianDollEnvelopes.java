package com.mirak.leetcode.individual.hard;

import java.util.*;

public class RussianDollEnvelopes {

  private static final int INF = 1000000000;

  public int maxEnvelopes(int[][] envelopes) {
    if (envelopes.length == 0) {
      return 0;
    }
    Arrays.sort(envelopes, new Comparator<int[]>() {
      @Override
      public int compare(int[] a, int[] b) {
        return a[0] - b[0] != 0 ? a[0] - b[0] : a[1] - b[1];
      }
    });

    ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    int prevX = envelopes[0][0] - 1;
    for (int[] a : envelopes) {
      if (a[0] != prevX) {
        list.add(new ArrayList<>());
      }
      prevX = a[0];
      list.get(list.size() - 1).add(a[1]);
    }
    int[] dist = new int[envelopes.length + 1];
    Arrays.fill(dist, INF);
    dist[0] = -INF;
    for (ArrayList<Integer> y : list) {
      for (int i = y.size() - 1; i >= 0; i--) {
        int j = upperBound(dist, y.get(i));
        if (dist[j - 1] < y.get(i) && y.get(i) < dist[j]) {
          dist[j] = y.get(i);
        }
      }
    }

    for (int i = 1; i <= envelopes.length; i++) {
      if (dist[i] == INF) {
        return i - 1;
      }
    }
    return envelopes.length;
  }

  private int upperBound(int[] dist, int key) {
    int low = 0;
    int high = dist.length - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (dist[mid] >= key) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }

}
