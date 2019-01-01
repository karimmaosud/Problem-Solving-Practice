package com.mirak.leetcode.individual.medium;

public class SearchInASortedArrayOfUnknownSize {
  private class ArrayReader {
    int get(int index) {
      return -1;
    }
  }
  public int search(ArrayReader reader, int target) {
    int high = 1;
    while (target > reader.get(high)) {
      high <<= 1;
    }

    int low = high >> 1;
    System.out.println(low + " " + high);
    while (low <= high) {
      int mid = (low + high) / 2;
      int element = reader.get(mid);
      if (element > target) {
        high = mid - 1;
      } else if (element < target) {
        low = mid + 1;
      } else {
        return mid;
      }
    }
    return -1;
  }
}
