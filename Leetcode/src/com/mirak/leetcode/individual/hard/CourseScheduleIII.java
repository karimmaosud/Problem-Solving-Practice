package com.mirak.leetcode.individual.hard;

import java.util.*;

public class CourseScheduleIII {

  private final int INF = 1000000000;

  public int scheduleCourse(int[][] courses) {
    int n = courses.length;
    if (n == 0) {
      return 0;
    }

    Arrays.sort(courses, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[1] - o2[1] != 0 ? o1[1] - o2[1] : o1[0] - o2[0];
      }
    });

    PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o2[0] - o1[0];
      }
    });

    int sum = 0;
    int max = 0;
    for (int i = 0; i < courses.length; i++) {
      int[] course = courses[i];
      if (sum + course[0] <= course[1]) {
        pq.add(course);
        sum += course[0];
      } else {
        if (pq.isEmpty()) {
          continue;
        }
        if (course[0] < pq.peek()[0]) {
          sum -= pq.poll()[0];
          i--;
        }
      }
      max = Math.max(max, pq.size());
    }
    return max;
  }
}
