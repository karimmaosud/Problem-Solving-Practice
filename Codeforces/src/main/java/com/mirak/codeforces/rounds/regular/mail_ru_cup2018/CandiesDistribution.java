package com.mirak.codeforces.rounds.regular.mail_ru_cup2018;

import java.io.*;
import java.util.Arrays;

public class CandiesDistribution {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    int[] l = new int[n];
    int[] r = new int[n];
    String[] lStrs = reader.readLine().split(" ");
    String[] rStrs = reader.readLine().split(" ");
    for (int i = 0; i < n; i++) {
      l[i] = Integer.parseInt(lStrs[i]);
      r[i] = Integer.parseInt(rStrs[i]);
    }

    int[] res = new int[n];
    Arrays.fill(res, -1);
    int[] leftCount = new int[n];
    int[] rightCount = new int[n];

    int num = n;
    for (int sum = 0; sum < n; sum++) {
      for (int i = 0; i < n; i++) {
        if (l[i] + r[i] == sum && leftCount[i] == l[i] && rightCount[i] == r[i]) {
          res[i] = num;
        }
      }
      num--;
      leftCount[0] = res[0] == -1 ? 0 : 1;
      for (int i = 1; i < n; i++) {
        leftCount[i] = leftCount[i - 1] + (res[i] == -1 ? 0 : 1);
      }
      rightCount[n - 1] = res[n - 1] == -1 ? 0 : 1;
      for (int i = n - 2; i >= 0; i--) {
        rightCount[i] = rightCount[i + 1] + (res[i] == -1 ? 0 : 1);
      }
    }

    for (int i = 0; i < n; i++) {
      if (res[i] == -1) {
        System.out.println("NO");
        return;
      }
    }
    System.out.print("YES\n" + res[0]);
    for (int i = 1; i < n; i++) {
      System.out.print(" " + res[i]);
    }
    System.out.println();
  }
}
