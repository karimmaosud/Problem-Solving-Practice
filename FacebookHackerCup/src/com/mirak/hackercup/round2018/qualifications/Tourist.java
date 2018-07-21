package com.mirak.hackercup.round2018.qualifications;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Tourist {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("/Users/masoud/Desktop/tourist.txt"));
    BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/masoud/Desktop/out.txt"));
    int T = Integer.parseInt(reader.readLine());
    String[] strs;
    for (int t = 1; t <= T; t++) {
      strs = reader.readLine().split(" ");
      int n = Integer.parseInt(strs[0]);
      int k = Integer.parseInt(strs[1]);
      long v = Long.parseLong(strs[2]);

      ArrayList<String> attractions = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        attractions.add(reader.readLine());
      }

      ArrayList<Integer> cycleStart = new ArrayList<>();
      cycleStart.add(0);
      int next = k % n;
      while (next != 0) {
        cycleStart.add(next);
        next = (next + k) % n;
      }

      int startIndex = cycleStart.get((int) ((v % cycleStart.size() - 1) + cycleStart
          .size()) % cycleStart.size());
      ArrayList<String> res = new ArrayList<>();
      for (int i = 0; i < k - (n - startIndex); i++) {
        res.add(attractions.get(i));
      }

      for (int i = startIndex; i < Math.min(n, startIndex + k); i++) {
        res.add(attractions.get(i));
      }
      writer.write("Case #" + t + ":");
      for (String str : res) {
        writer.write(" " + str);
      }
      writer.write('\n');
    }
    reader.close();
    writer.close();

  }
}
