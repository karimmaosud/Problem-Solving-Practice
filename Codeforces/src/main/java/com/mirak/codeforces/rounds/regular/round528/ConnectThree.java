package com.mirak.codeforces.rounds.regular.round528;

import java.io.*;
import java.util.*;


public class ConnectThree {

  private static final int MAXN = 1000;

  public static void main(String[] args) throws IOException {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int[] a = readPoint(reader);
    int[] b = readPoint(reader);
    int[] c = readPoint(reader);

    int[] meetPoint = new int[2];
    int minTotalDist = Integer.MAX_VALUE;

    for (int i = 0; i <= MAXN; i++) {
      for (int j = 0; j <= MAXN; j++) {
        int dist = getDistance(i, j, a) + getDistance(i, j, b) + getDistance(i, j, c);
        if (dist < minTotalDist) {
          meetPoint[0] = i;
          meetPoint[1] = j;
          minTotalDist = dist;
        }
      }
    }

    int[] parent = new int[1 << 20];

    Arrays.fill(parent, -1);
    bfs(getMask(meetPoint[0], meetPoint[1]), parent, getMask(a[0], a[1]), getMask(b[0], b[1]),
        getMask(c[0], c[1]));

    Set<Integer> res = new HashSet<>();
    rollBackToMeetPoint(getMask(a[0], a[1]), res, parent);
    rollBackToMeetPoint(getMask(b[0], b[1]), res, parent);
    rollBackToMeetPoint(getMask(c[0], c[1]), res, parent);

    System.out.println(res.size());
    for (int point : res) {
      System.out.println(getX(point) + " " + getY(point));
    }
  }

  private static int getDistance(int i, int j, int[] p) {
    return Math.abs(p[0] - i) + Math.abs(p[1] - j);
  }

  private static int[] readPoint(BufferedReader reader) throws IOException {
    String[] pointArray = reader.readLine().split(" ");
    int[] ret = new int[2];
    ret[0] = Integer.parseInt(pointArray[0]);
    ret[1] = Integer.parseInt(pointArray[1]);
    return ret;
  }

  private static void bfs(int start, int[] parent, int a, int b, int c) {
    int[] rowInc = {1, -1, 0, 0};
    int[] colInc = {0, 0, 1, -1};

    boolean[] vis = new boolean[1 << 20];
    Queue<Integer> q = new LinkedList<>();
    q.add(start);
    vis[start] = true;
    while (!q.isEmpty()) {
      int top = q.poll();
      int x = getX(top);
      int y = getY(top);
      for (int i = 0; i < 4; i++) {
        int x_ = x + rowInc[i];
        int y_ = y + colInc[i];
        if (x_ < 0 || x_ > MAXN || y_ < 0 || y_ > MAXN) {
          continue;
        }
        int nextMask = getMask(x_, y_);
        if (vis[nextMask]) {
          continue;
        }
        vis[nextMask] = true;
        parent[nextMask] = top;
        q.add(nextMask);
      }
    }
  }

  private static void rollBackToMeetPoint(int start, Set<Integer> res, int[] parent) {
    while (start != -1) {
      res.add(start);
      start = parent[start];
    }
  }

  private static int getMask(int x, int y) {
    return x | (y << 10);
  }

  private static int getX(int mask) {
    return mask & ((1 << 10) - 1);
  }

  private static int getY(int mask) {
    return mask >> 10;
  }
}
