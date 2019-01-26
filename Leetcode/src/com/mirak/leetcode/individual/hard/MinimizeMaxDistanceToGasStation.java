package com.mirak.leetcode.individual.hard;


public class MinimizeMaxDistanceToGasStation {

  public double minmaxGasDist(int[] stations, int K) {
    double low = 0.0;
    double high = 100000000.0;
    while (high - low > 1e-9) {
      double mid = (low + high) / 2.0;
      if (can(stations, K, mid)) {
        high = mid - 1e-6;
      } else {
        low = mid + 1e-6;
      }
    }
    return low;
  }

  private boolean can(int[] stations, int K, double minDistance) {
    for (int i = 0; i < stations.length - 1; i++) {
      double distance = 1.0 * (stations[i + 1] - stations[i]);
      if (distance <= minDistance) {
        continue;
      }
      K -= (int) Math.ceil(distance / minDistance) - 1;
      if (K < 0) {
        return false;
      }
    }
    return true;
  }
}
