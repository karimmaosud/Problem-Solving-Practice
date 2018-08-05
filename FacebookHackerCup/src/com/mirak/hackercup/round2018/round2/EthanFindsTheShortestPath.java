package com.mirak.hackercup.round2018.round2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EthanFindsTheShortestPath {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(
        new FileReader("/Users/masoud/Desktop/ethan_finds_the_shortest_path.txt"));

    BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/masoud/Desktop/out.txt"));
    int T = Integer.parseInt(reader.readLine());

    String[] strs;
    for(int t = 1; t <= T; t++) {
      strs = reader.readLine().split(" ");
      int n = Integer.parseInt(strs[0]);
      int k = Integer.parseInt(strs[1]);

      int[][] adjMatrix = new int[n + 1][n + 1];
      adjMatrix[1][n] = k;

      int weight = k - 1;
      int sum = 0;
      for(int i = 1; i < n; i++) {
        if(weight == 1) {
          sum += 1;
          adjMatrix[i][n] = 1;
          break;
        }
        if (weight == 0) {
          break;
        }
        sum+= weight;
        adjMatrix[i][i + 1] = weight--;
      }
      int edges = 0;
      for(int i = 1; i <= n; i++) {
        for(int j = i + 1; j <= n; j++) {
          if(adjMatrix[i][j] != 0) {
            edges++;
          }
        }
      }

      writer.write("Case #" + t + ": " + Math.max(0, (sum - adjMatrix[1][n])) + "\n");
      writer.write(edges + "\n");
      for(int i = 1; i <= n; i++) {
        for(int j = i + 1; j <= n; j++) {
          if(adjMatrix[i][j] != 0) {
            writer.write(i + " " + j + " " + adjMatrix[i][j] + "\n");
          }
        }
      }
    }

    reader.close();
    writer.close();
  }
}
