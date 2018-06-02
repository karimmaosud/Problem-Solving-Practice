package com.mirak.leetcode.individual.hard;

public class CountOfRangeSum_MergeSort {
  public int countRangeSum(int[] nums, int lower, int upper) {
    if (nums.length == 0) {
      return 0;
    }
    long[] sum = new long[nums.length];
    initSum(nums, sum);
    return mergeSort(sum, lower, upper);
  }

  private void initSum(int[] nums, long[] sum) {
    sum[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      sum[i] = sum[i - 1] + nums[i];
    }
  }

  private int mergeSort(long[] sum, long lower, long upper) {
    return mergeSort(sum, 0, sum.length - 1, lower, upper);
  }

  private int mergeSort(long[] sum, int left, int right, long lower, long upper) {
    if (left > right) {
      return 0;
    }
    if (left == right) {
      return sum[left] >= lower && sum[left] <= upper ? 1 : 0;
    }
    int mid = (left + right) / 2;
    return mergeSort(sum, left, mid, lower, upper)
        + mergeSort(sum, mid + 1, right, lower, upper)
        + merge(sum, left, right, lower, upper);
  }

  private int merge(long[] sum, int left, int right, long lower, long upper) {
    int mid = (left + right) / 2;
    long[] leftArray = new long[mid - left + 1];
    long[] rightArray = new long[right - mid];

    for (int i = left; i <= mid; i++) {
      leftArray[i - left] = sum[i];
    }

    for (int i = mid + 1; i <= right; i++) {
      rightArray[i - mid - 1] = sum[i];
    }

    int p1 = 0, p2 = 0, p = left;
    int l = 0, r = 0;
    int res = 0;
    while (p1 < leftArray.length && p2 < rightArray.length) {
      if (leftArray[p1] < rightArray[p2]) {
        while (l < rightArray.length && rightArray[l] - leftArray[p1] < lower) l++;
        while (r < rightArray.length && rightArray[r] - leftArray[p1] <= upper) r++;
        sum[p++] = leftArray[p1++];
        res += r - l;
      } else {
        sum[p++] = rightArray[p2++];
      }
    }
    while (p1 < leftArray.length) {
      while (l < rightArray.length && rightArray[l] - leftArray[p1] < lower) l++;
      while (r < rightArray.length && rightArray[r] - leftArray[p1] <= upper) r++;
      sum[p++] = leftArray[p1++];
      res += r - l;
    }
    return res;
  }
}
