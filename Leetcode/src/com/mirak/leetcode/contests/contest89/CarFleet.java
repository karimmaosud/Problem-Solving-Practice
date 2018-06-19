package com.mirak.leetcode.contests.contest89;

public class CarFleet {
  private int MAXN = 1000010;
  public int carFleet(int target, int[] position, int[] speed) {
    int fleets = 0;
    int[] speedAtPosition = new int[MAXN];
    for(int i = 0; i < MAXN; i++) {
      speedAtPosition[i] = Integer.MAX_VALUE;
    }

    for(int i = 0; i < position.length; i++) {
      speedAtPosition[position[i]] = Math.min(speedAtPosition[position[i]], speed[i]);
    }

    double TTD = -1.0;
    for(int i = target; i >= 0; i--) {
      if(speedAtPosition[i] < Integer.MAX_VALUE) {
        double currentTTD = ((1.0 * (target - i)) / speedAtPosition[i]);
        int compare = Double.compare(currentTTD, TTD);
        if(compare > 0) {
          // current will arrive after next fleet
          fleets++;
          TTD = currentTTD;
        }
      }
    }
    return fleets;
  }
}
