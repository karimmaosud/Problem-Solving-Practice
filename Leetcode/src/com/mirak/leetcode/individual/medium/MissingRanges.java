package com.mirak.leetcode.individual.medium;

import java.util.*;

public class MissingRanges {

  public List<String> findMissingRanges(int[] nums, int lower, int upper) {
    List<String> missingRanges = new LinkedList<>();
    if (nums.length == 0) {
      appendRange(lower - 1, upper + 1, missingRanges);
      return missingRanges;
    }
    if (lower < nums[0]) {
      appendRange(lower - 1, nums[0], missingRanges);
    }
    for (int i = 1; i < nums.length; i++) {
      if ((long) nums[i] - (long) nums[i - 1] > 1L) {
        appendRange(nums[i - 1], nums[i], missingRanges);
      }
    }
    if (upper > nums[nums.length - 1]) {
      appendRange(nums[nums.length - 1], upper + 1, missingRanges);
    }
    return missingRanges;
  }


  private void appendRange(int left, int right, List<String> missingRanges) {
    int nextLeft = left + 1;
    int nextRight = right - 1;
    if (nextLeft == nextRight) {
      missingRanges.add("" + nextLeft);
    } else {
      missingRanges.add(nextLeft + "->" + nextRight);
    }

  }

}
