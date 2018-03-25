package com.mirak.codeforces.rounds.regular.round471;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemB {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String str = reader.readLine();

    if(str.length() < 4){
      System.out.println("No");
      return;
    }

    int[] charCount = new int[26];
    for(char a: str.toCharArray()){
      charCount[a - 'a']++;
    }
    System.out.println(solve(charCount));
  }

  private static String solve(int[] charCount){
    int distinctChars = 0;
    for(int i = 0; i < charCount.length; i++){
      if(charCount[i] > 0){
        distinctChars++;
      }
    }

    if(distinctChars == 1 || distinctChars > 4){
      return "nO";
    }

    if(distinctChars == 3 || distinctChars == 4){
      return "yEs";
    }

    for(int i = 0; i < charCount.length; i++){
      if(charCount[i] == 1){
        return "nO";
      }
    }
    return "yEs";
  }
}
