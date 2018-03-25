package com.mirak.codeforces.rounds.regular.round468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemB {
  public static void main(String[] args)throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");
    int n = Integer.parseInt(strs[0]);
    int a = Integer.parseInt(strs[1]);
    int b = Integer.parseInt(strs[2]);

    int finalRound = getLogBaseTwo(n);

    while(n > 1){
      // 1 ---> n/2, n/2 ---> n
      if(inTwoHalfs(a, b, n)){
        break;
      }
      if(a > (n/2) && b > (n/2)){
        a -= (n/2);
        b -= (n/2);
      }

      n >>= 1;
    }
    int meetingRound = getLogBaseTwo(n);
    if(meetingRound == finalRound){
      System.out.println("Final!");
    }else{
      System.out.println(meetingRound);
    }
  }

  private static boolean inTwoHalfs(int a, int b, int n) {
    int maxOne = n / 2;
    return a <= maxOne && b > maxOne || b <= maxOne && a > maxOne;
  }

  private static int getLogBaseTwo(int n){
    return (int) Math.ceil(Math.log10(1.0 * n) / Math.log10(2.0));
  }
}
