package com.mirak.leetcode.individual.medium;

import java.util.*;

public class FindKClosestElements {

  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    LinkedList<Integer> resultSet = new LinkedList<>();

    int pLeft = binarySearch(arr, x);
    int pRight = pLeft + 1;

    while (k > 0) {
      boolean leftTaken = false;
      int difference = Integer.MAX_VALUE;
      if (pLeft >= 0) {
        difference = Math.abs(x - arr[pLeft]);
        leftTaken = true;
      }

      if (pRight < arr.length && Math.abs(arr[pRight] - x) < difference) {
        leftTaken = false;
      }

      if (leftTaken) {
        addElementToList(resultSet, arr[pLeft--]);
      } else {
        addElementToList(resultSet, arr[pRight++]);
      }
      k--;
    }
    return resultSet;
  }

  private int binarySearch(int[] arr, int x) {
    int low = 0;
    int high = arr.length - 1;
    while (low <= high) {
      int mid = (low + high) / 2;
      if (arr[mid] == x) {
        return mid;
      }
      if (arr[mid] > x) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return high;
  }

  private void addElementToList(LinkedList<Integer> resultSet, int num) {
    if (resultSet.isEmpty() || num <= resultSet.getFirst()) {
      resultSet.addFirst(num);
    } else {
      resultSet.addLast(num);
    }
  }
}
