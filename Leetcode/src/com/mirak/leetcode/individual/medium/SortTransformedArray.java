package com.mirak.leetcode.individual.medium;

public class SortTransformedArray {

  public static int[] sortTransformedArray(int[] nums, int a, int b, int c) {
    int[] res = new int[nums.length];
    if (res.length == 0) {
      return res;
    }
    int l = 0, r = nums.length - 1;
    while (l <= r) {
      int appliedL = transform(a, b, c, nums[l]);
      int appliedR = transform(a, b, c, nums[r]);
      if (appliedL > appliedR) {
        if (a > 0) {
          res[r - l] = appliedL;
          l++;
        } else {
          res[nums.length + l - r - 1] = appliedR;
          r--;
        }
      } else {
        if (a > 0) {
          res[r - l] = appliedR;
          r--;
        } else {
          res[nums.length + l - r - 1] = appliedL;
          l++;
        }
      }
    }
    return res;
  }

  private static int transform(int a, int b, int c, int x) {
    return a * x * x + b * x + c;
  }

  public static void main(String[] args) {
    System.out.println(sortTransformedArray(new int[]{-4, -2, 2, 4}, -1, 3, 5));
  }
}
