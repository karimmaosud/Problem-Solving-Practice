package com.mirak.leetcode.individual.medium;

import java.util.*;

public class KthLargestElementInAnArray {

  public int findKthLargest(int[] nums, int k) {
    return findKthLargest(0, nums.length - 1, nums, nums.length - k + 1, new Random());
  }

  private int findKthLargest(int low, int high, int[] nums, int k, Random rand) {
    int length = high - low + 1;
    int randomIndex = low + rand.nextInt(length);
    swap(randomIndex, high, nums);
    int p = nums[high];
    int i = low - 1;
    int j = low;
    while (j < high) {
      if (nums[j] < p) {
        i++;
        swap(i, j, nums);
      }
      j++;
    }
    i++;
    swap(i, high, nums);
    if (k - 1 == i - low) {
      return nums[i];
    }
    if (k <= i - low) {
      return findKthLargest(low, i - 1, nums, k, rand);
    } else {
      return findKthLargest(i + 1, high, nums, k - (i - low) - 1, rand);
    }
  }

  private void swap(int i, int j, int[] nums) {
    if (i == j) {
      return;
    }
    nums[i] ^= nums[j];
    nums[j] ^= nums[i];
    nums[i] ^= nums[j];
  }
}
