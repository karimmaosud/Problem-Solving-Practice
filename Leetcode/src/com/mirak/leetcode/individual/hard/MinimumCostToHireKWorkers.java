package com.mirak.leetcode.individual.hard;

import java.util.*;

public class MinimumCostToHireKWorkers {

  private class Entity implements Comparable<Entity> {

    int quality;
    int wage;
    double ratio;

    Entity(int quality, int wage) {
      this.quality = quality;
      this.wage = wage;
      this.ratio = (1.0 * wage) / (1.0 * quality);
    }

    @Override
    public int compareTo(Entity that) {
      return Double.compare(this.ratio, that.ratio);
    }

  }

  public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
    ArrayList<Entity> entities = new ArrayList<>();
    for (int i = 0; i < quality.length; i++) {
      entities.add(new Entity(quality[i], wage[i]));
    }
    Collections.sort(entities);

    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    pq.add(entities.get(0).quality);

    int sum = entities.get(0).quality;
    double ratio = entities.get(0).ratio;
    double ans = K == 1 ? ratio * sum : 1e9;
    for (int i = 1; i < entities.size(); i++) {
      if (pq.size() == K) {
        sum -= pq.poll();
      }
      if (entities.get(i).quality * ratio < entities.get(i).wage) {
        ratio = entities.get(i).ratio;
      }
      sum += entities.get(i).quality;
      pq.add(entities.get(i).quality);
      if (pq.size() == K) {
        ans = Math.min(ans, sum * ratio);
      }
    }
    return ans;
  }
}
