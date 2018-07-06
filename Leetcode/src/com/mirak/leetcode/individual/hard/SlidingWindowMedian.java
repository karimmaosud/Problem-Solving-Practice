package com.mirak.leetcode.individual.hard;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SlidingWindowMedian {

  private class BSTWrapper {

    PriorityQueue<Integer> pq;
    Map<Integer, Integer> count;
    int size;
    boolean minHeap;

    BSTWrapper(boolean minHeap) {
      this.minHeap = minHeap;
      if (minHeap) {
        this.pq = new PriorityQueue<>();
      } else {
        this.pq = new PriorityQueue<>(Collections.reverseOrder());
      }
      this.count = new HashMap<>();
      this.size = 0;
    }

    int peek() {
      return pq.peek();
    }

    void add(int a) {
      count.put(a, count.getOrDefault(a, 0) + 1);
      pq.add(a);
      size++;
    }

    void remove(int a) {
      int currentCount = count.get(a);
      if (currentCount > 1) {
        count.put(a, currentCount - 1);
      } else {
        count.remove(a);
      }

      while (!pq.isEmpty() && !count.containsKey(pq.peek())) {
        pq.poll();
      }
      size--;
    }

    boolean isEmpty() {
      return size == 0;
    }
  }

  private class MedianManipulator {

    BSTWrapper minHeap;
    BSTWrapper maxHeap;

    MedianManipulator() {
      this.minHeap = new BSTWrapper(true);
      this.maxHeap = new BSTWrapper(false);
    }

    double add(int a) {
      if (maxHeap.isEmpty() || a <= maxHeap.peek()) {
        // insert into left.
        maxHeap.add(a);
      } else {
        minHeap.add(a);
      }

      balanceHeaps();

      return median();
    }

    void balanceHeaps() {
      if (maxHeap.size - minHeap.size > 1) {
        int removed = maxHeap.peek();
        maxHeap.remove(removed);
        minHeap.add(removed);
        return;
      }

      if (minHeap.size - maxHeap.size > 0) {
        int removed = minHeap.peek();
        minHeap.remove(removed);
        maxHeap.add(removed);
      }
    }

    double median() {
      if (minHeap.size == maxHeap.size) {
        return (((double) minHeap.peek() + maxHeap.peek()) / 2.0);
      }
      return maxHeap.peek();
    }

    void remove(int a) {
      if (a <= maxHeap.peek()) {
        maxHeap.remove(a);
      } else {
        minHeap.remove(a);
      }
      balanceHeaps();
    }
  }

  public double[] medianSlidingWindow(int[] nums, int k) {
    double[] medians = new double[nums.length - k + 1];
    MedianManipulator medianManipulator = new MedianManipulator();
    for (int i = 0; i < k; i++) {
      medianManipulator.add(nums[i]);
    }
    medians[0] = medianManipulator.median();
    for (int i = k; i < nums.length; i++) {
      medianManipulator.remove(nums[i - k]);
      medians[i - k + 1] = medianManipulator.add(nums[i]);
    }
    return medians;
  }

}
