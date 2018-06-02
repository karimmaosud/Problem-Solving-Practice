package com.mirak.leetcode.individual.medium;

import java.util.TreeSet;

public class ContainsDuplicateIII_BST {
  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
    TreeSet<Integer> bst = new TreeSet<>();
    for(int i = 0; i < nums.length; i++) {
      if(bst.size() == k + 1) {
        bst.remove(nums[i - k - 1]);
      }
      Integer successor = bst.ceiling(nums[i]);
      long maxRight = (long) nums[i] + t;
      long maxLeft = (long) nums[i] - t;
      if(successor != null && successor <= maxRight) {
        return true;
      }

      Integer predecessor = bst.floor(nums[i]);
      if(predecessor != null && predecessor >= maxLeft) {
        return true;
      }
      bst.add(nums[i]);
    }
    return false;
  }
}
