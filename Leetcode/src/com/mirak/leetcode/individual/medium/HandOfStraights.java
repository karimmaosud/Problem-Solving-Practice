package com.mirak.leetcode.individual.medium;

import java.util.*;

public class HandOfStraights {

  public boolean isNStraightHand(int[] hand, int W) {
    if (W == 0 || hand.length % W != 0) {
      return false;
    }
    Arrays.sort(hand);
    Map<Integer, Integer> count = new HashMap<>();
    for (int num : hand) {
      count.put(num, count.getOrDefault(num, 0) + 1);
    }
    for (int i = 0; i < hand.length; i++) {
      if (count.get(hand[i]) == 0) {
        continue;
      }
      for (int j = 0; j < W; j++) {
        int next = hand[i] + j;
        if (count.getOrDefault(next, 0) == 0) {
          return false;
        }
        count.put(next, count.get(next) - 1);
      }
    }
    return true;
  }

}
