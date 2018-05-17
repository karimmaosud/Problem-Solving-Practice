package com.mirak.leetcode.contests.individual.hard;

import java.util.ArrayList;
import java.util.Collections;

public class CountOfRangeSum {
  public int countRangeSum(int[] nums, int lower, int upper) {
    return countRangeSum(nums, 0, nums.length - 1, lower, upper);
  }

  private int countRangeSum(int[] nums, int left, int right, int lower, int upper) {
    if(left > right) {
      return 0;
    }
    if(left == right) {
      return nums[left] >= lower && nums[left] <= upper ? 1 : 0;
    }
    int mid = (left + right) / 2;
    return countRangeSum(nums, left, mid , lower, upper) + countRangeSum(nums, mid + 1, right, lower, upper) +
        countInCombineStep(nums, left, right, lower, upper);
  }

  private int countInCombineStep(int[] nums, int left, int right, int lower, int upper) {
    ArrayList<Long> leftArray = new ArrayList<>();
    ArrayList<Long> rightArray = new ArrayList<>();
    int mid = (left + right) / 2;
    long sum = nums[mid];
    for(int i = mid - 1; i >= left; i--) {
      leftArray.add(sum);
      sum += nums[i];
    }
    leftArray.add(sum);
    sum = nums[mid + 1];
    for(int i = mid + 2; i <= right; i++) {
      rightArray.add(sum);
      sum += nums[i];
    }
    rightArray.add(sum);
    Collections.sort(leftArray);
    Collections.sort(rightArray);
    int res = 0;
    for(int i = 0; i < leftArray.size(); i++) {
      int l = getLowerBound(leftArray.get(i), rightArray, lower);
      int r = getUpperBound(leftArray.get(i), rightArray, upper);
      if(l == rightArray.size() || r < 0) {
        continue;
      }
      res += r - l + 1;
    }
    return res;
  }

  private int getLowerBound(long key, ArrayList<Long> nums, int lower) {
    int low = 0;
    int high = nums.size() - 1;
    while(low <= high) {
      int mid = (low + high) / 2;
      if(key + nums.get(mid) >= lower) {
        high = mid - 1;
      }else {
        low = mid + 1;
      }
    }
    return low;
  }

  private int getUpperBound(long key, ArrayList<Long> nums, int upper) {
    int low = 0;
    int high = nums.size() - 1;
    while(low <= high) {
      int mid = (low + high) / 2;
      if(key + nums.get(mid) <= upper) {
        low = mid + 1;
      }else{
        high = mid - 1;
      }
    }
    return high;
  }
}