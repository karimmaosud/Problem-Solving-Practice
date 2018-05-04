package com.mirak.google.codejam.cg2018.round1B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class RoundingErrorSmall {
  public static void main(String[] args) throws IOException{

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(reader.readLine());
    for(int t = 1; t <= T; t++) {
      String [] strs = reader.readLine().split(" ");
      int n = Integer.parseInt(strs[0]);
      int l = Integer.parseInt(strs[1]);
      strs = reader.readLine().split(" ");
      ArrayList<Integer> source = new ArrayList<>();
      for(int i = 0; i < l; i++) {
        source.add(Integer.parseInt(strs[i]));
      }
      Collections.sort(source);
      ArrayList<ArrayList<Integer>> partitions = new ArrayList<>();
      generatePartitions(n, new LinkedList<>(), partitions);
      int max = 0;
      for(ArrayList<Integer> partition: partitions) {
        if(isValidPartition(partition, source)){
          int value = 0;
          for(int num: partition) {
            value += (int) Math.round((100.0 * num) / (1.0 * n));
          }
          max = Math.max(max, value);
        }
      }
      System.out.println("Case #" + t + ": " + max);
    }
  }

  private static boolean isValidPartition(ArrayList<Integer> partition, ArrayList<Integer> source){

    if(partition.size() < source.size()) {
      return false;
    }
    int p1 = 0, p2 = 0;
    while(p1 < source.size() && p2 < partition.size()) {
      if(source.get(p1) <= partition.get(p2)){
        p1++;
        p2++;
      }else{
        p2++;
      }
    }
    return p1 == source.size();
  }

  private static void generatePartitions(int rem, LinkedList<Integer> current, ArrayList<ArrayList<Integer>> partitions){
    if(rem == 0){
      ArrayList<Integer> cloned = new ArrayList<>(current);
      partitions.add(cloned);
      return;
    }
    int start = current.size() > 0? current.get(current.size() - 1): 1;
    for(int i = start; rem - i >= 0; i++) {
      current.addLast(i);
      generatePartitions(rem - i, current, partitions);
      current.removeLast();
    }
  }
}
