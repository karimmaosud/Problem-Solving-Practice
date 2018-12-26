package com.mirak.leetcode.individual.medium;

public class WiggleSort {

  public void wiggleSort(int[] nums) {
    if (nums.length <= 1) {
      return;
    }

    boolean greater = true;
    for (int i = 1; i < nums.length; i++) {
      if ((greater && nums[i] < nums[i - 1]) || (!greater && nums[i] > nums[i - 1])) {
        swap(nums, i, i - 1);
      }

      greater = !greater;
    }
  }

  private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}
