package com.mirak.leetcode.individual.medium;

import java.util.*;

public class Permutations {

  public List<List<Integer>> permute(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> permutations = new LinkedList<>();
    permuteArray(nums, 0, new LinkedList<>(), permutations);
    return permutations;
  }

  private void permuteArray(int[] nums, int mask, LinkedList<Integer> current,
      List<List<Integer>> permutations) {
    if (mask == (1 << nums.length) - 1) {
      LinkedList<Integer> currentCopy = new LinkedList<>();
      for (int i = 0; i < nums.length; i++) {
        int num = current.removeFirst();
        currentCopy.addLast(num);
        current.addLast(num);
      }
      permutations.add(currentCopy);
      return;
    }

    // a mask that contains only the un-used indicies with bits set to 1 instead of 0.
    int negatedMask = ~mask & ((1 << nums.length) - 1);
    while (negatedMask != 0) {
      int powerOfTwo = negatedMask & -negatedMask;
      int index = (int) Math.ceil(Math.log10(powerOfTwo) / Math.log10(2.0));
      current.addLast(nums[index]);
      permuteArray(nums, mask | (1 << index), current, permutations);
      current.removeLast();
      negatedMask -= powerOfTwo;
    }
  }
}

