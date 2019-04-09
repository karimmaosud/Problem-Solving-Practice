package com.mirak.leetcode.individual.medium;

public class RemoveDuplicatesFromSortedArrayII {
  public int removeDuplicates(int[] nums) {
    int n = nums.length;
    if (n == 0) {
      return 0;
    }
    int idx = 0;
    for (int i = 1; i < n; ++i) {
      if (equalityCount(nums[i], nums, idx) > 1) {
        continue;
      }
      swap(i, ++idx, nums);
    }
    return idx + 1;
  }

  private void swap(int i, int j, int[] nums) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  private int equalityCount(int num, int[] nums, int idx) {
    if (idx < 1 || num != nums[idx]) {
      return 0;
    }
    if (num == nums[idx - 1]) {
      return 2;
    }
    return 1;
  }
}
