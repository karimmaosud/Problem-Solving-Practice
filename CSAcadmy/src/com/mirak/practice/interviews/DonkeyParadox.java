package com.mirak.practice.interviews;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class DonkeyParadox {
  private static class Cell{
    int row, column;
    Cell(int row, int column){
      this.row = row;
      this.column = column;
    }
  }
  public static void main (String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");
    int n = Integer.parseInt(strs[0]);
    int m = Integer.parseInt(strs[1]);
    strs = reader.readLine().split(" ");
    int r1 = Integer.parseInt(strs[0]) - 1;
    int c1 = Integer.parseInt(strs[1]) - 1;
    strs = reader.readLine().split(" ");
    int r2 = Integer.parseInt(strs[0]) - 1;
    int c2 = Integer.parseInt(strs[1]) - 1;
    int[][] dist1 = new int[n][m];
    int[][] dist2 = new int[n][m];
    init(dist1);
    init(dist2);
    bfs(dist1, r1, c1);
    bfs(dist2, r2, c2);
    int res = 0;
    for(int i = 0; i < n; i++){
      for(int j = 0; j < m; j++){
        if(dist1[i][j] == dist2[i][j]){
          res++;
        }
      }
    }
    System.out.println(res);
  }

  private static void init(int[][] dist){
    for(int i = 0; i < dist.length; i++){
      for(int j = 0 ; j < dist[i].length; j++){
        dist[i][j] = -1;
      }
    }
  }

  private static void bfs(int[][] dist, int r, int c){
    int n = dist.length;
    int m = dist[0].length;
    Queue<Cell> queue = new LinkedList<>();
    queue.add(new Cell(r, c));
    dist[r][c] = 0;

    while(!queue.isEmpty()) {
      Cell current = queue.poll();
      int[] rowInc = {1, -1, 0, 0};
      int[] columnInc = {0, 0, 1, -1};
      for(int i = 0; i < rowInc.length; i++){
        int nextRow = current.row + rowInc[i];
        int nextColumn = current.column + columnInc[i];
        if(validCell(nextRow, nextColumn, n, m) && dist[nextRow][nextColumn] == -1){
          dist[nextRow][nextColumn] = dist[current.row][current.column] + 1;
          queue.add(new Cell(nextRow, nextColumn));
        }
      }
    }
  }

  private static boolean validCell(int row, int column , int n, int m){
    return !(row < 0 || row == n || column < 0 || column == m);
  }
}
