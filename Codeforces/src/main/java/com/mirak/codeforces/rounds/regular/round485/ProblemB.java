package com.mirak.codeforces.rounds.regular.round485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemB {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] line = reader.readLine().split(" ");
    int x = Integer.parseInt(line[0]);
    int y = Integer.parseInt(line[1]);
    double dxPy = y * Math.log10(1.0 * x);
    double dyPx = x * Math.log10(1.0 * y);
    if(dxPy < dyPx) {
      System.out.println("<");
    }else if(dxPy > dyPx){
      System.out.println(">");
    }else{
      System.out.println("=");
    }
  }
}
