package com.mirak.leetcode.individual.medium;

import java.util.*;

public class RandomPickWithWeight {

  class Solution {

    private int[] sum;
    private int[] w;
    private Random random;

    public Solution(int[] w) {
      random = new Random();
      this.w = w;
      sum = new int[w.length];
      sum[0] = w[0];
      for (int i = 1; i < w.length; i++) {
        sum[i] = w[i] + sum[i - 1];
      }
    }

    public int pickIndex() {
      int upperBound = sum[w.length - 1];
      int rand = 1 + random.nextInt(upperBound);
      return getWeightedIndex(rand);
    }

    private int getWeightedIndex(int key) {
      int low = 0;
      int high = sum.length - 1;
      while (low <= high) {
        int mid = low + (high - low) / 2;
        if (sum[mid] < key) {
          low = mid + 1;
        } else if (sum[mid] > key) {
          high = mid - 1;
        } else {
          return mid;
        }
      }
      return low;
    }
  }
}