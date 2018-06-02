package com.mirak.leetcode.individual.hard;

import java.util.ArrayList;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf {

  private class ElementWrapper {
    int value;
    int index;

    ElementWrapper(int value, int index) {
      this.value = value;
      this.index = index;
    }
  }

  public List<Integer> countSmaller(int[] nums) {

    ElementWrapper[] numsWrapper = new ElementWrapper[nums.length];
    for (int i = 0; i < nums.length; i++) {
      numsWrapper[i] = new ElementWrapper(nums[i], i);
    }
    ArrayList<Integer> countList = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      countList.add(0);
    }
    mergeSort(numsWrapper, countList);
    return countList;
  }

  private void mergeSort(ElementWrapper[] nums, ArrayList<Integer> countList) {
    mergeSort(nums, 0, nums.length - 1, countList);
  }

  private void mergeSort(ElementWrapper[] nums, int left, int right, ArrayList<Integer> countList) {
    if (left >= right) {
      return;
    }
    int mid = (left + right) / 2;
    mergeSort(nums, left, mid, countList);
    mergeSort(nums, mid + 1, right, countList);
    merge(nums, left, right, countList);
  }

  private void merge(ElementWrapper[] nums, int left, int right, ArrayList<Integer> countList) {
    int mid = (left + right) / 2;
    ElementWrapper[] leftArray = new ElementWrapper[mid - left + 1];
    ElementWrapper[] rightArray = new ElementWrapper[right - mid];
    for (int i = left; i <= mid; i++) {
      leftArray[i - left] = nums[i];
    }
    for (int i = mid + 1; i <= right; i++) {
      rightArray[i - mid - 1] = nums[i];
    }
    int pLeft = 0, pRight = 0, pNums = left;
    while (pLeft < leftArray.length && pRight < rightArray.length) {
      if (leftArray[pLeft].value > rightArray[pRight].value) {
        nums[pNums++] = rightArray[pRight++];
      } else {
        countList.set(leftArray[pLeft].index, countList.get(leftArray[pLeft].index) + pRight);
        nums[pNums++] = leftArray[pLeft++];
      }
    }
    while (pLeft < leftArray.length) {
      countList.set(
          leftArray[pLeft].index, countList.get(leftArray[pLeft].index) + rightArray.length);
      nums[pNums++] = leftArray[pLeft++];
    }
  }
}
