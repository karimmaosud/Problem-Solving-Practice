package com.mirak.codeforces.individual.div2.D;

import java.io.*;
import java.util.*;

public class DistanceInTree {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");
    int n = Integer.parseInt(strs[0]);
    int k = Integer.parseInt(strs[1]);

    ArrayList<Integer>[] adjList = new ArrayList[n + 1];
    for (int i = 0; i < adjList.length; i++) {
      adjList[i] = new ArrayList<>();
    }

    int[] parent = new int[n + 1];
    Arrays.fill(parent, -1);

    for (int i = 0; i < n - 1; i++) {
      strs = reader.readLine().split(" ");
      int u = Integer.parseInt(strs[0]);
      int v = Integer.parseInt(strs[1]);
      adjList[u].add(v);
      adjList[v].add(u);
    }

    long[][] rootedCount = new long[n + 1][k + 1];
    dfs(-1, 1, adjList, parent, rootedCount, k);

    System.out.println(countPairs(1, parent, adjList, rootedCount, k) / 2);
  }

  private static void dfs(int p, int u, ArrayList<Integer>[] adjList, int[] parent,
      long[][] rootedCount, int k) {

    parent[u] = p;

    for (int i = 0; i < adjList[u].size(); i++) {
      int v = adjList[u].get(i);
      if (v == p) {
        continue;
      }
      dfs(u, v, adjList, parent, rootedCount, k);
    }
    rootedCount[u][0] = 1;
    rootedCount[u][1] = adjList[u].size();

    if (p != -1) {
      rootedCount[u][1]--;
    }

    for (int i = 0; i < adjList[u].size(); i++) {
      int v = adjList[u].get(i);
      if (v == p) {
        continue;
      }

      for (int j = 1; j < k; j++) {
        if (rootedCount[v][j] == 0) {
          break;
        }
        rootedCount[u][j + 1] += rootedCount[v][j];
      }
    }
  }

  private static long countPairs(int u, int[] parent, ArrayList<Integer>[] adjList,
      long[][] rootedCount, int k) {

    long ans = rootedCount[u][k];

    int walk = 1;
    int uP = u;
    int p = parent[u];
    while (walk <= k && p != -1) {
      long tempCount = rootedCount[p][k - walk];
      if (k - walk - 1 >= 0) {
        tempCount -= rootedCount[uP][k - walk - 1];
      }
      ans += tempCount;
      uP = p;
      p = parent[uP];
      walk++;
    }

    for (int i = 0; i < adjList[u].size(); i++) {
      int v = adjList[u].get(i);
      if (v != parent[u]) {
        ans += countPairs(v, parent, adjList, rootedCount, k);
      }
    }
    return ans;
  }
}
