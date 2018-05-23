package com.mirak.leetcode.contests.individual.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EvaluateDivision {

  class Pair{
    int node;
    double value;
    Pair(int node, double value) {
      this.node = node;
      this.value = value;
    }
  }

  public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {

    if(queries.length == 0) {
      return new double[0];
    }

    Map<String, Integer> strToInteger = new HashMap<>();

    Map<Integer, ArrayList<Pair>> adjList = new HashMap<>();
    int mapping = 1;
    for(int i = 0; i < equations.length; i++) {
      String u = equations[i][0];
      String v =  equations[i][1];

      if(!strToInteger.containsKey(u)) {
        strToInteger.put(u, mapping++);
      }

      if(!strToInteger.containsKey(v)) {
        strToInteger.put(v, mapping++);
      }
      int uMapping = strToInteger.get(u);
      int vMapping = strToInteger.get(v);

      if(!adjList.containsKey(uMapping)) {
        adjList.put(uMapping, new ArrayList<>());
      }

      if(!adjList.containsKey(vMapping)) {
        adjList.put(vMapping, new ArrayList<>());
      }
      adjList.get(uMapping).add(new Pair(vMapping, values[i]));
      adjList.get(vMapping).add(new Pair(uMapping, 1.0 / values[i]));
    }

    double[] res = new double[queries.length];
    for(int i = 0; i < queries.length; i++) {
      boolean[] vis = new boolean[mapping + 1];
      String u = queries[i][0];
      String v = queries[i][1];
      if(!strToInteger.containsKey(u) || !strToInteger.containsKey(v)) {
        res[i] = -1.0;
        continue;
      }
      res[i] = getDivisionValue(strToInteger.get(u), strToInteger.get(v), adjList, vis);
    }
    return res;
  }

  private double getDivisionValue(int u, int dest, Map<Integer, ArrayList<Pair>> adjList, boolean[] vis) {

    if(!adjList.containsKey(u)) {
      return -1.0;
    }

    vis[u] = true;

    if(u == dest) {
      return 1.0;
    }

    ArrayList<Pair> list = adjList.get(u);
    double res = -1.0;
    for(Pair pair: list) {
      int v = pair.node;
      if(!vis[v]) {
        double ret = getDivisionValue(v, dest, adjList, vis);
        if(ret != -1.0) {
          return pair.value * ret;
        }
      }
    }

    return res;

  }
}
