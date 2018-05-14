package com.mirak.codeforces.rounds.regular.round478;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemB {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");
    int[] nums = new int[14];
    for(int i = 0; i < strs.length; i++) {
      nums[i] = Integer.parseInt(strs[i]);
    }
    long maxScore = 0;
    for(int i = 0; i < 14; i++) {
      if(nums[i] > 0) {
        int[] cloned = nums.clone();
        int added = cloned[i] / 14;
        int rem = cloned[i] % 14;
        int j = (i + 1) % 14;
        while(j != i) {
          cloned[j] += added;
          j = (j + 1) % 14;
        }
        j = (i + 1) % 14;
        while(rem != 0) {
          cloned[j]++;
          j = (j + 1) % 14;
          rem--;
        }
        cloned[i] = 0;
        long score = 0;
        for(j = 0; j < 14; j++) {
          if((cloned[j] & 1) == 0){
            score += cloned[j];
          }
        }
        maxScore = Math.max(maxScore, score);
      }
    }
    System.out.println(maxScore);
  }
}
