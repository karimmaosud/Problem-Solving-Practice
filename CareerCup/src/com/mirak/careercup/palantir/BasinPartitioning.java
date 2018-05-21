package com.mirak.careercup.palantir;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class BasinPartitioning {

  public static void main(String[] args) throws IOException{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int s = Integer.parseInt(reader.readLine());

    int[][] grid = new int[s][s];
    String[] strs;
    for(int i = 0; i < s; i++) {
      strs = reader.readLine().split(" ");
      for(int j = 0; j < s; j++) {
        grid[i][j] = Integer.parseInt(strs[j]);
      }
    }

    int[][] p = new int[s][s];
    initP(p);

    for(int i = 0; i < s; i++) {
      for(int j = 0; j < s; j++) {
        int[] rowInc = {1, -1, 0, 0};
        int[] columnInc = {0, 0, 1, -1};
        int sinkI = i, sinkJ = j;
        for(int k = 0; k < rowInc.length; k++) {
          int iN = i + rowInc[k];
          int jN = j + columnInc[k];
          if(iN < 0 || iN == s || jN < 0 || jN == s) {
            continue;
          }
          if(grid[iN][jN] < grid[sinkI][sinkJ]) {
            sinkI = iN;
            sinkJ = jN;
          }
        }
        union(i, j, sinkI, sinkJ, p);
      }
    }
    Map<Integer, Integer> countMap = new HashMap<>();
    for(int i = 0; i < s; i++) {
      for(int j = 0; j < s; j++) {
        int parent = findParent(i, j, p);
        countMap.put(parent, countMap.getOrDefault(parent, 0) + 1);
      }
    }
    ArrayList<Integer> res = new ArrayList<>();
    for(Entry<Integer, Integer> entry: countMap.entrySet()) {
      res.add(entry.getValue());
    }
    Collections.sort(res, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2 - o1;
      }
    });
    for(int num: res) {
      System.out.print(num + " ");
    }
    System.out.println();
  }

  private static void initP(int[][] p){
    for(int i = 0; i < p.length; i++) {
      for(int j = 0; j < p[i].length; j++) {
        p[i][j] = i * p.length + j;
      }
    }
  }

  private static int findParent(int i, int j, int[][] p) {
    int parent = i * p.length + j;
    if(p[i][j] == parent) {
      return parent;
    }
    int pJ = p[i][j] % p.length;
    int pI = (p[i][j] - pJ) / p.length;
    p[i][j] = findParent(pI, pJ, p);
    return p[i][j];
  }

  private static void union(int i1, int j1, int i2, int j2, int[][] p) {
    int parent1 = findParent(i1, j1, p);
    int pJ1 = parent1 % p.length;
    int pI1 = (parent1 - pJ1) / p.length;
    p[pI1][pJ1] = findParent(i2, j2, p);
  }
}
