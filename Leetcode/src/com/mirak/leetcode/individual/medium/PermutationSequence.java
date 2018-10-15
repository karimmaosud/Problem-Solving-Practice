package com.mirak.leetcode.individual.medium;

import java.util.LinkedList;

public class PermutationSequence {

  public String getPermutation(int n, int k) {
    int[] factorial = new int[10];
    LinkedList<Integer> nums = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      nums.add(i + 1);
    }
    initFactorial(factorial);
    StringBuilder builder = new StringBuilder();
    findKthPermutation(nums, k, factorial, builder);
    return builder.toString();
  }

  private void initFactorial(int[] factorial) {
    factorial[0] = 1;
    for (int i = 1; i < 10; i++) {
      factorial[i] = factorial[i - 1] * i;
    }
  }

  private void findKthPermutation(LinkedList<Integer> nums, int k,
      int[] factorial, StringBuilder builder) {
    if (nums.isEmpty()) {
      return;
    }
    int n = nums.size() - 1;
    // how many you'd take of n!.
    int i = k / factorial[n];
    if (k - (i * factorial[n]) == 0) {
      i--;
    }
    findKthPermutation(nums, k - i * factorial[n], factorial,
        builder.append(nums.remove(i)));
  }
}