package com.mirak.google.codejam.cg2018.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SavingTheUniverseAgain {
  public static void main(String[] args) throws IOException{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder builder = new StringBuilder();
    int T = Integer.parseInt(reader.readLine());
    for(int t = 1; t <= T; t++) {
      String[] strs = reader.readLine().split(" ");
      long D = Long.parseLong(strs[0]);
      char[] pChars = strs[1].toCharArray();
      int res = 0;
      while(getStringValue(pChars) > D) {
        boolean canMove = move(pChars);
        if(canMove) {
          res++;
        }else{
          res = -1;
          break;
        }
      }
      builder.append("Case #").append(t).append(": ").append(getResStr(res)).append('\n');
    }
    System.out.print(builder.toString());
  }

  private static long getStringValue(char[] chars){
    int power = 0;
    long sum = 0;
    for(char c: chars) {
      if(c == 'S') {
        sum += (1L << power);
      }else{
        power++;
      }
    }
    return sum;
  }
  private static boolean move(char[] pChars) {
    for(int i = pChars.length - 1; i > 0; i--) {
      if(pChars[i] == 'S' && pChars[i - 1] == 'C') {
        pChars[i] = 'C';
        pChars[i - 1] = 'S';
        return true;
      }
    }
    return false;
  }

  private static String getResStr(int res) {
    if(res == -1){
      return "IMPOSSIBLE";
    }
    return "" + res;
  }
}
