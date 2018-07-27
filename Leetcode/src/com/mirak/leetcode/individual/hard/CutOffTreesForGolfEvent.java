package com.mirak.leetcode.individual.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CutOffTreesForGolfEvent {

  private int[] rowInc = {1, -1, 0, 0};
  private int[] colInc = {0, 0, 1, -1};

  private final int SHIFT = 6;

  public int cutOffTree(List<List<Integer>> forest) {
    int n = forest.size();
    if (n == 0) {
      return -1;
    }

    int m = forest.get(0).size();
    int[][] matrix = new int[n][m];

    Map<Integer, Integer> numIndex = new HashMap<>();
    ArrayList<Integer> nums = new ArrayList<>();

    for (int i = 0; i < forest.size(); i++) {

      if (forest.get(i).size() != m) {
        return -1;
      }

      for (int j = 0; j < forest.get(i).size(); j++) {
        matrix[i][j] = forest.get(i).get(j);
        if (matrix[i][j] > 1) {
          nums.add(matrix[i][j]);
          numIndex.put(matrix[i][j], (i | (j << SHIFT)));
        }
      }
    }

    int res = 0;
    Collections.sort(nums);
    int currentLocation = 0;
    for (int i = 0; i < nums.size(); i++) {
      int dist = bfs(currentLocation, numIndex.get(nums.get(i)), matrix);
      if (dist == Integer.MAX_VALUE) {
        return -1;
      }
      res += dist;
      currentLocation = numIndex.get(nums.get(i));
    }
    return res;
  }

  private int bfs(int startLocation, int endLocation, int[][] matrix) {
    int[] dist = new int[1 << 12];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[startLocation] = 0;
    Queue<Integer> q = new LinkedList<>();
    q.add(startLocation);
    while (!q.isEmpty()) {
      int location = q.poll();
      if (location == endLocation) {
        break;
      }
      int i = location & ((1 << SHIFT) - 1);
      int j = location >> SHIFT;
      for (int k = 0; k < 4; k++) {
        int i_ = i + rowInc[k];
        int j_ = j + colInc[k];
        if (i_ < 0 || i_ == matrix.length || j_ < 0 || j_ == matrix[0].length || matrix[i][j] == 0) {
          continue;
        }
        int nextLocation = i_ | (j_ << SHIFT);
        if (dist[nextLocation] == Integer.MAX_VALUE) {
          dist[nextLocation] = dist[location] + 1;
          q.add(nextLocation);
        }
      }
    }

    return dist[endLocation];
  }
}
