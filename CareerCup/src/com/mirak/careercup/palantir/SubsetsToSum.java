package com.mirak.careercup.palantir;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SubsetsToSum {
  public static List<LinkedList<Integer>> getSubsetsToSum(int[] nums, int sum) {
    Arrays.sort(nums);
    List<LinkedList<Integer>> res = new LinkedList<>();
    LinkedList<Integer> current = new LinkedList<>();
    solve(nums, 0, current, res, sum);
    return res;
  }

  private static void solve(int[] nums, int index, LinkedList<Integer> current, List<LinkedList<Integer>> res, int sum) {
    if(sum == 0) {
      res.add((LinkedList<Integer>) current.clone());
      return;
    }
    if(sum < 0 || index == nums.length) {
      return;
    }
    // take
    current.add(nums[index]);
    solve(nums, index + 1, current, res, sum - nums[index]);
    current.removeLast();
    int j = index;
    while(j < nums.length && nums[j] == nums[index]) {
      j++;
    }
    solve(nums, j, current, res, sum);
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 2, 3, 4, 5};
    System.out.println(getSubsetsToSum(nums, 5));
  }
}
