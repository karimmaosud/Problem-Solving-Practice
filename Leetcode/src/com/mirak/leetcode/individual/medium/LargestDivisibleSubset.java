package com.mirak.leetcode.individual.medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LargestDivisibleSubset {
  public List<Integer> largestDivisibleSubset(int[] nums) {
    if(nums.length == 0) {
      return new LinkedList<>();
    }
    Arrays.sort(nums);
    int[] subsetSize = new  int[nums.length];
    int[] parent = new int[nums.length];
    for(int i = 0; i < subsetSize.length; i++) {
      subsetSize[i] = 1;
      parent[i] = i;
    }
    int maxSubsetLength = 1;
    for(int i = nums.length - 1; i >= 0; i--) {
      for(int j = i - 1; j >= 0; j--) {
        if(nums[i] % nums[j] == 0 && subsetSize[j] < subsetSize[i] + 1) {
          subsetSize[j] = subsetSize[i] + 1;
          parent[j] = i;
          maxSubsetLength = Math.max(maxSubsetLength, subsetSize[j]);
        }
      }
    }
    int lower = 0;
    for(; lower < nums.length; lower++) {
      if(subsetSize[lower] == maxSubsetLength) {
        break;
      }
    }
    List<Integer> list = new LinkedList<>();
    list.add(nums[lower]);
    while(parent[lower] != lower) {
      lower = parent[lower];
      list.add(nums[lower]);
    }
    return list;
  }
}

