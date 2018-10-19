package com.mirak.codeforces.rounds.regular.mail_ru_cup2018;

import java.io.*;

public class ElevatorOrStairs {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");
    int x = Integer.parseInt(strs[0]);
    int y = Integer.parseInt(strs[1]);
    int z = Integer.parseInt(strs[2]);
    int t1 = Integer.parseInt(strs[3]);
    int t2 = Integer.parseInt(strs[4]);
    int t3 = Integer.parseInt(strs[5]);

    int dStairs = Math.abs(x - y) * t1;
    int dElevator = Math.abs(x - z) * t2 + Math.abs(x - y) * t2 + 3 * t3;
    if (dStairs < dElevator) {
      System.out.println("NO");
    } else {
      System.out.println("YES");
    }
  }
}
