package com.mirak.hackercup.round2018.qualifications;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Interception {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("/Users/masoud/Desktop/interception.txt"));
    BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/masoud/Desktop/out.txt"));
    int T = Integer.parseInt(reader.readLine());
    for(int t = 1; t <= T; t++) {
      int N = Integer.parseInt(reader.readLine());
      int[] p = new int[N + 1];
      for(int i = 0; i < N + 1; i++) {
        p[i] = Integer.parseInt(reader.readLine());
      }
      writer.write("Case #" + t + ": ");
      if((N & 1) == 0) {
        writer.write('0');
      }else {
        writer.write("1\n0.0");
      }
      writer.write('\n');
    }
    reader.close();
    writer.close();
  }
 }
