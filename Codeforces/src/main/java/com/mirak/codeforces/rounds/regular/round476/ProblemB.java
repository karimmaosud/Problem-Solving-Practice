package com.mirak.codeforces.rounds.regular.round476;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemB {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");
    int n = Integer.parseInt(strs[0]);
    int k = Integer.parseInt(strs[1]);
    char[][] grid = new char[n][n];
    for(int i = 0; i < n; i++) {
      grid[i] = reader.readLine().toCharArray();
    }
    int[][] sumRowLeft = new int[n][n];
    int[][] sumRowRight = new int[n][n];
    int[][] sumColumnUp = new int[n][n];
    int[][] sumColumnDown = new int[n][n];
    init(grid, sumRowLeft, sumRowRight, sumColumnUp, sumColumnDown);
    int res_i = 1, res_j = 1, max = -1;
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        if(grid[i][j] == '#'){
          continue;
        }
        int totalInRow = Math.min(k - 1, sumRowLeft[i][j] - 1) + Math.min(k - 1, sumRowRight[i][j] - 1) + 1;
        int totalInColumn = Math.min(k - 1, sumColumnDown[i][j] - 1) + Math.min(k - 1, sumColumnUp[i][j] - 1) + 1;
        int current = Math.min(k, Math.max(0, totalInRow - k + 1)) + Math.min(k, Math.max(0, totalInColumn - k + 1));
        if(max < current){
          max = current;
          res_i = i + 1;
          res_j = j + 1;
        }
      }
    }
    System.out.println(res_i + " " + res_j);
  }
  private static void init(char[][] grid, int[][] sumRowLeft, int[][] sumRowRight, int[][] sumColumnUp
      , int[][] sumColumnDown) {
    int n = grid.length;
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        if(grid[i][j] == '#'){
          continue;
        }

        sumRowLeft[i][j] = 1;
        sumColumnDown[i][j] = 1;

        if(j - 1 >= 0) {
          sumRowLeft[i][j] += sumRowLeft[i][j - 1];
        }
        if(i - 1 >= 0) {
          sumColumnDown[i][j] += sumColumnDown[i - 1][j];
        }
      }
    }

    for(int i = n - 1; i >= 0; i--) {
      for(int j = n - 1; j >= 0; j--) {
        if(grid[i][j] == '#'){
          continue;
        }
        sumRowRight[i][j] = sumColumnUp[i][j] = 1;
        if(j + 1 < n) {
          sumRowRight[i][j] += sumRowRight[i][j + 1];
        }
        if(i + 1 < n) {
          sumColumnUp[i][j] += sumColumnUp[i + 1][j];
        }
      }
    }
  }
}
