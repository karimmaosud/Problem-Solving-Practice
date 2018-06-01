package com.mirak.leetcode.contests.individual.easy;

public class RemoveDuplicatesFromSortedArray {
  public int removeDuplicates(int[] nums) {
    int n = nums.length;
    if(n <= 1) {
      return 1;
    }
    int last_index = 0;
    for(int i = 1; i < n; i++) {
      if(nums[i] == nums[last_index]) {
        continue;
      }
      nums[++last_index] = nums[i];
    }
    return last_index + 1;
  }
}
