package com.mirak.leetcode.individual.medium;

import java.util.*;

public class ShuffleAnArray {

  int[] original;
  int[] nums;
  Random random;

  public ShuffleAnArray(int[] nums) {
    random = new Random();
    this.nums = nums;
    original = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      original[i] = nums[i];
    }
  }

  /**
   * Resets the array to its original configuration and return it.
   */
  public int[] reset() {
    for (int i = 0; i < original.length; i++) {
      nums[i] = original[i];
    }
    return nums;
  }

  /**
   * Returns a random shuffling of the array.
   */
  public int[] shuffle() {
    int n = nums.length;
    for (int i = 0; i < nums.length; i++) {
      int r = random.nextInt(n--);
      int temp = nums[i];
      nums[i] = nums[i + r];
      nums[i + r] = temp;
    }
    return nums;
  }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */


