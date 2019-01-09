package com.mirak.leetcode.individual.medium;

import java.util.*;

public class RandomPickIndex {

  private Map<Integer, ArrayList<Integer>> indexes;
  private Random random;

  public RandomPickIndex(int[] nums) {
    random = new Random();
    indexes = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      indexes.putIfAbsent(nums[i], new ArrayList<>());
      indexes.get(nums[i]).add(i);
    }
  }

  public int pick(int target) {
    return indexes.get(target).get(random.nextInt(indexes.get(target).size()));
  }
}
