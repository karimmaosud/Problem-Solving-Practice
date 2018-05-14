package com.mirak.codeforces.rounds.regular.round476;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemA {
  public static void main(String[] args) throws IOException{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");
    int k = Integer.parseInt(strs[0]);
    int n = Integer.parseInt(strs[1]);
    int s = Integer.parseInt(strs[2]);
    int p = Integer.parseInt(strs[3]);
    int sheetsPerPerson = (int) Math.ceil((1.0 * n) / (1.0 * s));
    System.out.println((int) Math.ceil((1.0 * sheetsPerPerson * k) / (1.0 * p)));

  }
}
