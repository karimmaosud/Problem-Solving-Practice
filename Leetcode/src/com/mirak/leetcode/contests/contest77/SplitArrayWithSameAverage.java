package com.mirak.leetcode.contests.contest77;

import java.util.ArrayList;
import java.util.List;

public class SplitArrayWithSameAverage {

  public static boolean splitArraySameAverage(int[] A) {
    int sum = 0;
    int[][][] dp = new int[30][300000][15];
    for(int i = 0; i < dp.length; i++) {
      for(int j = 0; j < dp[i].length; j++) {
        for(int k = 0; k < dp[i][j].length; k++) {
          dp[i][j][k] = -1;
        }
      }
    }
    for(int i = 0; i < A.length; i++) {
      sum += A[i];
    }

    for(int i = 1; i <= A.length/2; i++) {
      if(canSplit(A, 0, 0, i, sum)){
        return true;
      }
    }
    return false;
  }
  private static boolean canSplit(int[] A, int idx, int runnerSum, int k, int sum){
    if(k == 0){
      if(runnerSum == 0 || runnerSum == sum) {
        return false;
      }
//      return Math.abs(((1.0 * runnerSum) / K) - ((1.0 * sum - runnerSum) / (A.length - K))) <= 1e-9;
    }

    if(idx == A.length) {
      return false;
    }

//    return canSplit(A, idx + 1, mask, k, sum) | canSplit(A, idx + 1, mask | (1 << idx), k - 1, sum);
    return false;
  }
  public static void main(String[] args) {
    List<Integer> l1 = new ArrayList<>();
    List<Integer> l2 = new ArrayList<>();
    for(int i = 1; i <= 8; i++){
      l1.add(i);
    }
    int chosen;
    while((chosen = balanceWithOneNumber(l1, l2)) != -1){
      int removed = l1.remove(chosen);
      l2.add(removed);
      System.out.println(l1 + "\t\t\t" + l2);
    }
  }

  private static int balanceWithOneNumber(List<Integer> l1, List<Integer> l2){

    int s1 = 0, s2 = 0;
    for(int num: l1){
      s1 += num;
    }
    for(int num: l2){
     s2 += num;
    }
    double min = l2.size() == 0? 1000000000.0: Math.abs(((1.0 * s1) / l1.size()) - ((1.0 * s2) / l2.size()));
    int chosen = -1;
    for(int i = 0; i < l1.size(); i++){
      int num = l1.get(i);
      double d1 = (1.0 * (s1 - num)) / l1.size();
      double d2 = (1.0 * (s2 + num)) / (l2.size() + 1);
      if(Math.abs(d1 - d2) < min){
        min = Math.abs(d1 - d2);
        chosen = i;
      }
    }
    return chosen;
  }
}
