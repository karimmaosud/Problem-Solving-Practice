package com.mirak.practice.codeforces.rounds.regular.round468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ProblemA {
  public static void main(String[] args)throws IOException{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int a = Integer.parseInt(reader.readLine());
    int b = Integer.parseInt(reader.readLine());
    int meetingPoint = (a + b) / 2;
    int ap = Math.abs(a - meetingPoint);
    int bp = Math.abs(b - meetingPoint);
    System.out.println((ap * (ap + 1)) / 2 + (bp * (bp + 1)) / 2);
  }
}
