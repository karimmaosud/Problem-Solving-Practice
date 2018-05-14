package com.mirak.codeforces.rounds.regular.round478;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemC {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");
    int n = Integer.parseInt(strs[0]);
    int q = Integer.parseInt(strs[1]);
    strs = reader.readLine().split(" ");
    long[] nums = new long[n];
    for(int i = 0; i < n; i++) {
      nums[i] = Long.parseLong(strs[i]);
    }
    strs = reader.readLine().split(" ");
    long[] k = new long[q];
    for(int i = 0; i < q; i++) {
      k[i] = Long.parseLong(strs[i]);
    }
    long accSum = 0;
    long[] sum = getSumArray(nums);
    for(int i = 0; i < q; i++) {
      accSum += k[i];
      int remainder = getRemainder(sum, accSum);
      if(remainder == 0){
        remainder = n;
        accSum = 0;
      }
      System.out.println(remainder);
    }
  }

  private static long[] getSumArray(long[] nums) {
    long[] sum = new long[nums.length];
    sum[0] = nums[0];
    for(int i = 1; i < nums.length; i++) {
      sum[i] = sum[i - 1] + nums[i];
    }
    return sum;
  }

  private static int getRemainder(long[] sum, long strength){
    int low = 0;
    int high = sum.length - 1;
    while(low <= high) {
      int mid = (low + high) / 2;
      if(sum[mid] <= strength) {
        low = mid + 1;
      }else{
        high = mid - 1;
      }
    }
    return Math.max(0, sum.length - low);
  }
}
