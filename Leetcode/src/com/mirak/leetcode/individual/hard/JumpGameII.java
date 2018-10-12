package com.mirak.leetcode.individual.hard;

public class JumpGameII {

  public int jump(int[] nums) {
    if (nums.length <= 1) {
      return 0;
    }

    int maxReach = 0;
    int steps = 1;
    int incrementStepsAfter = nums[0];
    for (int i = 0; i < nums.length; i++) {
      if (i > incrementStepsAfter) {
        steps++;
        incrementStepsAfter = maxReach;
      }
      int right = Math.min(nums.length - 1, i + nums[i]);
      if (right > maxReach) {
        maxReach = right;
      }
    }
    return steps;
  }
}
