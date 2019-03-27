package com.mirak.leetcode.individual.medium;

public class H_Index {

  public int hIndex(int[] citations) {
    int n = citations.length;
    int[] count = new int[n + 1];
    for (int num : citations) {
      count[Math.min(n, num)]++;
    }
    int idx = 1;
    for (int i = n; i >= 0; i--) {
      if (count[i] == 0) {
        continue;
      }
      if (i < idx) {
        return idx - 1;
      }
      idx++;
      count[i++]--;
    }
    return idx - 1;
  }

}
