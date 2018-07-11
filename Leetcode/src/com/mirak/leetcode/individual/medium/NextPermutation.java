package com.mirak.leetcode.individual.medium;

public class NextPermutation {

  public void nextPermutation(int[] nums) {
    if (nums.length == 0) {
      return;
    }
    int i = nums.length - 1;
    while (i - 1 >= 0 && nums[i - 1] >= nums[i]) {
      i--;
    }
    if (i == 0) {
      reverse(nums, 0);
      return;
    }
    // nums[i - 1] < nums[i]
    int k = i;
    while (k < nums.length && nums[k] > nums[i - 1]) {
      k++;
    }
    k--;
    swap(nums, i - 1, k);
    reverse(nums, i);
  }

  private void reverse(int[] nums, int i) {
    int j = nums.length - 1;
    while (i < j) {
      swap(nums, i, j);
      i++;
      j--;
    }
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}
