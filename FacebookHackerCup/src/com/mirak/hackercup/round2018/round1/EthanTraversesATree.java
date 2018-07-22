package com.mirak.hackercup.round2018.round1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class EthanTraversesATree {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("/Users/masoud/Desktop/ethan_traverses_a_tree.txt"));
    BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/masoud/Desktop/out.txt"));
    int T = Integer.parseInt(reader.readLine());
    String[] str;
    for (int t = 1; t <= T; t++) {
      str = reader.readLine().split(" ");
      int n = Integer.parseInt(str[0]);

      int[] p = new int[n + 1];
      initP(p);

      ArrayList<Integer>[] adjList = new ArrayList[n + 1];
      initAdjList(adjList);

      int k = Integer.parseInt(str[1]);

      for (int i = 1; i <= n; i++) {
        str = reader.readLine().split(" ");
        adjList[i].add(Integer.parseInt(str[0]));
        adjList[i].add(Integer.parseInt(str[1]));
      }

      ArrayList<Integer> preOrderList = new ArrayList<>();
      preOrderTraversal(1, adjList, preOrderList);

      ArrayList<Integer> postOrderList = new ArrayList<>();
      postOrderTraversal(1, adjList, postOrderList);

      for (int i = 0; i < preOrderList.size(); i++) {
        union(preOrderList.get(i), postOrderList.get(i), p);
      }

      Map<Integer, Integer> nodeToLabel = new HashMap<>();
      int label = 1;
      for (int i = 1; i <= n; i++) {
        int currentParent = findParent(i, p);
        if (nodeToLabel.containsKey(currentParent)) {
          continue;
        }
        nodeToLabel.put(currentParent, label);
        label++;
        if (label > k) {
          label = 1;
        }
      }

      writer.write("Case #" + t + ":");

      if (nodeToLabel.size() < k) {
        // not enough parents.
        writer.write(" Impossible\n");
        continue;
      }

      for (int i = 1; i <= n; i++) {
        writer.write(" " + nodeToLabel.get(findParent(i, p)));
      }

      writer.write('\n');
    }
    reader.close();
    writer.close();
  }

  private static void initAdjList(ArrayList<Integer>[] adjList) {
    for (int i = 0; i < adjList.length; i++) {
      adjList[i] = new ArrayList<>();
    }
  }

  private static void initP(int[] p) {
    for (int i = 0; i < p.length; i++) {
      p[i] = i;
    }
  }

  private static void preOrderTraversal(int node, ArrayList<Integer>[] adjList,
      ArrayList<Integer> list) {
    if (node == 0) {
      return;
    }
    list.add(node);
    preOrderTraversal(adjList[node].get(0), adjList, list);
    preOrderTraversal(adjList[node].get(1), adjList, list);
  }

  private static void postOrderTraversal(int node, ArrayList<Integer>[] adjList,
      ArrayList<Integer> list) {
    if (node == 0) {
      return;
    }
    postOrderTraversal(adjList[node].get(0), adjList, list);
    postOrderTraversal(adjList[node].get(1), adjList, list);
    list.add(node);
  }

  private static int findParent(int i, int[] p) {
    if (p[i] == i) {
      return i;
    }
    return p[i] = findParent(p[i], p);
  }

  private static void union(int i, int j, int[] p) {
    if (Math.random() <= 0.5) {
      p[findParent(i, p)] = p[findParent(j, p)];
    } else {
      p[findParent(j, p)] = p[findParent(i, p)];
    }
  }

}
