package com.mirak.practice.codeforces.rounds.regular.round468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemE {
  public static void main(String[] args)throws IOException{

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    char[] chars = reader.readLine().toCharArray();

    int n = chars.length;

    int[][][] firstCharLevelCharsCount = new int[26][n + 1][26];

    for(int i = 0; i < n; i++){
      int steps = 0;
      int level = 0;
      for(int j = i; steps < n; steps++, j = (j + 1) % n){
        firstCharLevelCharsCount[chars[i] - 'a'][level][chars[j] - 'a']++;
        level++;
      }
    }

    int res = 0;
    for(int i = 0; i < 26; i++){
      int count = 0;
      for(int j = 1; j < n; j++){
        int unique = 0;
        for(int k = 0; k < 26; k++){
          if(firstCharLevelCharsCount[i][j][k] == 1){
            unique++;
          }
        }
        count = Math.max(count, unique);
      }
      res += count;
    }
    System.out.println(res/(1.0 * n));

  }
}
