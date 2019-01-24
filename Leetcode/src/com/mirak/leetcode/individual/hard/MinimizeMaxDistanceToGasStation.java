package com.mirak.leetcode.individual.hard;

import java.util.*;

public class MinimizeMaxDistanceToGasStation {

  private class Interval {

    int count;
    double distance;

    Interval(double distance) {
      this.count = 1;
      this.distance = distance;
    }

    double getDistance() {
      return this.distance / this.count;
    }

    @Override
    public String toString() {
      return String.format("distance: %f - count: %d", distance, count);
    }
  }

  public double minmaxGasDist(int[] stations, int K) {
    PriorityQueue<Interval> pq = new PriorityQueue<>(new Comparator<Interval>() {
      @Override
      public int compare(Interval i1, Interval i2) {
        double diff = i1.getDistance() - i2.getDistance();
        if (diff > 0.0) {
          return -1;
        }
        if (diff < 0.0) {
          return 1;
        }
        return i2.count - i1.count;
      }
    });

    for (int i = 1; i < stations.length; i++) {
      pq.add(new Interval(1.0 * (stations[i] - stations[i - 1])));
    }

    for (int i = 0; i < K; i++) {
      Interval current = pq.poll();
      current.count++;
      pq.add(current);
    }
    return pq.poll().getDistance();
  }
}
