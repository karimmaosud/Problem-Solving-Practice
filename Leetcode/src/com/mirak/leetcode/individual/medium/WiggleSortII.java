package com.mirak.leetcode.individual.medium;

import java.util.Arrays;

public class WiggleSortII {

  public void wiggleSort(int[] nums) {
    int n = nums.length;
    int[] numsCopy = new int[n];
    Arrays.sort(nums);
    for (int i = 0; i < n; i++) {
      numsCopy[i] = nums[i];
    }
    int j = n - 1;
    int start = 1;
    while (start < n) {
      nums[start] = numsCopy[j--];
      start += 2;
    }
    start = 0;
    while (start < n) {
      nums[start] = numsCopy[j--];
      start += 2;
    }
  }
}
