package com.mirak.practice.codeforces.rounds.regular.round470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemA {
  public static void main(String[] args)throws IOException{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");
    int n = Integer.parseInt(strs[0]);
    int m = Integer.parseInt(strs[1]);
    char[][] grid = new char[n][m];
    for(int i = 0; i < n; i++){
      char[] chars = reader.readLine().toCharArray();
      grid[i] = chars;
      for(int j = 0; j < m; j++){
        if(grid[i][j] == '.'){
          grid[i][j] = 'D';
        }
      }
    }
    boolean[][] vis = new boolean[n][m];
    for(int i = 0; i < n; i++){
      for(int j = 0; j < m; j++){
        if(grid[i][j] == 'W' && canReachAny(i, j, grid, vis)){
          System.out.println("No");
          return;
        }
      }
    }
    System.out.println("Yes");
    for(int i = 0; i < n; i++){
      System.out.println(new String(grid[i]));
    }
  }

  private static boolean canReachAny(int i, int j, char[][] grid, boolean[][] vis) {
    if(i < 0 || i == vis.length || j < 0 || j == vis[0].length || vis[i][j] || grid[i][j] == 'D'){
      return false;
    }
    if(grid[i][j] == 'S'){
      return true;
    }
    vis[i][j] = true;
    int[] rowInc = {1, -1, 0, 0};
    int[] colInc = {0, 0, 1, -1};
    boolean ans = false;
    for(int idx = 0; idx < 4; idx++){
      ans |= canReachAny(i + rowInc[idx], j + colInc[idx], grid, vis);
    }
    return ans;
  }
}
