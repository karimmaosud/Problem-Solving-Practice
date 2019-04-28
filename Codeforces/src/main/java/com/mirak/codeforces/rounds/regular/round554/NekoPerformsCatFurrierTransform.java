package com.mirak.codeforces.rounds.regular.round554;

import java.io.*;
import java.util.*;

public class NekoPerformsCatFurrierTransform {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int x = Integer.parseInt(reader.readLine());
    List<Integer> appliedNums = new ArrayList<>();
    int steps = 0;
    for (int i = 29; i >= 0; --i) {
      if (isPerfectNumber(x)) {
        break;
      }
      if (1 << i > x || (x & (1 << i)) != 0) {
        continue;
      }
      appliedNums.add(i + 1);
      x ^= ((1 << (i + 1)) - 1);
      steps++;
      if (isPerfectNumber(x)) {
        break;
      }
      x++;
      steps++;
    }

    System.out.println(steps);
    for (int num : appliedNums) {
      System.out.print(num + " ");
    }
  }

  private static boolean isPerfectNumber(int num) {
    while (num > 0) {
      if ((num & 1) == 0) {
        return false;
      }
      num >>= 1;
    }
    return true;
  }

}
