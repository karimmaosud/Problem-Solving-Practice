package com.mirak.codeforces.rounds.regular.round530;

import java.io.*;
import java.util.*;

public class SumInThetree {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(reader.readLine());
    ArrayList<Integer>[] adjList = new ArrayList[n + 1];

    for (int i = 0; i < adjList.length; i++) {
      adjList[i] = new ArrayList<>();
    }

    String[] strs = reader.readLine().split(" ");
    int[] p = new int[n + 1];
    p[1] = -1;
    for (int i = 2; i <= n; i++) {
      p[i] = Integer.parseInt(strs[i - 2]);
      adjList[p[i]].add(i);
      adjList[i].add(p[i]);
    }
    strs = reader.readLine().split(" ");
    int[] s = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      s[i] = Integer.parseInt(strs[i - 1]);
    }

    int[] a = new int[n + 1];
    boolean[] vis = new boolean[n + 1];
    Queue<Integer> q = new LinkedList<>();
    q.add(1);
    vis[1] = true;
    int h = 1;
    while (!q.isEmpty()) {
      int size = q.size();

      for (int i = 0; i < size; i++) {
        int node = q.poll();

        if ((h & 1) != 1) {
          // even node.
          s[node] = s[p[node]];
        } else {
          if (p[node] != -1 && s[node] < s[p[node]]) {
            System.out.println(-1);
            return;
          }
          a[node] = s[node] - (p[node] == -1 ? 0 : s[p[node]]);
        }
        int min = Integer.MAX_VALUE;
        for (int v : adjList[node]) {
          if (!vis[v]) {
            q.add(v);
            vis[v] = true;
            min = Math.min(min, s[v]);
          }
        }

        if ((h & 1) == 1) {
          continue;
        }

        if (min == Integer.MAX_VALUE) {
          continue;
        }
        if (min < s[node]) {
          System.out.println(-1);
          return;
        }
        int diff = min - s[node];
        a[node] = diff;
        s[node] += diff;
      }
      h++;
    }
    long finalSum = 0;
    for (int i = 1; i <= n; i++) {
      finalSum += a[i];
    }
    System.out.println(finalSum);
  }
}
