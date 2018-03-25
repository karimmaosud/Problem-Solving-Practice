package com.mirak.codeforces.rounds.regular.round468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ProblemD {
  public static void main(String[] args)throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(reader.readLine());
    String[] strs = reader.readLine().split(" ");
    ArrayList<Integer>[] adjList = new ArrayList[n + 1];
    for(int i = 0; i < adjList.length; i++){
      adjList[i] = new ArrayList<Integer>();
    }
    for(int i = 2; i <= n; i++){
      int p = Integer.parseInt(strs[i - 2]);
      adjList[p].add(i);
    }
    Queue<Integer> q = new LinkedList<Integer>();
    int add = (adjList[1].size() & 1);
    int res = 1;
    q.add(1);
    while(!q.isEmpty()){
      res += add;
      add = 0;
      int size = q.size();
      for(int i = 0; i < size; i++){
        int node = q.poll();
        for(int v: adjList[node]){
          add ^= (adjList[v].size() & 1);
          q.add(v);
        }
      }
    }
    System.out.println(res);
  }
}
