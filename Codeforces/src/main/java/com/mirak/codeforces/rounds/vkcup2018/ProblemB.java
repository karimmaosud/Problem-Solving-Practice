package com.mirak.codeforces.rounds.vkcup2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ProblemB {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    int[] nums = new int[n];
    String[] strs = reader.readLine().split(" ");
    for(int i = 0; i < n; i++){
      nums[i] = Integer.parseInt(strs[i]);
    }
    Arrays.sort(nums);

  }
}
