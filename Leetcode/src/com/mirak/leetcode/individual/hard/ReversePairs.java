package com.mirak.leetcode.individual.hard;

public class ReversePairs {

  public int reversePairs(int[] nums) {
    return sort(nums);
  }

  private int sort(int[] nums) {
    return mergeSort(nums, 0, nums.length - 1);
  }

  private int mergeSort(int[] nums, int left, int right) {
    if(left >= right) {
      return 0;
    }
    int mid = (left + right) / 2;
    return mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right) +
        combine(nums, left, right, mid);
  }

  private int combine(int[] nums, int left, int right, int mid) {
    int[] leftArray = new int[mid - left + 1];
    int[] rightArray = new int[right - mid];
    for(int i  = left; i <= mid; i++){
      leftArray[i - left] = nums[i];
    }
    for(int i = mid + 1; i <= right; i++){
      rightArray[i - mid - 1] = nums[i];
    }
    int pLeft = 0, pRight = 0, pNum = left;
    int pointer = 0;
    int count = 0;
    while(pLeft < leftArray.length && pRight < rightArray.length) {
      while(pointer < rightArray.length && leftArray[pLeft] > 2L * rightArray[pointer]) {
        pointer++;
      }
      if(leftArray[pLeft] < rightArray[pRight]) {
        nums[pNum++] = leftArray[pLeft++];
        count += pointer;
      }else {
        nums[pNum++] = rightArray[pRight++];
      }
    }
    while(pLeft < leftArray.length) {
      while(pointer < rightArray.length && leftArray[pLeft] > 2L * rightArray[pointer]) {
        pointer++;
      }
      nums[pNum++] = leftArray[pLeft++];
      count += pointer;
    }
    return count;
  }
}

