package com.mirak.leetcode.contests.contest119;

import java.util.*;

public class OddEvenJump {

  public int oddEvenJumps(int[] A) {
    TreeSet<Integer> set = new TreeSet<>();
    int n = A.length;
    boolean[] reachOdd = new boolean[n];
    boolean[] reachEven = new boolean[n];

    reachOdd[n - 1] = reachEven[n - 1] = true;

    int[] elementIndex = new int[100001];

    elementIndex[A[n - 1]] = n - 1;

    set.add(A[n - 1]);
    int res = 0;
    for (int i = n - 2; i >= 0; i--) {
      Integer ceil = set.ceiling(A[i]);
      if (ceil != null) {
        reachOdd[i] = reachEven[elementIndex[ceil]];
      }
      Integer floor = set.floor(A[i]);
      if (floor != null) {
        reachEven[i] = reachOdd[elementIndex[floor]];
      }
      set.add(A[i]);
      elementIndex[A[i]] = i;
      res += reachOdd[i] ? 1 : 0;
    }
    return res + 1;
  }
}
