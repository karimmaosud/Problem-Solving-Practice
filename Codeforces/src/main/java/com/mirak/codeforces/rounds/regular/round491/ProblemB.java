package com.mirak.codeforces.rounds.regular.round491;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class ProblemB {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    String[] strs = reader.readLine().split(" ");
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int sum = 0;
    for(String num: strs) {
      int current = Integer.parseInt(num);
      sum += current;
      pq.add(current);
    }
    int res = 0;
    while(Math.round((sum * 1.0f) / (1.0f * n)) < 5) {
      res++;
      int top = pq.poll();
      sum -= top;
      sum += 5;
      pq.add(5);
    }
    System.out.println(res);
  }
}
