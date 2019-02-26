package com.mirak.leetcode.individual.hard;

import java.util.*;

public class RangeModule {

  private ArrayList<int[]> original;
  private ArrayList<int[]> dummy;

  public RangeModule() {
    original = new ArrayList<>();
    dummy = new ArrayList<>();
  }

  public void addRange(int left, int right) {
    insertInterval(new int[]{left, right});
  }

  public boolean queryRange(int left, int right) {
    int index = floor(new int[]{left, right});
    if (index < 0 || index == original.size()) {
      return false;
    }
    return left >= original.get(index)[0] && right <= original.get(index)[1];
  }

  public void removeRange(int left, int right) {
    int[] removedPart = new int[]{left, right};
    for (int[] interval : original) {
      int[][] addedPart = getAddedPart(interval, removedPart);
      for (int[] a : addedPart) {
        if (a != null) {
          dummy.add(a);
        }
      }
    }
    swap();
  }

  private void insertInterval(int[] interval) {
    int index = floor(interval);
    for (int i = 0; i <= index; i++) {
      dummy.add(original.get(i));
    }

    if (dummy.isEmpty() || !intersect(dummy.get(dummy.size() - 1), interval)) {
      dummy.add(interval);
    } else {
      mergeIntervals(dummy.get(dummy.size() - 1), interval);
    }

    for (int i = index + 1; i < original.size(); i++) {
      if (intersect(dummy.get(dummy.size() - 1), original.get(i))) {
        mergeIntervals(dummy.get(dummy.size() - 1), original.get(i));
      } else {
        dummy.add(original.get(i));
      }
    }
    swap();
  }

  private int floor(int[] interval) {
    int low = 0;
    int high = original.size() - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (original.get(mid)[0] > interval[0]) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return high;
  }

  private boolean intersect(int[] a, int[] b) {
    return !(a[1] < b[0] || b[1] < a[0]);
  }

  private void mergeIntervals(int[] merged, int[] interval) {
    merged[1] = Math.max(merged[1], interval[1]);
  }

  private void swap() {
    ArrayList<int[]> temp = original;
    original = dummy;
    dummy = temp;
    dummy.clear();
  }

  private int[][] getAddedPart(int[] interval, int[] removedPart) {

    if (!intersect(interval, removedPart)) {
      return new int[][]{interval};
    }

    if (removedPart[0] >= interval[0] && removedPart[1] <= interval[1]) {
      int[] leftPart = {interval[0], removedPart[0]};
      int[] rightPart = {removedPart[1], interval[1]};
      return new int[][]{validInterval(leftPart) ? leftPart : null,
          validInterval(rightPart) ? rightPart : null};
    }

    if (removedPart[0] >= interval[0]) {
      interval[1] = removedPart[0];
    } else {
      interval[0] = removedPart[1];
    }
    return new int[][]{validInterval(interval) ? interval : null};
  }

  private boolean validInterval(int[] interval) {
    return interval[0] < interval[1];
  }
}


