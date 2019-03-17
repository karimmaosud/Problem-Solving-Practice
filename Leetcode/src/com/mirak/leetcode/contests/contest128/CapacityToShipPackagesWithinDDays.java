package com.mirak.leetcode.contests.contest128;

public class CapacityToShipPackagesWithinDDays {

  public int shipWithinDays(int[] weights, int D) {
    int low = 0;
    for (int weight : weights) {
      low = Math.max(low, weight);
    }
    int high = low * weights.length;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (countDays(mid, weights) <= D) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }

  private int countDays(int shipWeight, int[] weights) {
    int sum = 0;
    int days = 1;
    for (int weight : weights) {
      if (weight + sum <= shipWeight) {
        sum += weight;
      } else {
        days++;
        sum = weight;
      }
    }
    return days;
  }
}
