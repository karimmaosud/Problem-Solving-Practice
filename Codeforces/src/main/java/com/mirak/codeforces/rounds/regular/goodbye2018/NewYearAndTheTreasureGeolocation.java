package com.mirak.codeforces.rounds.regular.goodbye2018;

import java.awt.Point;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;


public class NewYearAndTheTreasureGeolocation {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    String[] strs;
    Point[] obsilks = new Point[n];
    for (int i = 0; i < n; i++) {
      strs = reader.readLine().split(" ");
      obsilks[i] = new Point(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]));
    }
    Point[] clues = new Point[n];
    for (int i = 0; i < n; i++) {
      strs = reader.readLine().split(" ");
      clues[i] = new Point(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]));
    }

    Map<Point, Integer> locationCount = new HashMap<>();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        Point newLocation = new Point(obsilks[i].x + clues[j].x, obsilks[i].y + clues[j].y);
        locationCount.put(newLocation, locationCount.getOrDefault(newLocation, 0) + 1);
      }
    }

    for (Entry<Point,Integer> entry : locationCount.entrySet()) {
      if (entry.getValue() == n) {
        System.out.println(entry.getKey().x + " " + entry.getKey().y);
        return;
      }
    }
  }
}
