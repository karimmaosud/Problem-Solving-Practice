package com.mirak.leetcode.individual.easy;

public class RotateArray {

  public void rotate(int[] nums, int k) {
    int maxIndex = rotateNext(nums, 0, k);
    for (int i = 1; i < Math.min(nums.length, maxIndex); i++) {
      rotateNext(nums, i, k);
    }
  }

  private int rotateNext(int[] nums, int start, int k) {
    int ret = Integer.MAX_VALUE;
    int n = nums.length;
    int next = (start + k) % n;
    while (next != start) {
      ret = Math.min(ret, next);
      swap(start, next, nums);
      next = (next + k) % n;
    }
    return ret;
  }

  private void swap(int i, int j, int[] nums) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}
