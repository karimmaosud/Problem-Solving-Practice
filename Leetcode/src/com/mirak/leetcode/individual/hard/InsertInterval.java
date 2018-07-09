package com.mirak.leetcode.individual.hard;

import java.util.LinkedList;
import java.util.List;

public class InsertInterval {

  class Interval {

    int start;
    int end;

    Interval() {
      start = 0;
      end = 0;
    }

    Interval(int s, int e) {
      start = s;
      end = e;
    }
  }

  public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    List<Interval> mergedIntervals = new LinkedList<>();
    if (intervals.size() == 0) {
      mergedIntervals.add(newInterval);
      return mergedIntervals;
    }
    Interval lastInterval = null;
    boolean added = false;
    for (int i = 0; i < intervals.size(); i++) {
      if (lastInterval == null) {
        if (newInterval.start < intervals.get(i).start) {
          lastInterval = newInterval;
          i--;
        } else {
          lastInterval = intervals.get(i);
        }
        continue;
      }

      if (!added) {
        if (isOverlap(lastInterval, newInterval)) {
          // merge
          added = true;
          lastInterval.end = Math.max(lastInterval.end, newInterval.end);
        } else if (startsBefore(newInterval, intervals.get(i))) {
          // doesn't overlap with last interval, but comes before the next one
          mergedIntervals.add(lastInterval);
          lastInterval = newInterval;
          added = true;
        }

        if (added) {
          i--;
          continue;
        }
      }

      if (isOverlap(lastInterval, intervals.get(i))) {
        lastInterval.end = Math.max(lastInterval.end, intervals.get(i).end);
      } else {
        mergedIntervals.add(lastInterval);
        lastInterval = intervals.get(i);
      }

    }

    mergedIntervals.add(lastInterval);
    if (!added) {
      if (newInterval.start > ((LinkedList<Interval>) mergedIntervals).getLast().end) {
        mergedIntervals.add(newInterval);
      } else {
        ((LinkedList<Interval>) mergedIntervals).getLast().end = Math
            .max(((LinkedList<Interval>) mergedIntervals).getLast().end, newInterval.end);
      }
    }
    return mergedIntervals;
  }

  private boolean startsBefore(Interval left, Interval right) {
    return left.start <= right.start;
  }

  private boolean isOverlap(Interval left, Interval right) {

    return right.start <= left.end;
  }
}
