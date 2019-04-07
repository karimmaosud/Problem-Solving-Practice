package com.mirak.leetcode.individual.hard;

import java.util.*;

public class ArithmeticSlicesIISubsequence {

  private class Pair {

    long step;
    int count;

    Pair(long step, int count) {
      this.step = step;
      this.count = count;
    }
  }

  public int numberOfArithmeticSlices(int[] A) {
    if (A.length == 0) {
      return 0;
    }

    Map<Long, Pair>[] map = new HashMap[A.length];
    for (int i = 0; i < A.length; ++i) {
      map[i] = new HashMap<>();
    }

    Map<Integer, Integer> countMap = new HashMap<>();
    countMap.put(A[0], 1);
    int ans = 0;

    for (int i = 1; i < A.length; ++i) {

      countMap.put(A[i], countMap.getOrDefault(A[i], 0) + 1);

      for (int j = 0; j < i; ++j) {
        if (A[j] == A[i]) {
          continue;
        }

        long step = (long) A[i] - A[j];

        map[i].putIfAbsent(step, new Pair(step, 0));
        if (!map[j].containsKey(step)) {
          map[i].get(step).count++;
          continue;
        }

        ans += map[j].get(step).count;
        map[i].get(step).count += map[j].get(step).count + 1;
      }
    }

    for (int key : countMap.keySet()) {
      int value = countMap.get(key);
      if (value < 3) {
        continue;
      }
      ans += Math.pow(2, value) - (1 + value + ((value * (value - 1) / 2)));
    }
    return ans;
  }

}
