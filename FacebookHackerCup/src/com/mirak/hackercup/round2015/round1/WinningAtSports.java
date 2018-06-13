package com.mirak.hackercup.round2015.round1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WinningAtSports {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("/Users/masoud/Desktop/winning_at_sports.txt"));
    BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/masoud/Desktop/out.txt"));
    int T = Integer.parseInt(reader.readLine());
    String[] strs;
    for(int t = 1; t <= T; t++) {
      strs = reader.readLine().split("-");
      int myFinalScore = Integer.parseInt(strs[0]);
      int otherFinalScore = Integer.parseInt(strs[1]);
      long[][] dp1 = new long[myFinalScore + 1][otherFinalScore + 1];
      long[][] dp2 = new long[myFinalScore + 1][otherFinalScore + 1];
      initDpArray(dp1);
      initDpArray(dp2);
      writer.write("Case #" + t + ": " + solveStressFree(0, 0, myFinalScore, otherFinalScore, dp1) + " " + solveStressFul(0, 0, myFinalScore, otherFinalScore, dp2) + "\n");
    }
    reader.close();
    writer.close();
  }

  private static long mod = 1000000007L;
  private static long solveStressFree(int myScore, int otherScore, int myFinalScore, int otherFinalScore, long[][] dp) {

    if(myScore == myFinalScore && otherScore == otherFinalScore) {
      return 1;
    }

    if(dp[myScore][otherScore] != -1) {
      return dp[myScore][otherScore];
    }

    long ans = 0;
    if(myScore + 1 <= myFinalScore) {
      ans = (ans + solveStressFree(myScore + 1, otherScore, myFinalScore, otherFinalScore, dp) % mod) % mod;
    }

    if(otherScore + 1 < myScore && otherScore + 1 <= otherFinalScore) {
      ans = (ans + solveStressFree(myScore, otherScore + 1, myFinalScore, otherFinalScore, dp) % mod) % mod;
    }
    return dp[myScore][otherScore] = ans;
  }

  private static long solveStressFul(int myScore, int otherScore, int myFinalScore, int otherFinalScore, long[][] dp) {

    if(myScore == myFinalScore && otherScore == otherFinalScore) {
      return 1;
    }

    if(dp[myScore][otherScore] != -1) {
      return dp[myScore][otherScore];
    }

    long ans = 0;

    if(otherScore + 1 <= otherFinalScore) {
      ans = (ans + solveStressFul(myScore, otherScore + 1, myFinalScore, otherFinalScore, dp) % mod) % mod;
    }

    if((otherScore == otherFinalScore || myScore + 1 <= otherScore) && myScore + 1 <= myFinalScore) {
      ans = (ans + solveStressFul(myScore + 1, otherScore, myFinalScore, otherFinalScore, dp) % mod) % mod;
    }

    return dp[myScore][otherScore] = ans;
  }

  private static void initDpArray(long[][] dp) {
    for(int i = 0; i < dp.length; i++) {
      for(int j = 0; j < dp[i].length; j++) {
        dp[i][j] = -1;
      }
    }
  }
}
