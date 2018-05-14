package com.mirak.codeforces.rounds.regular.round478;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class ProblemA {
  public static void main(String[] args) throws IOException{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    String[] strs = reader.readLine().split(" ");
    Set<Integer> nums = new HashSet<>();
    for(String s: strs) {
      int mask = 0;
      for(char a : s.toCharArray()) {
        mask |= (1 << (a - 'a'));
      }
      nums.add(mask);
    }
    System.out.println(nums.size());
  }
}
