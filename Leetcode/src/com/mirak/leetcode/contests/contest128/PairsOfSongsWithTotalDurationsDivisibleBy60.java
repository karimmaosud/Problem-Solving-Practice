package com.mirak.leetcode.contests.contest128;

import java.util.*;

public class PairsOfSongsWithTotalDurationsDivisibleBy60 {

  public int numPairsDivisibleBy60(int[] time) {
    for (int i = 0; i < time.length; i++) {
      time[i] %= 60;
    }
    Arrays.sort(time);
    int[] count = new int[60];
    count[time[0]]++;
    int res = 0;
    for (int i = 1; i < time.length; i++) {
      res += count[(60 - time[i]) % 60];
      count[time[i]]++;
    }
    return res;
  }
}
