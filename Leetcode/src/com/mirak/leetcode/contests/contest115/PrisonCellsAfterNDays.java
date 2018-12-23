package com.mirak.leetcode.contests.contest115;

import java.util.*;

public class PrisonCellsAfterNDays {

  public int[] prisonAfterNDays(int[] cells, int N) {
    LinkedList<Integer> nums = new LinkedList<>();
    Set<Integer> set = new HashSet<>();

    int next = arrayToInteger(cells);

    while (!set.contains(next)) {
      nums.add(next);
      set.add(next);
      next = getNext(next);
    }

    if (N < nums.size()) {
      return integerToArray(nums.get(N));
    }

    while (nums.getFirst() != next) {
      N--;
      nums.removeFirst();
    }

    int idx = N % nums.size();
    return integerToArray(nums.get(idx));

  }

  private int[] integerToArray(int num) {
    int[] numArray = new int[8];
    for (int i = 7; i >= 0; i--) {
      numArray[i] = num & 1;
      num >>= 1;
    }
    return numArray;
  }

  private int arrayToInteger(int[] numArray) {
    int num = 0;
    for (int i = 0; i < 8; i++) {
      num += (1 << i) * numArray[8 - i - 1];
    }
    return num;
  }

  private int getNext(int num) {
    int next = 0;
    for (int i = 1; i < 7; i++) {
      int left = (num & (1 << (i - 1))) == 0 ? 0 : 1;
      int right = (num & (1 << (i + 1))) == 0 ? 0 : 1;
      next |= (1 << i) * ((left ^ right) == 0 ? 1 : 0);
    }
    return next;
  }
}
