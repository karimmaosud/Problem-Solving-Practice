package com.mirak.leetcode.contests.individual.hard;

public class TrappingRainWater {
  class IntegerWrapper {
    int val;
    IntegerWrapper (int val) {
      this.val = val;
    }
  }
  public int trap(int[] height) {
    int left = 0;
    int right = height.length - 1;
    int res = 0;
    IntegerWrapper walkedValue = new IntegerWrapper(0);
    while(left < right) {
      walkedValue.val = 0;
      if(height[left] < height[right]) {
        res += walk(left, 1, right, height, walkedValue);
        left += walkedValue.val;
      }else {
        res += walk(right, -1, left, height, walkedValue);
        right -= walkedValue.val;
      }
    }
    return res;
  }

  private int walk (int start, int inc, int bound, int[] height, IntegerWrapper walkedValue) {
    int max = height[start];
    int res = 0;
    while(start != bound && height[start] <= max) {
      res += max - height[start];
      start += inc;
      walkedValue.val++;
    }
    return res;
  }

}

