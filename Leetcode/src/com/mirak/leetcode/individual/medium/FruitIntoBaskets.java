package com.mirak.leetcode.individual.medium;

import java.util.*;

public class FruitIntoBaskets {

  public int totalFruit(int[] tree) {
    int left = 0, right = 0;
    Map<Integer, Integer> countMap = new HashMap<>();
    int res = 0;
    while (right < tree.length) {
      countMap.put(tree[right], countMap.getOrDefault(tree[right], 0) + 1);

      while (left < right && countMap.size() > 2) {
        decrementOccrance(tree[left++], countMap);
      }
      res = Math.max(res, right - left + 1);
      right++;
    }
    return res;
  }

  private void decrementOccrance(int fruitType, Map<Integer, Integer> countMap) {
    if (countMap.get(fruitType) == 1) {
      countMap.remove(fruitType);
    } else {
      countMap.put(fruitType, countMap.get(fruitType) - 1);
    }
  }


}
