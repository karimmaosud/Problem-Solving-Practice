package com.mirak.hackercup.round2018.round2;

import java.io.*;
import java.util.*;

public class JacksCandyShop {


  public static void main(String[] args) throws IOException {

    BufferedReader reader = new BufferedReader(
        new FileReader("/Users/masoud/Desktop/jacks_candy_shop.txt"));

    BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/masoud/Desktop/out.txt"));
    int T = Integer.parseInt(reader.readLine());
    String[] strs;
    for (int t = 1; t <= T; t++) {

      strs = reader.readLine().split(" ");
      int n = Integer.parseInt(strs[0]);
      int m = Integer.parseInt(strs[1]);
      int a = Integer.parseInt(strs[2]);
      int b = Integer.parseInt(strs[3]);

      Map<Integer, Integer> count = new HashMap<>();
      int[] c = new int[m];
      for (int i = 0; i < m; i++) {
        long num = (((long) i % n * a % n) + b % n) % n;
        c[i] = (int) num % n;
        count.put(c[i], count.getOrDefault(c[i], 0) + 1);
      }

      int[] p = new int[n];
      p[0] = -1;

      ArrayList<Integer>[] adjList = new ArrayList[n];
      for (int i = 0; i < n; i++) {
        adjList[i] = new ArrayList<>();
      }

      for (int i = 1; i < n; i++) {
        p[i] = Integer.parseInt(reader.readLine());
        adjList[p[i]].add(i);
      }

      PriorityQueue<Integer>[] nodePQ = new PriorityQueue[n];
      for (int i = 0; i < nodePQ.length; i++) {
        nodePQ[i] = new PriorityQueue<>(Collections.reverseOrder());
      }

      long sum = dfs(0, adjList, count, nodePQ);

      writer.write("Case #" + t + ": " + sum + "\n");
    }
    reader.close();
    writer.close();
  }

  private static long dfs(int u, ArrayList<Integer>[] adjList, Map<Integer, Integer> count,
      PriorityQueue<Integer>[] nodePQ) {

    long sum = 0;
    nodePQ[u].add(u);
    for (int v : adjList[u]) {
      sum += dfs(v, adjList, count, nodePQ);

      if (nodePQ[v].size() < nodePQ[u].size()) {
        while (!nodePQ[v].isEmpty()) {
          nodePQ[u].add(nodePQ[v].poll());
        }
      } else {
        while (!nodePQ[u].isEmpty()) {
          nodePQ[v].add(nodePQ[u].poll());
        }
        nodePQ[u] = nodePQ[v];
      }
    }

    while (!nodePQ[u].isEmpty() && count.getOrDefault(u, 0) > 0) {
      sum += nodePQ[u].poll();
      count.put(u, count.get(u) - 1);
    }
    return sum;
  }

}
