package com.mirak.leetcode.contests.contest85;

import java.util.ArrayList;

public class PushDominoes {
  public static String pushDominoes(String dominoes) {

    ArrayList<Integer> forces = new ArrayList<>();
    for(int i = 0; i < dominoes.length(); i++) {
      if(dominoes.charAt(i) != '.') {
        forces.add(i);
      }
    }
    char[] res = dominoes.toCharArray();
    int prev = -1;
    for(int i = 0; i < forces.size(); i++) {
      if(res[forces.get(i)] == 'L') {
        int j = forces.get(i);
        while(j > prev) {
          res[j--] = 'L';
        }
        prev = forces.get(i);
      }else{
        if(i + 1 == forces.size() || res[forces.get(i + 1)] == 'R') {
          int j = forces.get(i);
          int end = i + 1 == forces.size()? res.length: forces.get(i + 1);
          while(j < end) {
            res[j++] = 'R';
          }
          prev = end - 1;
        }else{
          int j = forces.get(i + 1);
          prev = forces.get(i);
          while(prev < j) {
            res[prev++] = 'R';
            res[j--] = 'L';
          }
          prev = forces.get(i + 1);
          i++;
        }
      }
    }
    return new String(res);
  }

  public static void main(String[] args) {
    System.out.println(pushDominoes("RR.L"));
  }
}
