package com.mirak.leetcode.individual.hard;

public class MedianOfTwoSortedArrays {

  private class Pair {
    int idx1, idx2;

    Pair(int idx1, int idx2) {
      this.idx1 = idx1;
      this.idx2 = idx2;
    }
  }

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int n = nums1.length;
    int m = nums2.length;
    int idx1 = (int) Math.ceil((n + m) / 2.0);
    double ret = getKthNumber(nums1, nums2, idx1);
    if (((n + m) & 1) != 1) {
      return (ret + getKthNumber(nums1, nums2, idx1 + 1)) / 2.0;
    }
    return ret;
  }

  private int getKthNumber(int[] nums1, int[] nums2, int k) {

    Pair pair = getRank(nums1, nums2, k);

    if (pair.idx1 == -1) {
      return nums2[k - 1];
    }

    int rank = pair.idx1 + pair.idx2 + 2;
    if (rank < k) {
      return nums2[k - pair.idx1 - 2];
    }
    return nums1[pair.idx1];
  }

  private Pair getRank(int[] nums1, int[] nums2, int k) {
    int low = 0;
    int high = nums1.length - 1;
    int highRank = -1;
    while (low <= high) {
      int mid = (low + high) / 2;
      int rank = mid + 2 + getRankInArray(nums2, nums1[mid]);
      if (rank < k) {
        highRank = rank;
        low = mid + 1;
      } else if (rank > k) {
        high = mid - 1;
      } else {
        return new Pair(mid, rank - mid - 2);
      }
    }

    return new Pair(high, highRank - high - 2);
  }

  private int getRankInArray(int[] nums, int key) {
    int low = 0;
    int high = nums.length - 1;
    while (low <= high) {
      int mid = (low + high) / 2;
      if (nums[mid] >= key) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return high;
  }
}
