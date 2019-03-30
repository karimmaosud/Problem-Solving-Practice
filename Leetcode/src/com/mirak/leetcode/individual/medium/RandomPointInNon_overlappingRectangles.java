package com.mirak.leetcode.individual.medium;

import java.util.*;

public class RandomPointInNon_overlappingRectangles {

  private int[][] rects;
  private int[] areaSum;
  private Random random;

  public RandomPointInNon_overlappingRectangles(int[][] rects) {
    this.rects = rects;
    this.areaSum = new int[rects.length];
    random = new Random();
    initAreaSum();
  }

  private void initAreaSum() {
    for (int i = 0; i < rects.length; i++) {
      areaSum[i] = rectangleArea(rects[i]) + (i > 0 ? areaSum[i - 1] : 0);
    }
  }

  public int[] pick() {
    int randomArea = 1 + random.nextInt(areaSum[rects.length - 1]);
    int rectangleIndex = rectangleIndexWithAreaRange(randomArea);
    return getRandomPointInsideRectangle(rects[rectangleIndex],
        areaSum[rectangleIndex] - randomArea + 1);
  }

  private int rectangleIndexWithAreaRange(int area) {
    int low = 0;
    int high = areaSum.length - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (areaSum[mid] == area) {
        return mid;
      }
      if (areaSum[mid] < area) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return low;
  }

  private int rectangleArea(int[] rec) {
    return (rec[2] - rec[0] + 1) * (rec[3] - rec[1] + 1);
  }

  private int[] getRandomPointInsideRectangle(int[] rec, int index) {
    int xLen = rec[2] - rec[0] + 1;
    return new int[]{rec[0] + index % xLen, rec[1] + (index - 1) / xLen};
  }


}
