package com.mirak.leetcode.contests.contest114;

import java.util.*;

public class ArrayOfDoubledPairs {
  public boolean canReorderDoubled(int[] A) {
    PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return Math.abs(o2) - Math.abs(o1);
      }
    });

    Map<Integer, Integer> countMap = new HashMap<>();

    for (int num : A) {
      pq.add(num);
      countMap.put(num, countMap.getOrDefault(num, 0) + 1);
    }

    while (!pq.isEmpty()) {
      int top = pq.poll();
      if (countMap.get(top) == 0) {
        continue;
      }
      System.out.println(top);
      if ((top & 1) == 1 || countMap.getOrDefault(top / 2, 0) == 0) {
        return false;
      }
      countMap.put(top, countMap.get(top) - 1);
      countMap.put(top / 2, countMap.get(top / 2) - 1);
    }
    return true;
  }
}
