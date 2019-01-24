package com.mirak.leetcode.individual.medium;

import java.util.*;

public class _4Sum {

  public List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);

    int n = nums.length;

    Set<Integer> set = new HashSet<>();
    Set<String> vis = new HashSet<>();
    for (int num : nums) {
      set.add(num);
    }

    List<List<Integer>> res = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        for (int k = j + 1; k < n; k++) {
          int d = target - (nums[i] + nums[j] + nums[k]);
          if (d > nums[k] && set.contains(d) || (d == nums[k] && k + 1 < n && nums[k + 1] == d)) {
            String hash = nums[i] + "#" + nums[j] + "#" + nums[k] + "#" + d;
            if (!vis.contains(hash)) {
              vis.add(hash);
              res.add(Arrays.asList(nums[i], nums[j], nums[k], d));
            }
          }
        }
      }
    }
    return res;
  }
}
