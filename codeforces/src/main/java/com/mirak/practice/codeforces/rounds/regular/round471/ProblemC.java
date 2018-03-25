package com.mirak.practice.codeforces.rounds.regular.round471;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class ProblemC {
  private static final long NEXTMAX = 1000000000000L;

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<Long> nums = getInitialSet();
    Collections.sort(nums);
    String[] strs;
    int Q = Integer.parseInt(reader.readLine());
    for(int q = 0; q < Q; q++) {
      strs = reader.readLine().split(" ");
      long L = Long.parseLong(strs[0]);
      long R = Long.parseLong(strs[1]);
      System.out.println(solveQuery(L, R, nums));
    }
  }

  private static ArrayList<Long> getInitialSet(){
    boolean[] vis = new boolean[1000001];
    ArrayList<Long> nums = new ArrayList<>();
    nums.add(1L);
    for(int i = 2; i <= 1000000; i++){
      if(!vis[i]){
        int k = (int) (18.0 / (Math.log10(i))) - 1;
        for(long j = (long) i * i; k > 0; j *= i, k--){
          if(j <= 1000000){
            vis[(int)j] = true;
          }
          if(addToList(j)) {
            nums.add(j);
          }
        }
      }
    }

    return nums;
  }

  private static boolean addToList(long num){
    return num <= (NEXTMAX) || !hasSquareRoot(num);
  }

  private static boolean hasSquareRoot(long num){
    long root = (long) Math.sqrt(num);
    return root * root == num;
  }

  private static int solveQuery(long L, long R, ArrayList<Long> nums){
    int left = getBound(L, nums, true);
    int right = getBound(R, nums, false);
    int res = 0;
    if(right >= left){
      res = (right - left) + 1;
    }

    if(R > NEXTMAX){
      if(L <= NEXTMAX) {
        L = NEXTMAX + 1;
      }
      left = (int) Math.ceil(Math.sqrt(L));
      right = getMysteriousCount(R, false);
      if(right >= left){
        res += (right - left) + 1;
      }
    }

    return res;
  }

  private static int getBound(long key, ArrayList<Long> nums, boolean lowerBound){
    int low = 0;
    int high = nums.size() - 1;
    while(low <= high){
      int mid = (low + high) / 2;
      if(nums.get(mid) < key) {
        low = mid + 1;
      }else if(nums.get(mid) > key) {
        high = mid - 1;
      }else{
        return mid;
      }
    }
    if(lowerBound) {
      return low;
    }
    return high;
  }


  private static int getMysteriousCount(long key, boolean lowerBound){
    int low = 1000001;
    int high = 1000000000;
    while(low <= high) {
      int mid = (low + high) / 2;
      long current = (long) mid * mid;
      if(current < key) {
        low = mid + 1;
      }else  if(current > key) {
        high = mid - 1;
      }else{
        return mid;
      }
    }
    if(lowerBound) {
      return low;
    }
    return high;
  }

}
