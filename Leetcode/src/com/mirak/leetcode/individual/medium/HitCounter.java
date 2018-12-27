package com.mirak.leetcode.individual.medium;

import java.util.*;

public class HitCounter {

  private class HitObject {

    int timestamp;
    int count;

    HitObject(int timestamp) {
      this.timestamp = timestamp;
      this.count = 1;
    }

    void addHit() {
      this.count++;
    }
  }

  private int hitsCount;

  private LinkedList<HitObject> hits;

  /**
   * Initialize your data structure here.
   */
  public HitCounter() {
    hits = new LinkedList<>();
    hitsCount = 0;
  }

  /**
   * Record a hit.
   *
   * @param timestamp - The current timestamp (in seconds granularity).
   */
  public void hit(int timestamp) {

    if (hits.isEmpty() || hits.getLast().timestamp != timestamp) {
      hits.add(new HitObject(timestamp));
    } else {
      hits.getLast().addHit();
    }
    hitsCount++;
    adjustHitsQueue(hits.getLast().timestamp);
  }

  /**
   * Return the number of hits in the past 5 minutes.
   *
   * @param timestamp - The current timestamp (in seconds granularity).
   */
  public int getHits(int timestamp) {
    adjustHitsQueue(timestamp);
    return hitsCount;
  }

  private void adjustHitsQueue(int timestamp) {
    while (!hits.isEmpty() && timestamp - hits.getFirst().timestamp + 1 > 300) {
      hitsCount -= hits.removeFirst().count;
    }
  }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */