package com.mirak.leetcode.individual.hard;

import java.util.*;

public class MaxChunksToMakeSortedII {

  public int maxChunksToSorted(int[] arr) {
    int n = arr.length;
    int[] nums = new int[n];
    for (int i = 0; i < n; ++i) {
      nums[i] = arr[i];
    }
    Arrays.sort(nums);
    Map<Integer, Integer> matched = new HashMap<>();
    int chunkEndIndex = 0;
    int chunksCount = 0;
    for (int i = 0; i < n; ++i) {
      if (i > chunkEndIndex) {
        chunksCount++;
        chunkEndIndex = i;
      }
      int idx = binSearch(nums, arr[i]) + matched.getOrDefault(arr[i], 0);
      chunkEndIndex = Math.max(chunkEndIndex, idx);
      matched.put(arr[i], matched.getOrDefault(arr[i], 0) + 1);
    }
    return chunksCount + 1;
  }

  private int binSearch(int[] nums, int key) {
    int low = 0;
    int high = nums.length - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] >= key) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }

  private int stackSolve(int[] nums) {
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < nums.length; ++i) {
      if (stack.isEmpty()) {
        stack.push(nums[i]);
        continue;
      }
      int max = Math.max(nums[i], stack.peek());
      while (!stack.isEmpty() && nums[i] < stack.peek()) {
        stack.pop();
      }
      stack.push(max);
    }
    return stack.size();
  }

}
