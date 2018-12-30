package com.mirak.codeforces.rounds.regular.goodbye2018;

import java.io.*;
import java.util.*;


public class NewYearAndTheSphereTransmission {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    ArrayList<Integer> factors = new ArrayList<>();
    for (int i = 1; i * i <= n; i++) {
      if (n % i == 0) {
        factors.add(i);
        if (i != n / i) {
          factors.add(n / i);
        }
      }

    }

    ArrayList<Long> res = new ArrayList<>();
    for (int i = 0; i < factors.size(); i++) {
      long count = (long) (n / factors.get(i));
      long last = (long) (n - factors.get(i) + 1);
      res.add((count * (1 + last)) / 2);
    }
    StringBuilder builder = new StringBuilder();
    Collections.sort(res);
    for (int i = 0; i < res.size(); i++) {
      if (i > 0) {
        builder.append(" ");
      }
      builder.append(res.get(i));
    }
    System.out.println(builder);
  }

}
