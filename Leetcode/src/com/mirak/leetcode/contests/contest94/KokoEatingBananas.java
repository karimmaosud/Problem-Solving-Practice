package com.mirak.leetcode.contests.contest94;

public class KokoEatingBananas {
  public int minEatingSpeed(int[] piles, int H) {
    return getMinK(piles, H);
  }

  private int getMinK(int[] piles, int H) {
    long low = 0;
    long high = 10000000000000000L;
    while (low <= high) {
      long mid = (low + high) / 2;
      int eatHours = getEatHours(piles, mid);
      if(eatHours <= H) {
        high = mid - 1;
      }else {
        low = mid + 1;
      }
    }
    return (int) low;
  }

  private int getEatHours(int[] piles, long mid) {
    int hours = 0;
    for(int i = 0; i < piles.length; i++) {
      hours += (int) Math.ceil((piles[i] * 1.0) / (1.0 * mid));
    }
    return hours;
  }
}
