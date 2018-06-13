package com.mirak.hackercup.round2015.round1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Homework {
  private static final int MAXN = 10000001;
  public static void main(String[] args) throws IOException {
    ArrayList<ArrayList<Integer>> list = preCalculate();
    BufferedReader reader = new BufferedReader(new FileReader("/Users/masoud/Desktop/homework.txt"));
    BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/masoud/Desktop/out.txt"));
    int T = Integer.parseInt(reader.readLine());
    String[] strs;
    for(int t = 1; t <= T; t++) {
      strs = reader.readLine().split(" ");
      int a = Integer.parseInt(strs[0]);
      int b = Integer.parseInt(strs[1]);
      int k = Integer.parseInt(strs[2]);
      if(k >= list.size()) {
        writer.write("Case #" + t + ": " + 0 + "\n");
        continue;
      }

      int aUpperBound = upperBound(list.get(k), a);
      int bUpperBound = upperBound(list.get(k), b);
      if(bUpperBound < list.get(k).size() && list.get(k).get(bUpperBound) > b) {
        bUpperBound--;
      }

      if(aUpperBound == list.get(k).size() || bUpperBound == -1) {
        // 0.
        writer.write("Case #" + t + ": " + 0 + "\n");
        continue;
      }

      int res = bUpperBound - aUpperBound;
      if(bUpperBound < list.get(k).size()) {
        res++;
      }
      writer.write("Case #" + t + ": " + res + "\n");
    }
    reader.close();
    writer.close();
  }

  private static ArrayList<ArrayList<Integer>> preCalculate() {
    int[] primacity = new int[MAXN];
    ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    boolean[] flag = new boolean[MAXN];

    for(int i = 2; i < MAXN; i++) {
      flag[i] = true;
    }

    for(int i = 2; i <= MAXN/2; i++) {
      if(flag[i]) {
        primacity[i]++;
        if(list.size() < primacity[i]) {
          list.add(new ArrayList<>());
        }
        for(int j = i + i; j < MAXN; j += i) {
          flag[j] = false;

          primacity[j]++;
          if(list.size() < primacity[j]) {
            list.add(new ArrayList<>());
          }
        }
      }
    }

    list.add(new ArrayList<>());

    for(int i = 2; i < MAXN; i++) {
      if(primacity[i] == 0) {
        primacity[i]++;
      }
      list.get(primacity[i]).add(i);
    }

    return list;
  }

  private static int upperBound(ArrayList<Integer> list, int key) {
    int low = 0;
    int high = list.size() - 1;
    while(low <= high) {
      int mid = (low + high) / 2;
      if(list.get(mid) > key) {
        high = mid - 1;
      }else if(list.get(mid) < key) {
        low = mid + 1;
      }else {
        return mid;
      }
    }
    return low;
  }
}
