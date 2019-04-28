package com.mirak.leetcode.contests.contest134;

import java.util.*;

public class MovingStonesUntilConsecutive {

  public int[] numMovesStones(int a, int b, int c) {
    int[] nums = new int[]{a, b, c};
    Arrays.sort(nums);
    int min = 0;
    int[] diffs = new int[]{nums[1] - nums[0], nums[2] - nums[1]};
    if (diffs[0] <= 2 || diffs[1] <= 2) {
      if (diffs[0] != 1 || diffs[1] != 1) {
        min = 1;
      }

    } else {
      min = 2;
    }
    int max = nums[1] - nums[0] - 1 + nums[2] - nums[1] - 1;
    return new int[]{min, max};
  }

}
