package com.mirak.leetcode.individual.hard;

public class FirstMissingPositive {

  public int firstMissingPositive(int[] nums) {
    for(int i = 0; i < nums.length; i++) {
      while(!isValidNumber(nums[i], i, nums.length)) {
        int temp = nums[i];
        int j = nums[i] - 1;
        if(nums[j] == j + 1) {
          break;
        }
        nums[i] = nums[j];
        nums[j] = temp;
      }
    }
    for(int i = 0; i < nums.length; i++) {
      if(nums[i] != i + 1) {
        return i + 1;
      }
    }
    return nums.length + 1;
  }

  private boolean isValidNumber(int num, int position, int arrayLength) {
    return num <= 0 || num == position + 1 || num > arrayLength;
  }

}
