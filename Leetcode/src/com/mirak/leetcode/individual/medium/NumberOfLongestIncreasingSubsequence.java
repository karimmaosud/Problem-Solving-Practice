package com.mirak.leetcode.individual.medium;

import java.util.*;

public class NumberOfLongestIncreasingSubsequence {

  public int findNumberOfLIS_DP(int[] nums) {
    int n = nums.length;
    if (n == 0) {
      return 0;
    }
    int[] lci = new int[n];
    Arrays.fill(lci, Integer.MIN_VALUE);
    int[] count = new int[n];
    lci[0] = 1;
    count[0] = 1;
    int max = 1;
    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[j] < nums[i] && lci[j] + 1 > lci[i]) {
          lci[i] = lci[j] + 1;
          count[i] = count[j];
        } else if (nums[j] < nums[i] && lci[j] + 1 == lci[i]) {
          count[i] += count[j];
        }
        count[i] = Math.max(count[i], 1);
        lci[i] = Math.max(lci[i], 1);
        max = Math.max(max, lci[i]);
      }
    }
    int ans = 0;
    for (int i = 0; i < n; i++) {
      if (lci[i] == max) {
        ans += count[i];
      }
    }
    return ans;
  }

  public int findNumberOfLIS_BinarySearch(int[] nums) {
    int n = nums.length;
    if (n == 0) {
      return 0;
    }
    int[] d = new int[n + 1];

    Arrays.fill(d, Integer.MAX_VALUE);
    d[0] = Integer.MIN_VALUE;

    ArrayList<Integer>[] numsList = new ArrayList[n + 1];
    ArrayList<Integer>[] sums = new ArrayList[n + 1];

    for (int i = 0; i < n; i++) {
      int idx = binarySearch(d, nums[i]);

      if (nums[i] < d[idx]) {
        d[idx] = nums[i];
      }

      if (numsList[idx] == null) {
        numsList[idx] = new ArrayList<>();
        sums[idx] = new ArrayList<>();
      }
      numsList[idx].add(nums[i]);
      int nextSum = getNextSum(numsList[idx - 1], sums[idx - 1], nums[i]);
      sums[idx].add(nextSum + (sums[idx].isEmpty() ? 0 : sums[idx].get(sums[idx].size() - 1)));
    }
    for (int i = n; i > 0; i--) {
      if (d[i] != Integer.MAX_VALUE) {
        return sums[i].get(sums[i].size() - 1);
      }
    }
    return 0;
  }

  private int getNextSum(ArrayList<Integer> seq, ArrayList<Integer> sums, int key) {
    if (seq == null) {
      return 1;
    }
    int low = 0;
    int high = sums.size() - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (seq.get(mid) < key) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return sums.get(sums.size() - 1) - (high == -1 ? 0 : sums.get(high));
  }

  private int binarySearch(int[] d, int key) {
    int low = 0;
    int high = d.length - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (d[mid] < key) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return low;
  }

}
