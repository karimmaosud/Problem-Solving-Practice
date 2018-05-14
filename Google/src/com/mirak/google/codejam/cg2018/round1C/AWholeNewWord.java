package com.mirak.google.codejam.cg2018.round1C;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AWholeNewWord {
  public static void main(String[] args) throws IOException {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(reader.readLine());
    for(int t = 1; t <= T; t++) {
      String[] strs = reader.readLine().split(" ");
      int n = Integer.parseInt(strs[0]);
      int l = Integer.parseInt(strs[1]);
      char[][] grid = new char[n][l];
      for(int i = 0; i < n; i++) {
        grid[i] = reader.readLine().toCharArray();
      }
      boolean[][][] adj = new boolean[26][26][l];
      boolean[][] exist = new boolean[26][l];
      for(int j = 0; j < l - 1; j++) {
        for(int i = 0; i < n; i++) {
          exist[grid[i][j]- 'A'][j] = true;
          exist[grid[i][j + 1] - 'A'][j + 1] = true;
          adj[grid[i][j] - 'A'][grid[i][j + 1] - 'A'][j] = true;
        }
      }
      int level = -1, first = -1, second = -1;

      for(int j = 0; j < l - 1; j++) {
       for(int c1 = 0; c1 < 26; c1++) {
         if(!exist[c1][j]) {
           continue;
         }
         for(int c2 = 0; c2 < 26; c2++) {
           if(!exist[c2][j + 1]){
             continue;
           }
           if(!adj[c1][c2][j]) {
             level = j;
             first = c1;
             second = c2;
             break;
           }
         }
         if(level != -1){
           break;
         }
       }
       if(level != -1){
         break;
       }
      }
      System.out.print("Case #" + t +": ");
      if(level == -1){
        System.out.println("-");
        continue;
      }
      StringBuilder result = new StringBuilder();
      int j = 0;
      while(j < level) {
        result.append(grid[0][j]);
        j++;
      }
      result.append((char)('A' + first)).append((char)('A' + second));
      j += 2;
      while (j < l){
        result.append(grid[0][j]);
        j++;
      }
      System.out.println(result);
    }
  }
}
