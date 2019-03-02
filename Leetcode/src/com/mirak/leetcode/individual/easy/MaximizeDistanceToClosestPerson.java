package com.mirak.leetcode.individual.easy;

public class MaximizeDistanceToClosestPerson {


  public int maxDistToClosest(int[] seats) {
    int n = seats.length;
    int runner = 0;
    int ans = Integer.MIN_VALUE;
    while (runner < n) {
      if (seats[runner] == 1) {
        runner++;
        continue;
      }
      int j = 0;
      while (runner + j < n && seats[runner + j] == 0) {
        j++;
      }

      if (runner == 0 || runner + j == n) {
        ans = Math.max(ans, j);
      } else {
        ans = Math.max(ans, (j + 1) / 2);
      }

      runner = runner + j;
    }
    return ans;
  }
}
