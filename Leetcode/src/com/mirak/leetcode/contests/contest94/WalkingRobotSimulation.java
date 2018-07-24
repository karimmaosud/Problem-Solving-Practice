package com.mirak.leetcode.contests.contest94;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class WalkingRobotSimulation {

  private static final int INF = 1000000000;

  private static enum Direction {
    NORTH, SOUTH, EAST, WEST
  }

  private static Direction[] rotationDirections = {Direction.NORTH, Direction.EAST, Direction.SOUTH,
      Direction.WEST};

  public static int robotSim(int[] commands, int[][] obstacles) {

    int ans = 0;

    Map<Integer, ArrayList<Integer>> xMap = new HashMap<>();
    Map<Integer, ArrayList<Integer>> yMap = new HashMap<>();
    initMaps(obstacles, xMap, yMap);

    int x = 0;
    int y = 0;
    int rotationIndex = 0;
    Direction direction = Direction.NORTH;

    for (int i = 0; i < commands.length; i++) {
      if (commands[i] == -1) {
        direction = rotationDirections[(rotationIndex + 1) % 4];
        rotationIndex = (rotationIndex + 1) % 4;
      } else if (commands[i] == -2) {
        direction = rotationDirections[(rotationIndex - 1 + 4) % 4];
        rotationIndex = (rotationIndex + 3) % 4;
      } else {
        if (direction == Direction.EAST || direction == Direction.WEST) {
          // x move
          int xObstacle = direction == Direction.EAST ? getSmallestGreater(yMap.get(y), x)
              : getMaximumSmaller(yMap.get(y), x);
          int finalDestination = direction == Direction.EAST ? x + commands[i] : x - commands[i];
          if (obstacleFound(x, finalDestination, xObstacle)) {
            x = beforeObstaclePosition(xObstacle, direction);
          } else {
            x = finalDestination;
          }
        } else {
          int yObstacle = direction == Direction.NORTH ? getSmallestGreater(xMap.get(x), y)
              : getMaximumSmaller(xMap.get(x), y);
          int finalDestination = direction == Direction.NORTH ? y + commands[i] : y - commands[i];
          if (obstacleFound(y, finalDestination, yObstacle)) {
            y = beforeObstaclePosition(yObstacle, direction);
          } else {
            y = finalDestination;
          }
        }
      }
      ans = Math.max(ans, x * x + y * y);
    }
    return ans;
  }

  private static void initMaps(int[][] obstacles, Map<Integer, ArrayList<Integer>> xMap,
      Map<Integer, ArrayList<Integer>> yMap) {
    for (int i = 0; i < obstacles.length; i++) {
      int x = obstacles[i][0];
      int y = obstacles[i][1];
      xMap.putIfAbsent(x, new ArrayList<>());
      yMap.putIfAbsent(y, new ArrayList<>());
      xMap.get(x).add(y);
      yMap.get(y).add(x);
    }
    sortMapEntries(xMap);
    sortMapEntries(yMap);
  }

  private static void sortMapEntries(Map<Integer, ArrayList<Integer>> map) {
    for (Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
      Collections.sort(entry.getValue());
    }
  }

  private static int getSmallestGreater(ArrayList<Integer> list, int key) {
    if (list == null) {
      return INF;
    }
    int low = 0;
    int high = list.size() - 1;
    while (low <= high) {
      int mid = (low + high) / 2;
      if (list.get(mid) <= key) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    if (low == list.size()) {
      return INF;
    }
    return list.get(low);
  }

  private static int getMaximumSmaller(ArrayList<Integer> list, int key) {
    if (list == null) {
      return INF;
    }
    int low = list.size() - 1;
    int high = 0;
    while (low >= high) {
      int mid = (low + high) / 2;
      if (list.get(mid) >= key) {
        low = mid - 1;
      } else {
        high = mid + 1;
      }
    }
    if (low == -1) {
      return INF;
    }
    return list.get(low);
  }

  private static boolean obstacleFound(int start, int end, int obstacle) {
    if (obstacle == INF) {
      return false;
    }
    return obstacle >= Math.min(start, end) && obstacle <= Math.max(start, end);
  }

  private static int beforeObstaclePosition(int position, Direction direction) {

    if (direction == Direction.EAST || direction == Direction.NORTH) {
      return position - 1;
    }
    return position + 1;
  }

  public static void main(String[] args) {
    int[] moves = {7,-2,-2,7,5};
    int[][] obstacles = {{-3,2},{-2,1},{0,1},{-2,4},{-1,0},{-2,-3},{0,-3},{4,4},{-3,3},{2,2}};
    System.out.println(robotSim(moves, obstacles));
  }

}
